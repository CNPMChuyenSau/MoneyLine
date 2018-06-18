package com.vanluom.group11.quanlytaichinhcanhan.servicelayer.qif;

import android.content.Context;
import android.text.TextUtils;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.core.TransactionTypes;
import com.vanluom.group11.quanlytaichinhcanhan.database.ISplitTransaction;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.SplitCategoriesRepository;
import com.vanluom.group11.quanlytaichinhcanhan.servicelayer.CategoryService;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDate;
import com.vanluom.group11.quanlytaichinhcanhan.viewmodels.AccountTransactionDisplay;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import info.javaperformance.money.Money;

/**
 * A .qif file record. Represents a first transaction or account header record.
 * References:
 * QIF format
 * http://en.wikipedia.org/wiki/Quicken_Interchange_Format
 * Date formats inside qif file
 * http://www.unicode.org/reports/tr35/tr35-dates.html#Date_Format_Patterns
 */
public class QifRecord {
    public QifRecord(Context context) {
        mContext = context;
    }

    private Context mContext;

    public Context getContext() {
        return mContext;
    }

    /**
     * Parses the data and generates a QIF record for transaction.
     * @return A string representing one QIF record
     */
    public String parse(AccountTransactionDisplay transaction) throws ParseException {
        final String lineSeparator = System.getProperty("line.separator");

        StringBuilder builder = new StringBuilder();

        // Date
        String date = parseDate(transaction);
        builder.append("D");
        builder.append(date);
        builder.append(lineSeparator);

        // Amount
        String amount = parseAmount(transaction);
//        builder.append("U");
//        builder.append(this.Amount);
//        builder.append(System.lineSeparator());
        builder.append("T");
        builder.append(amount);
        builder.append(lineSeparator);

        // Cleared status
//        String status = parseCleared(transaction);
        String status = transaction.getStatusCode();
        if (!TextUtils.isEmpty(status)) {
            // Cleared: * or c. We don't have Cleared in MMEX.
            // Reconciled: X or R
            builder.append("C");
            switch (status) {
                case "R":
                    builder.append(status);
                    break;
            }
        }

        // Payee
        String payee = transaction.getPayee();
        if (!TextUtils.isEmpty(payee)) {
            builder.append("P");
            builder.append(payee);
            builder.append(lineSeparator);
        }

        // Categories / Transfers
        String category;
//        String transactionTypeName = getTransactionTypeName(transaction);
        TransactionTypes transactionType = transaction.getTransactionType();
        if (transactionType.equals(TransactionTypes.Transfer)) {
            // Category is the destination account name.
            category = transaction.getAccountName();
            // in square brackets
            category = "[%]".replace("%", category);
        } else {
            // Category
            category = parseCategory(transaction);
        }
        if (category != null) {
            builder.append("L");
            builder.append(category);
            builder.append(lineSeparator);
        }

        // Split Categories
        boolean splitCategory = transaction.getIsSplit();
        if (splitCategory) {
            String splits = getSplitCategories(transaction);
            builder.append(splits);
        }

        // Memo
        String memo = transaction.getNotes();
        if (!TextUtils.isEmpty(memo)) {
            builder.append("M");
            builder.append(memo);
            builder.append(lineSeparator);
        }

        builder.append("^");
        builder.append(lineSeparator);

        return builder.toString();
    }

    public String getSplitCategories(AccountTransactionDisplay transaction) {
        StringBuilder builder = new StringBuilder();

        // retrieve splits
        SplitCategoriesRepository repo = new SplitCategoriesRepository(mContext);
        int transactionId = transaction.getId();
        ArrayList<ISplitTransaction> splits = repo.loadSplitCategoriesFor(transactionId);
        if (splits == null) return Constants.EMPTY_STRING;

        String transactionType = transaction.getTransactionTypeName();

        for(ISplitTransaction split : splits) {
            String splitRecord = getSplitCategory(split, transactionType);
            builder.append(splitRecord);
        }

        return builder.toString();
    }

    private String getSplitCategory(ISplitTransaction split, String transactionType) {
        final String lineSeparator = System.getProperty("line.separator");

        StringBuilder builder = new StringBuilder();

        // S = category in split
        // $ = amount in split
        // E = memo in split

        // category
        CategoryService service = new CategoryService(getContext());
        String category = service.getCategorySubcategoryName(split.getCategoryId(), split.getSubcategoryId());
        builder.append("S");
        builder.append(category);
        builder.append(lineSeparator);

        // amount
        Money amount = split.getAmount();
        // e sign
        if (TransactionTypes.valueOf(transactionType).equals(TransactionTypes.Withdrawal)) {
            amount = amount.negate();
        }
        if (TransactionTypes.valueOf(transactionType).equals(TransactionTypes.Deposit)) {
            // leave positive?
        }
        builder.append("$");
        builder.append(amount);
        builder.append(lineSeparator);

        // memo - currently we don't have a field for it.
//        String memo = split.get

        return builder.toString();
    }

    private String parseDate(AccountTransactionDisplay transaction) throws ParseException {
        Date date = transaction.getDate();

        // todo: get Quicken date format from preferences.
        String qifDatePattern = "MM/dd''yy";
//        DateTimeFormatter qifFormat = DateTimeFormat.forPattern();
//        return qifFormat.print(date);

        MmxDate dateTime = new MmxDate(date);
        return dateTime.toString(qifDatePattern);
    }

    private String parseAmount(AccountTransactionDisplay transaction) {
        String amount;
        if (transaction.getTransactionType().equals(TransactionTypes.Transfer)) {
            amount = transaction.getToAmount().toString();
        } else {
            amount = transaction.getAmount().toString();
        }
        return amount;
    }

    private String parseCategory(AccountTransactionDisplay transaction) {
        String category = transaction.getCategory();
        String subCategory = transaction.getSubcategory();

        if (!TextUtils.isEmpty(subCategory)) {
            return category + ":" + subCategory;
        } else {
            return category;
        }
    }
}

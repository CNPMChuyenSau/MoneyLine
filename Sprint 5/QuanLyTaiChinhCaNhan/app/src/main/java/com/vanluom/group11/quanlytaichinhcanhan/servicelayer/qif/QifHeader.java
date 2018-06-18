package com.vanluom.group11.quanlytaichinhcanhan.servicelayer.qif;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.text.TextUtils;

import com.vanluom.group11.quanlytaichinhcanhan.datalayer.AccountRepository;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Account;
import com.vanluom.group11.quanlytaichinhcanhan.account.AccountTypes;
import com.vanluom.group11.quanlytaichinhcanhan.database.QueryAllData;

import java.util.HashMap;

/**
 * Represents qif header record.
 */
public class QifHeader {
    public QifHeader(Context context) {
        mContext = context;
    }

    private Context mContext;

    public Context getContext() {
        return mContext;
    }

    public String parse(Cursor cursor) {
        StringBuilder builder = new StringBuilder();

        // Line separator.
        String separator;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            separator = System.getProperty("line.separator");
        } else {
            separator = System.lineSeparator();
        }

        Account account = loadAccount(cursor);

        /* header from mmex desktop:
!Account
NCash (EUR)
TBank
D[EUR]
$57.300000
^
!Type:Cash
         */

        // header depends on the account type.

        builder.append("!Account");
        builder.append(separator);

        // name
        builder.append("N");
        String name = account.getName();
        builder.append(name);
        builder.append(separator);

        // description
        String description = account.getNotes();
        if (!TextUtils.isEmpty(description)) {
            builder.append("D");
            builder.append(description);
            builder.append(separator);
        }

        // account type
        String accountType = getAccountType(account);
        builder.append("T");
        builder.append(accountType);
        builder.append(separator);

        // Limit, for credit cards only.
//        if (accountType.equals(Constants.ACCOUNT_TYPE_CREDIT_CARD)) {
//            builder.append("L");
//            builder.append();
//        builder.append(System.lineSeparator());
//        }

        // Header separator.
        builder.append("^");
        builder.append(separator);

        // also add the first line for transaction list.
        builder.append("!Type:");
        builder.append(accountType);
        builder.append(separator);

        return builder.toString();
    }

    private String getAccountType(Account account) {
        String accountType = account.getTypeName();

        // Translation table:
        HashMap<String, String> accountDictionary = new HashMap<>();
        accountDictionary.put(AccountTypes.CHECKING.toString(), "Bank");
        accountDictionary.put(AccountTypes.TERM.toString(), "Bank");
        accountDictionary.put(AccountTypes.CREDIT_CARD.toString(), "CCard");
        // !Type:Invst
        // Newer versions use Port instead of Invst.
        accountDictionary.put(AccountTypes.INVESTMENT.toString(), "Port");
        // Cash?

        String result = accountDictionary.get(accountType);

        return result;
    }

    private String createCreditCardHeader() {
        StringBuilder builder = new StringBuilder();

        // from quicken
        /*
N28 Degrees MasterCard
TCCard
L5,000.00
^
         */

        return "not implemented";
    }

    private Account loadAccount(Cursor cursor) {
//        int accountId = cursor.getInt(cursor.getColumnIndex(QueryAllData.ACCOUNTID));
        int accountId = cursor.getInt(cursor.getColumnIndex(QueryAllData.TOACCOUNTID));
        AccountRepository repo = new AccountRepository(getContext());
        Account account = repo.load(accountId);
        return account;
    }
}

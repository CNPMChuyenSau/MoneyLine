package com.vanluom.group11.quanlytaichinhcanhan.viewmodels;

import android.database.Cursor;
import android.database.DatabaseUtils;

import com.vanluom.group11.quanlytaichinhcanhan.core.TransactionStatuses;
import com.vanluom.group11.quanlytaichinhcanhan.core.TransactionTypes;
import com.vanluom.group11.quanlytaichinhcanhan.database.QueryAllData;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.EntityBase;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDate;

import java.util.Date;

import info.javaperformance.money.Money;

/**
 * Record from All Data query. Used for account transactions lists (search results, account
 * transactions).
 * Source is QueryAllData.
 * Note: This data is readonly! Records can not be created or updated.
 */
public class AccountTransactionDisplay
    extends EntityBase {

    @Override
    public void loadFromCursor(Cursor c) {
        super.loadFromCursor(c);

        // Reload all money values.
        DatabaseUtils.cursorDoubleToCursorValues(c, QueryAllData.Amount, this.contentValues);
        DatabaseUtils.cursorDoubleToCursorValues(c, QueryAllData.ToAmount, this.contentValues);
    }

    public Integer getId() {
        return getInt(QueryAllData.ID);
    }

    public Integer getAccountId() {
        return getInt(QueryAllData.ACCOUNTID);
    }

    public String getAccountName() {
        return getString(QueryAllData.AccountName);
    }

    public Money getAmount() {
        return getMoney(QueryAllData.Amount);
    }

    public String getCategory() {
        return getString(QueryAllData.Category);
    }

    public String getDateString() {
        return getString(QueryAllData.Date);
    }

    public Date getDate() {
        String dateString = getDateString();

        Date dateTime = new MmxDate(dateString).toDate();

        return dateTime;
    }

    public String getNotes() {
        return getString(QueryAllData.Notes);
    }

    public boolean getIsSplit() {
        int split = getInt(QueryAllData.SPLITTED);
        return split > 0;
    }

    public String getPayee() {
        return getString(QueryAllData.Payee);
    }

    public String getStatusCode() {
        return getString(QueryAllData.Status);
    }

    public TransactionStatuses getStatus() {
        String code = getStatusCode();
        return TransactionStatuses.get(code);
    }

    public String getSubcategory() {
        return getString(QueryAllData.Subcategory);
    }

    public int getToAccountId() {
        return getInt(QueryAllData.TOACCOUNTID);
    }

    public Money getToAmount() {
        return getMoney(QueryAllData.ToAmount);
    }

    public String getTransactionTypeName() {
        return getString(QueryAllData.TransactionType);
    }

    public TransactionTypes getTransactionType() {
        String typeName = getTransactionTypeName();

        TransactionTypes transactionType = TransactionTypes.valueOf(typeName);
        return transactionType;
    }
}

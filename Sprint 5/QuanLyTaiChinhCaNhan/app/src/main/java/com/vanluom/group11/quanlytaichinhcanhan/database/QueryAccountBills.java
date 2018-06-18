package com.vanluom.group11.quanlytaichinhcanhan.database;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.settings.AppSettings;
import com.vanluom.group11.quanlytaichinhcanhan.settings.LookAndFeelSettings;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxFileUtils;

/**
 * Select account bills = account data with balances.
 */
public class QueryAccountBills
        extends Dataset {

    public static final String ACCOUNTID = "ACCOUNTID";
    public static final String ACCOUNTNAME = "ACCOUNTNAME";
    public static final String STATUS = "STATUS";
    public static final String FAVORITEACCT = "FAVORITEACCT";
    public static final String CURRENCYID = "CURRENCYID";
    public static final String ACCOUNTTYPE = "ACCOUNTTYPE";
    public static final String TOTAL = "TOTAL";
    public static final String RECONCILED = "RECONCILED";
    public static final String TOTALBASECONVRATE = "TOTALBASECONVRATE";
    public static final String RECONCILEDBASECONVRATE = "RECONCILEDBASECONVRATE";

    private int accountId;
    private String accountName;
    private String status;
    private String favoriteAcct;
    private String accountType;
    private int currencyId;
    private double total;
    private double reconciled;
    private double totalBaseConvRate;
    private double reconciledBaseConvRate;

    private Context context;

    // constructor
    public QueryAccountBills(Context context) {
        super(MmxFileUtils.getRawAsString(context, R.raw.query_account_bills), DatasetType.QUERY, "accountbills");

        this.context = context.getApplicationContext();
    }

    public double getReconciled() {
        return reconciled;
    }

    public void setReconciled(double reconciled) {
        this.reconciled = reconciled;
    }

    public double getTotalBaseConvRate() {
        return totalBaseConvRate;
    }

    public void setTotalBaseConvRate(double totalBaseConvRate) {
        this.totalBaseConvRate = totalBaseConvRate;
    }

    public double getReconciledBaseConvRate() {
        return reconciledBaseConvRate;
    }

    public void setReconciledBaseConvRate(double reconciledBaseConvRate) {
        this.reconciledBaseConvRate = reconciledBaseConvRate;
    }

    /**
     * @return the accountId
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public String[] getAllColumns() {
        return new String[]{"ACCOUNTID AS _id", ACCOUNTID, ACCOUNTNAME, STATUS, FAVORITEACCT,
                CURRENCYID, ACCOUNTTYPE, TOTAL, RECONCILED, TOTALBASECONVRATE,
                RECONCILEDBASECONVRATE};
    }

    /**
     * @return the currencyId
     */
    public int getCurrencyId() {
        return currencyId;
    }

    /**
     * @param currencyId the currencyId to set
     */
    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    /**
     * @return the favoriteAcct
     */
    public String getFavoriteAcct() {
        return favoriteAcct;
    }

    /**
     * @param favoriteAcct the favoriteAcct to set
     */
    public void setFavoriteAcct(String favoriteAcct) {
        this.favoriteAcct = favoriteAcct;
    }

    /**
     * @return selection made ​​if it appears only accounts opened and / or favorites
     */
    public String getFilterAccountSelection() {
        LookAndFeelSettings settings = new AppSettings(this.context).getLookAndFeelSettings();

        // check if show only open accounts
        String where = settings.getViewOpenAccounts() ? "LOWER(" + STATUS + ")='open'" : null;
        // check if show fav accounts
        where = settings.getViewFavouriteAccounts() ? "LOWER(" + FAVORITEACCT + ")='true'" : where;

        return !(TextUtils.isEmpty(where)) ? where : null;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the accountType
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public void setValueFromCursor(Cursor c) {
        if (c == null) return;

        if (!(c.getColumnCount() == this.getAllColumns().length)) {
            return;
        }

        this.setAccountId(c.getInt(c.getColumnIndex(ACCOUNTID)));
        this.setAccountName(c.getString(c.getColumnIndex(ACCOUNTNAME)));
        this.setAccountType(c.getString(c.getColumnIndex(ACCOUNTTYPE)));
        this.setCurrencyId(c.getInt(c.getColumnIndex(CURRENCYID)));
        this.setFavoriteAcct(c.getString(c.getColumnIndex(FAVORITEACCT)));
        this.setReconciled(c.getDouble(c.getColumnIndex(RECONCILED)));
        this.setReconciledBaseConvRate(c.getDouble(c.getColumnIndex(RECONCILEDBASECONVRATE)));
        this.setStatus(c.getString(c.getColumnIndex(STATUS)));
        this.setTotal(c.getDouble(c.getColumnIndex(TOTAL)));
        this.setTotalBaseConvRate(c.getDouble(c.getColumnIndex(TOTALBASECONVRATE)));
    }
}

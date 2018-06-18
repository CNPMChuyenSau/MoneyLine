package com.vanluom.group11.quanlytaichinhcanhan.database;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxFileUtils;

/**
 */
public class QueryAllData
    extends Dataset {

    // FIELDS
    public static final String ID = "ID";
    public static final String TransactionType = "TransactionType";
    public static final String Date = "Date";
    public static final String UserDate = "UserDate";
    public static final String Year = "Year";
    public static final String Month = "Month";
    public static final String Day = "Day";
    public static final String Category = "Category";
    public static final String Subcategory = "Subcategory";
    public static final String Amount = "Amount";
    public static final String BaseConvRate = "BaseConvRate";
    public static final String CURRENCYID = "CurrencyID";
    public static final String AccountName = "AccountName";
    public static final String ACCOUNTID = "AccountID";
//    public static final String FromAccountName = "FromAccountName";
//    public static final String FromAccountId = "FromAccountId";
//    public static final String FromAmount = "FromAmount";
//    public static final String FromCurrencyId = "FromCurrencyId";
    public static final String SPLITTED = "Splitted";
    public static final String CategID = "CategID";
    public static final String SubcategID = "SubcategID";
    public static final String Payee = "Payee";
    public static final String PayeeID = "PayeeID";
    public static final String ToAccountName = "ToAccountName";
    public static final String TOACCOUNTID = "ToAccountId";
    public static final String ToAmount = "ToAmount";
    public static final String ToCurrencyId = "ToCurrencyId";
    public static final String TransactionNumber = "TransactionNumber";
    public static final String Status = "Status";
    public static final String Notes = "Notes";
    public static final String currency = "currency";
    public static final String finyear = "finyear";

    public QueryAllData(Context context) {
        super(MmxFileUtils.getRawAsString(context, R.raw.query_alldata), DatasetType.QUERY, "queryalldata");
    }

    @Override
    public String[] getAllColumns() {
        return new String[]{"ID AS _id", ID, TransactionType, Date, UserDate, Year, Month, Day,
                Category, Subcategory, Amount, BaseConvRate, CURRENCYID, AccountName, ACCOUNTID,
//                FromAccountName, FromAccountId, FromAmount, FromCurrencyId,
                SPLITTED, CategID, SubcategID, Payee, PayeeID, TransactionNumber, Status, Notes,
                ToAccountName, TOACCOUNTID, ToAmount, ToCurrencyId,
                currency, finyear};
    }
}

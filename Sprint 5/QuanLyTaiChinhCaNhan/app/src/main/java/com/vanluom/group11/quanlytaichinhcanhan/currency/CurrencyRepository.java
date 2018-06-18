
package com.vanluom.group11.quanlytaichinhcanhan.currency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.vanluom.group11.quanlytaichinhcanhan.database.DatasetType;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.RepositoryBase;
import com.vanluom.group11.quanlytaichinhcanhan.database.WhereStatementGenerator;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Currency;

import info.javaperformance.money.Money;
import timber.log.Timber;

/**
 * Currency repository. Provides access to Currency entities.
 */
public class CurrencyRepository
    extends RepositoryBase<Currency> {

    public CurrencyRepository(Context context) {
        super(context, "currencyformats_v1", DatasetType.TABLE, "currencyformats");

        //this.TABLENAME = "currencyformats_v1";
    }

    @Override
    public String[] getAllColumns() {
        return new String[] {
            "CURRENCYID AS _id", Currency.CURRENCYID, Currency.CURRENCYNAME,
            Currency.PFX_SYMBOL, Currency.SFX_SYMBOL, Currency.DECIMAL_POINT,
            Currency.GROUP_SEPARATOR, Currency.UNIT_NAME, Currency.CENT_NAME,
            Currency.SCALE, Currency.BASECONVRATE, Currency.CURRENCY_SYMBOL
        };
    }

    public Currency load(int id) {
        WhereStatementGenerator where = new WhereStatementGenerator();
        where.addStatement(Currency.CURRENCYID, "=", id);

        return first(where.getWhere());
    }

    public boolean insert(Currency value) {
        return this.insert(value.contentValues) > 0;
    }

    public boolean update(Currency value) {
        int id = value.getCurrencyId();

        WhereStatementGenerator generator = new WhereStatementGenerator();
        String where = generator.getStatement(Currency.CURRENCYID, "=", id);

        return update(value, where);
    }

    public boolean delete(int id) {
        int result = delete(Currency.CURRENCYID + "=?", new String[]{Integer.toString(id)});
        return result > 0;
    }

//    public boolean delete(Currency currency) {
//        delete(currency);
//    }

    public Currency loadCurrency(int currencyId) {
        return loadCurrency(
            Currency.CURRENCYID + "=?",
            new String[]{Integer.toString(currencyId)});
    }

    public Currency loadCurrency(String code) {
        return loadCurrency(
            Currency.CURRENCY_SYMBOL + "=?",
            new String[] { code });
    }

    public int saveExchangeRate(int currencyId, Money exchangeRate) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Currency.BASECONVRATE, exchangeRate.toString());

        int result = getContext().getContentResolver().update(this.getUri(),
            contentValues,
            Currency.CURRENCYID + "=?",
            new String[] { Integer.toString(currencyId) });

        return result;
    }

    // private methods

    private Currency loadCurrency(String selection, String[] selectionArgs) {
        Currency result = null;
        try {
            result = loadCurrencyInternal(selection, selectionArgs);
        } catch (Exception e) {
            Timber.e(e, "loading currency");
        }
        return result;
    }

    private Currency loadCurrencyInternal(String selection, String[] selectionArgs) {
        Currency currency = new Currency();

        Cursor cursor = openCursor(null, selection, selectionArgs);
        if (cursor == null) return null;

        if (cursor.moveToNext()) {
            currency.loadFromCursor(cursor);
        } else {
            currency = null;
        }
        cursor.close();

        return currency;
    }

    public Currency first(String selection) {
        return query(null, selection, null);
    }

    public Currency query(String[] projection, String selection, String[] args) {
        Cursor c = openCursor(projection, selection, args);

        if (c == null) return null;

        Currency account = null;

        if (c.moveToNext()) {
            account = Currency.fromCursor(c);
        }

        c.close();

        return account;
    }
}

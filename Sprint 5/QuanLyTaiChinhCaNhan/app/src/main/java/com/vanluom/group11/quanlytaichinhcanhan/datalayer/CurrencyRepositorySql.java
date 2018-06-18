
package com.vanluom.group11.quanlytaichinhcanhan.datalayer;

import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Currency;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

/**
 * Currency repository with Rx
 */

public class CurrencyRepositorySql
    extends SqlRepositoryBase<Currency> {

    public static final String TABLE_NAME = "currencyformats_v1";

    @Inject
    CurrencyRepositorySql(BriteDatabase db) {
        super(TABLE_NAME, db);
    }

    public boolean exists(String currencyCode) {
        Select query = new Select(Currency.CURRENCYID)
            .from(tableName)
            .where(Currency.CURRENCY_SYMBOL + "=?", currencyCode);

        return super.exists(query);
    }
}

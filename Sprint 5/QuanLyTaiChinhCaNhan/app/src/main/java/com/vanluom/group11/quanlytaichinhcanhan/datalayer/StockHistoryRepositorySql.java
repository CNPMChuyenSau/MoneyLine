
package com.vanluom.group11.quanlytaichinhcanhan.datalayer;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;

import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.StockHistory;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDate;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.Date;

import javax.inject.Inject;

import info.javaperformance.money.Money;
import timber.log.Timber;

/**
 * Sqlite-based repository
 */
public class StockHistoryRepositorySql
    extends SqlRepositoryBase<StockHistory> {

    private static final String TABLE_NAME = "stockhistory_v1";

    @Inject
    public StockHistoryRepositorySql(BriteDatabase db) {
        super(TABLE_NAME, db);

//        application.iocComponent.inject(this);
    }

    public boolean addStockHistoryRecord(String symbol, Money price, Date date) {
        boolean success = false;

        boolean recordExists = recordExists(symbol, date);

        // check whether to insert or update.
        if (!recordExists) {
            ContentValues values = getContentValues(symbol, price, date);
            long id = database.insert(TABLE_NAME, values);

            if (id > 0) {
                // success
                success = true;
            } else {
                Timber.w("Failed inserting stock history record.");
            }
        } else {
            // update
            success = updateHistory(symbol, price, date);
        }

        // todo: notify of changes. sync manager.

        return success;
    }

    public ContentValues getContentValues(String symbol, Money price, Date date) {
        String isoDate = new MmxDate(date).toIsoString();

        ContentValues values = new ContentValues();
        values.put(StockHistory.SYMBOL, symbol);
        values.put(StockHistory.DATE, isoDate);
        values.put(StockHistory.VALUE, price.toString());
        values.put(StockHistory.UPDTYPE, StockHistoryRepository.UpdateType.Online.type);

        return values;
    }

    public boolean recordExists(String symbol, Date date) {
        boolean result;

        String isoDate = new MmxDate(date).toIsoString();

        String sql = new Select()
            .from(TABLE_NAME)
            .where(StockHistory.SYMBOL + "=? AND " + StockHistory.DATE + "=?")
            .toString();

        Cursor cursor = database.query(sql, symbol, isoDate);
        if (cursor == null) return false;

        int records = cursor.getCount();
        result = records > 0;

        cursor.close();

        return result;
    }

    public boolean updateHistory(String symbol, Money price, Date date) {
        boolean result;

        ContentValues values = getContentValues(symbol, price, date);
        String where = StockHistory.SYMBOL + "=?";
        where = DatabaseUtils.concatenateWhere(where, StockHistory.DATE + "=?");
        String[] whereArgs = new String[] { symbol, values.getAsString(StockHistory.DATE) };

        int records = database.update(TABLE_NAME, values, where, whereArgs);

        result = records > 0;

        return result;
    }

}

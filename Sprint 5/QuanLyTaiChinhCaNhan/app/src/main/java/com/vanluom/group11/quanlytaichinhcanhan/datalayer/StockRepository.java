
package com.vanluom.group11.quanlytaichinhcanhan.datalayer;

import android.content.Context;
import android.database.Cursor;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.database.DatasetType;
import com.vanluom.group11.quanlytaichinhcanhan.database.WhereStatementGenerator;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Stock;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDatabaseUtils;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import info.javaperformance.money.Money;

/**
 * Data repository for Stock entities.
 */
public class StockRepository
    extends RepositoryBase<Stock> {

    @Inject
    public StockRepository(Context context) {
        super(context, "stock_v1", DatasetType.TABLE, "stock");

    }

//    @Override
    public String[] getAllColumns() {
        String [] idColumn = new String[] {
                "STOCKID AS _id"
        };

        return ArrayUtils.addAll(idColumn, tableColumns());
    }

    public boolean delete(int id) {
        int result = super.delete(StockFields.STOCKID + "=?", new String[] { Integer.toString(id)});
        return result > 0;
    }

    public String[] tableColumns() {
        Field[] fields = StockFields.class.getFields();
        String[] names = new String[fields.length];

        for(int i = 0; i < fields.length; i++) {
            names[i] = fields[i].getName();
        }

        return names;
    }

    public Stock load(int id) {
        if (id == Constants.NOT_SET) return null;

        return first(Stock.class,
                null,
                StockFields.STOCKID + "=?",
                new String[] { Integer.toString(id) },
                null);
    }

    /**
     * Load multiple items by id.
     * @param ids Array of ids to load.
     * @return List of stocks.
     */
    public List<Stock> load(Integer[] ids) {
        if (ids.length == 0) return null;

        MmxDatabaseUtils dbUtils = new MmxDatabaseUtils(getContext());
        String placeHolders = dbUtils.makePlaceholders(ids.length);
        String[] idParams = new String[ids.length];

        for (int i = 0; i < ids.length; i++) {
            idParams[i] = Integer.toString(ids[i]);
        }

        Cursor c = openCursor(null,
            StockFields.STOCKID + " IN (" + placeHolders + ")",
            idParams,
            null);
        if (c == null) return null;

        List<Stock> result = getEntities(c);

        return result;
    }

    public List<Stock> loadForSymbols(String[] symbols) {
        if (symbols.length == 0) return null;

        MmxDatabaseUtils dbUtils = new MmxDatabaseUtils(getContext());
        String placeHolders = dbUtils.makePlaceholders(symbols.length);

        Cursor c = openCursor(null,
            StockFields.SYMBOL + " IN (" + placeHolders + ")",
            symbols,
            null);
        if (c == null) return null;

        List<Stock> result = getEntities(c);

        return result;
    }

    /**
     * Retrieves all record ids which refer the given symbol.
     * @return array of ids of records which contain the symbol.
     */
    public int[] findIdsBySymbol(String symbol) {
        int[] result;

        Cursor cursor = getContext().getContentResolver().query(this.getUri(),
                new String[]{ StockFields.STOCKID },
                StockFields.SYMBOL + "=?", new String[]{symbol},
                null);
        if (cursor == null) return null;

        int records = cursor.getCount();
        result = new int[records];

        for (int i = 0; i < records; i++) {
            cursor.moveToNext();
            result[i] = cursor.getInt(cursor.getColumnIndex(StockFields.STOCKID));
        }
        cursor.close();

        return result;
    }

    public boolean insert(Stock stock) {
        return insert(stock.contentValues) > 0;
    }

    public boolean save(Stock stock) {
        int id = stock.getId();

        WhereStatementGenerator generator = new WhereStatementGenerator();
        String where = generator.getStatement(StockFields.STOCKID, "=", id);

        return update(stock, where);
    }

    /**
     * Update price for all the records with this symbol.
     * @param symbol Stock symbol
     * @param price Stock price
     */
    public void updateCurrentPrice(String symbol, Money price) {
        int[] ids = findIdsBySymbol(symbol);

        // recalculate value

        for (int id : ids) {
            Stock stock = load(id);
            stock.setCurrentPrice(price);
            // recalculate & assign the value
            Money value = stock.getValue();

            save(stock);
        }
    }

    private List<Stock> getEntities(Cursor c) {
        List<Stock> result = new ArrayList<>();
        while (c.moveToNext()) {
            result.add(Stock.from(c));
        }
        c.close();

        return result;
    }
}

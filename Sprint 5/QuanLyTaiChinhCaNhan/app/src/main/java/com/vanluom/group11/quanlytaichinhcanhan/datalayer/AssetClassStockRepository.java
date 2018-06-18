package com.vanluom.group11.quanlytaichinhcanhan.datalayer;

import android.content.Context;
import android.database.Cursor;

import com.vanluom.group11.quanlytaichinhcanhan.database.DatasetType;
import com.vanluom.group11.quanlytaichinhcanhan.database.WhereStatementGenerator;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.AssetClassStock;

import java.util.ArrayList;
import java.util.List;

/**
 * AssetClass / Stock link repository
 */
public class AssetClassStockRepository
    extends RepositoryBase {

    public AssetClassStockRepository(Context context) {
        super(context, "assetclass_stock_v1", DatasetType.TABLE, "assetclassstock");

    }

    @Override
    public String[] getAllColumns() {
        return new String[] {AssetClassStock.ID + " AS _id", AssetClassStock.ID,
            AssetClassStock.ASSETCLASSID, AssetClassStock.STOCKSYMBOL };
    }

    public boolean insert(AssetClassStock value) {
        int id = this.insert(value.contentValues);
        value.setId(id);

        return id > 0;
    }

    public boolean delete(String stockSymbol) {
        WhereStatementGenerator where = new WhereStatementGenerator();
        where.addStatement(AssetClassStock.STOCKSYMBOL, "=", stockSymbol);

        return this.delete(where.getWhere(), null) > 0;
    }

    public boolean delete(int assetClassId, String stockSymbol) {
        WhereStatementGenerator where = new WhereStatementGenerator();
        where.addStatement(AssetClassStock.ASSETCLASSID, "=", assetClassId);
        where.addStatement(AssetClassStock.STOCKSYMBOL, "=", stockSymbol);

        return this.delete(where.getWhere(), null) > 0;
    }

    public boolean deleteAllForAssetClass(int assetClassId) {
        WhereStatementGenerator where = new WhereStatementGenerator();
        where.addStatement(AssetClassStock.ASSETCLASSID, "=", assetClassId);

        return this.delete(where.getWhere(), null) >= 0;
    }

    public List<AssetClassStock> loadForClass(int assetClassId) {
        WhereStatementGenerator where = new WhereStatementGenerator();
        String selection = where.getStatement(AssetClassStock.ASSETCLASSID, "=", assetClassId);

        Cursor c = openCursor(null, selection, null);
        if (c == null) return null;

        List<AssetClassStock> result = new ArrayList<>();

        while (c.moveToNext()) {
            AssetClassStock entity = AssetClassStock.create(0, "");
            entity.loadFromCursor(c);
            result.add(entity);
        }

        c.close();

        return result;
    }

//    /**
//     * Retrieves the cursor for the list of linked securities to the given asset class.
//     * @param assetClassId Id of the asset class for which to load the data.
//     * @return Cursor on the list of linked securities.
//     */
//    public Cursor fetchCursorAssignedSecurities(int assetClassId) {
//        AssetClassStockRepository repo = new AssetClassStockRepository(getContext());
//
//        String sql =
//            "SELECT acs.ID as _id, s.* " +
//            "FROM " + repo.getSource() + " AS acs " +
//            "INNER JOIN STOCK_V1 AS s ON acs." + AssetClassStock.STOCKSYMBOL + " = s.symbol " +
//            "WHERE acs." + AssetClassStock.ASSETCLASSID + "=?";
//        String[] args = new String[] { Integer.toString(assetClassId)};
//
//        Cursor c = MmxOpenHelper.getInstance(getContext()).getReadableDatabase()
//            .rawQuery(sql, args);
//
////        c.close();
//        return c;
//    }
}

package com.vanluom.group11.quanlytaichinhcanhan.datalayer;

import android.content.Context;
import android.database.Cursor;

import com.vanluom.group11.quanlytaichinhcanhan.database.DatasetType;
import com.vanluom.group11.quanlytaichinhcanhan.database.ISplitTransaction;
import com.vanluom.group11.quanlytaichinhcanhan.database.WhereStatementGenerator;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.SplitCategory;

import java.util.ArrayList;

/**
 * Repository for Split Categories (TableSplitTransaction).
 */
public class SplitCategoriesRepository
    extends RepositoryBase
    implements IRepository {

    public SplitCategoriesRepository(Context context) {
        super(context, SplitCategory.TABLE_NAME, DatasetType.TABLE, "splittransaction");
    }

    @Override
    public String[] getAllColumns() {
        return new String[] {"SPLITTRANSID AS _id",
            SplitCategory.SPLITTRANSID,
            SplitCategory.TRANSID,
            SplitCategory.CATEGID,
            SplitCategory.SUBCATEGID,
            SplitCategory.SPLITTRANSAMOUNT };
    }

    /**
     * Loads split transactions for the given transaction id.
     * @param transId Id of the main transaction for which to load the splits.
     * @return list of split categories for the given transaction.
     */
    public ArrayList<ISplitTransaction> loadSplitCategoriesFor(int transId) {
        Cursor curSplit = getContext().getContentResolver().query(getUri(), null,
            SplitCategory.TRANSID + "=" + Integer.toString(transId),
            null,
            SplitCategory.SPLITTRANSID);
        if (curSplit == null) return null;

        ArrayList<ISplitTransaction> listSplitTrans = new ArrayList<>();

        while (curSplit.moveToNext()) {
            SplitCategory splitCategory = new SplitCategory();
            splitCategory.loadFromCursor(curSplit);

            listSplitTrans.add(splitCategory);
        }
        curSplit.close();

        return listSplitTrans;
    }

    public boolean insert(SplitCategory item) {
        // Remove any existing id value.
        item.contentValues.remove(SplitCategory.SPLITTRANSID);

        int id = this.insert(item.contentValues);
        item.setId(id);

        return id > 0;
    }

    public boolean update(SplitCategory entity) {
        WhereStatementGenerator where = new WhereStatementGenerator();
        where.addStatement(SplitCategory.SPLITTRANSID, "=", entity.getId());

        return update(entity, where.getWhere());
    }

    public boolean delete(ISplitTransaction entity) {
        int deleted = super.delete(SplitCategory.SPLITTRANSID + "=?",
                new String[]{ Integer.toString(entity.getId()) });
        return deleted == 1;
    }

    public boolean delete(IEntity entity) {
        return delete((ISplitTransaction) entity);
    }
}

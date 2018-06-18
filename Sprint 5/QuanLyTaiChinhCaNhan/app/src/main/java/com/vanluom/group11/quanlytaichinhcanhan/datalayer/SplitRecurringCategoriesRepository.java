package com.vanluom.group11.quanlytaichinhcanhan.datalayer;

import android.content.Context;
import android.database.Cursor;

import com.vanluom.group11.quanlytaichinhcanhan.database.DatasetType;
import com.vanluom.group11.quanlytaichinhcanhan.database.ISplitTransaction;
import com.vanluom.group11.quanlytaichinhcanhan.database.WhereStatementGenerator;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.SplitRecurringCategory;

import java.util.ArrayList;

/**
 * Repository for Recurring Split Categories (TableBudgetSplitTransactions).
 */
public class SplitRecurringCategoriesRepository
    extends RepositoryBase
    implements IRepository {

    public SplitRecurringCategoriesRepository(Context context) {
        super(context, SplitRecurringCategory.TABLE_NAME, DatasetType.TABLE, "budgetsplittransactions");
    }

    @Override
    public String[] getAllColumns() {
        return new String[] {"SPLITTRANSID AS _id",
            SplitRecurringCategory.SPLITTRANSID,
            SplitRecurringCategory.TRANSID,
            SplitRecurringCategory.CATEGID,
            SplitRecurringCategory.SUBCATEGID,
            SplitRecurringCategory.SPLITTRANSAMOUNT };
    }

    /**
     * Loads split transactions for the given transaction id.
     * @param transId Id of the main transaction for which to load the splits.
     * @return list of split categories for the given transaction.
     */
    public ArrayList<ISplitTransaction> loadSplitCategoriesFor(int transId) {
        Cursor curSplit = getContext().getContentResolver().query(getUri(), null,
            SplitRecurringCategory.TRANSID + "=" + Integer.toString(transId),
            null,
            SplitRecurringCategory.SPLITTRANSID);
        if (curSplit == null) return null;

        ArrayList<ISplitTransaction> listSplitTrans = new ArrayList<>();

        while (curSplit.moveToNext()) {
            SplitRecurringCategory splitRecurringCategory = new SplitRecurringCategory();
            splitRecurringCategory.loadFromCursor(curSplit);

            listSplitTrans.add(splitRecurringCategory);
        }
        curSplit.close();

        return listSplitTrans;
    }

    public boolean insert(SplitRecurringCategory item) {
        // Remove any existing id value.
        item.contentValues.remove(SplitRecurringCategory.SPLITTRANSID);

        int id = this.insert(item.contentValues);
        item.setId(id);

        return id > 0;
    }

    public boolean update(SplitRecurringCategory entity) {
        WhereStatementGenerator where = new WhereStatementGenerator();
        where.addStatement(SplitRecurringCategory.SPLITTRANSID, "=", entity.getId());

        return update(entity, where.getWhere());
    }

    public boolean delete (int id) {
        int deleted = super.delete(SplitRecurringCategory.SPLITTRANSID + "=?",
                new String[]{ Integer.toString(id) });

        return deleted == 1;
    }

    public boolean delete(ISplitTransaction entity) {
        return delete(entity.getId());
    }

    public boolean delete(IEntity entity) {
        return delete((ISplitTransaction) entity);
    }
}

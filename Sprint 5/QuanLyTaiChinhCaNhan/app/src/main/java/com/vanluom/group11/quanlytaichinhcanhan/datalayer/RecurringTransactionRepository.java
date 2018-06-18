
package com.vanluom.group11.quanlytaichinhcanhan.datalayer;

import android.content.Context;
import android.database.Cursor;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.database.DatasetType;
import com.vanluom.group11.quanlytaichinhcanhan.database.ITransactionEntity;
import com.vanluom.group11.quanlytaichinhcanhan.database.WhereStatementGenerator;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.RecurringTransaction;

/**
 * Recurring transaction repository.
 */
public class RecurringTransactionRepository
    extends RepositoryBase<RecurringTransaction>{

    public RecurringTransactionRepository(Context context) {
        super(context, "billsdeposits_v1", DatasetType.TABLE, "billsdeposits");
    }

    @Override
    public String[] getAllColumns() {
        return new String [] { RecurringTransaction.BDID + " AS _id", RecurringTransaction.BDID,
                ITransactionEntity.ACCOUNTID,
                ITransactionEntity.TOACCOUNTID,
                ITransactionEntity.PAYEEID,
                ITransactionEntity.TRANSCODE,
                ITransactionEntity.TRANSAMOUNT,
                ITransactionEntity.STATUS,
                ITransactionEntity.TRANSACTIONNUMBER,
                ITransactionEntity.NOTES,
                ITransactionEntity.CATEGID,
                ITransactionEntity.SUBCATEGID,
                ITransactionEntity.TRANSDATE,
                ITransactionEntity.FOLLOWUPID,
                ITransactionEntity.TOTRANSAMOUNT,
                RecurringTransaction.REPEATS,
                RecurringTransaction.NEXTOCCURRENCEDATE,
                RecurringTransaction.NUMOCCURRENCES};
    }

    public int delete(int id) {
        return super.delete(RecurringTransaction.BDID + "=?", new String[] { Integer.toString(id)});
    }

    public RecurringTransaction load(int id) {
        if (id == Constants.NOT_SET) return null;

        WhereStatementGenerator where = new WhereStatementGenerator();
        where.addStatement(RecurringTransaction.BDID, "=", id);

        RecurringTransaction tx = first(null, where.getWhere(), null);

        return tx;
    }

    public RecurringTransaction first(String[] projection, String selection, String[] args) {
        Cursor c = openCursor(projection, selection, args);

        if (c == null) return null;

        RecurringTransaction entity = null;

        if (c.moveToNext()) {
            entity = new RecurringTransaction();
            entity.loadFromCursor(c);
        }

        c.close();

        return entity;
    }

    public RecurringTransaction insert(RecurringTransaction entity) {
        entity.contentValues.remove(RecurringTransaction.BDID);

        int id = insert(entity.contentValues);

        entity.setId(id);

        return entity;
    }

    public boolean update(RecurringTransaction value) {
        int id = value.getId();

        WhereStatementGenerator generator = new WhereStatementGenerator();
        String where = generator.getStatement(RecurringTransaction.BDID, "=", id);

        return update(value, where);
    }

}

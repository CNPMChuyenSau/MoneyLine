
package com.vanluom.group11.quanlytaichinhcanhan.datalayer;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.database.DatasetType;
import com.vanluom.group11.quanlytaichinhcanhan.database.WhereStatementGenerator;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Budget;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDatabaseUtils;

/**
 * Budget repository.
 */
public class BudgetRepository
    extends RepositoryBase<Budget> {

    public static final String TABLE_NAME = "budgetyear_v1";

    public BudgetRepository(Context context) {
        super(context, TABLE_NAME, DatasetType.TABLE, "budgetyear");

    }

    @Override
    public String[] getAllColumns() {
        return new String[] {"BUDGETYEARID AS _id", Budget.BUDGETYEARID, Budget.BUDGETYEARNAME};
    }

    public boolean delete(int id) {
        int deleted = super.delete(Budget.BUDGETYEARID + "=?", MmxDatabaseUtils.getArgsForId(id));
        return deleted > 0;
    }

    public Budget load(int id) {
        if (id == Constants.NOT_SET) return null;

        WhereStatementGenerator where = new WhereStatementGenerator();
        where.addStatement(Budget.BUDGETYEARID, "=", id);

        Budget result = super.first(Budget.class,
                null,
                where.getWhere(),
                null,
                null);
        return result;
    }

    public boolean save(Budget entity) {
        boolean result;

        if (entity.getId() == null || entity.getId() == Constants.NOT_SET) {
            // remove any existing id value
            entity.contentValues.remove(Budget.BUDGETYEARID);

            // new record
            int id = super.insert(entity.contentValues);
            result = id != 0;
        } else {
            result = super.update(entity, Budget.BUDGETYEARID + "=?",
                    MmxDatabaseUtils.getArgsForId(entity.getId()));
        }
        return result;
    }
}

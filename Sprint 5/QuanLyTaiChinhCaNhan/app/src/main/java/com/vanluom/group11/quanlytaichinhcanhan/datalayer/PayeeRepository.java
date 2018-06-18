package com.vanluom.group11.quanlytaichinhcanhan.datalayer;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.database.DatasetType;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Payee;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDatabaseUtils;

/**
 * Payee repository
 */
public class PayeeRepository
    extends RepositoryBase{

    public PayeeRepository(Context context) {
        super(context, "payee_v1", DatasetType.TABLE, "payee");

    }

    @Override
    public String[] getAllColumns() {
        return new String[] { "PAYEEID AS _id",
            Payee.PAYEEID,
            Payee.PAYEENAME,
            Payee.CATEGID,
            Payee.SUBCATEGID
        };
    }

    public int add(Payee entity) {
        return insert(entity.contentValues);
    }

    public boolean delete(int id) {
        if (id == Constants.NOT_SET) return false;

        int result = delete(Payee.PAYEEID + "=?", MmxDatabaseUtils.getArgsForId(id));
        return result > 0;
    }

    public Payee load(Integer id) {
        if (id == null || id == Constants.NOT_SET) return null;

        Payee payee = (Payee) super.first(Payee.class,
                getAllColumns(),
                Payee.PAYEEID + "=?", MmxDatabaseUtils.getArgsForId(id),
                null);

        return payee;
    }

    public boolean save(Payee payee) {
        int id = payee.getId();
        return super.update(payee, Payee.PAYEEID + "=" + id);
    }
}

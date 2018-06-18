package com.vanluom.group11.quanlytaichinhcanhan.datalayer;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.database.DatasetType;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Subcategory;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDatabaseUtils;

/**
 * A repository for Subcategories.
 */
public class SubcategoryRepository
    extends RepositoryBase {

    public static final String tableName = "subcategory_v1";

    public SubcategoryRepository(Context context) {
        super(context, tableName, DatasetType.TABLE, "subcategory");
    }

    @Override
    public String[] getAllColumns() {
        return new String[] { "SUBCATEGID AS _id",
            Subcategory.SUBCATEGID,
            Subcategory.SUBCATEGNAME,
            Subcategory.CATEGID
        };
    }

    public Subcategory load(int id) {
        if (id == Constants.NOT_SET) return null;

        Subcategory subcategory = (Subcategory) first(Subcategory.class,
                getAllColumns(),
                Subcategory.SUBCATEGID + "=?", MmxDatabaseUtils.getArgsForId(id),
                null);

        return subcategory;
    }
}

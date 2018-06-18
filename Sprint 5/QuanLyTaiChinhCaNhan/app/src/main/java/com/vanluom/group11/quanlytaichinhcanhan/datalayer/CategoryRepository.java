package com.vanluom.group11.quanlytaichinhcanhan.datalayer;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.database.DatasetType;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Category;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDatabaseUtils;

/**
 * A repository for Categories.
 */
public class CategoryRepository
    extends RepositoryBase<Category>{

    public static final String tableName = "category_v1";

    public CategoryRepository(Context context) {
        super(context, tableName, DatasetType.TABLE, "category");
    }

    @Override
    public String[] getAllColumns() {
        return new String[] {"CATEGID AS _id",
            Category.CATEGID,
            Category.CATEGNAME};
    }

    public Category load(int id) {
        if (id == Constants.NOT_SET) return null;

        Category category = (Category) first(Category.class,
                getAllColumns(),
                Category.CATEGID + "=?",
                MmxDatabaseUtils.getArgsForId(id),
                null);

        return category;
    }

    public int loadIdByName(String name) {
        Category temp = first(Category.class,
                new String[] { Category.CATEGID },
                Category.CATEGNAME + "=?",
                new String[] { name },
                null);

        if (temp == null) return Constants.NOT_SET;

        return temp.getId();
    }
}

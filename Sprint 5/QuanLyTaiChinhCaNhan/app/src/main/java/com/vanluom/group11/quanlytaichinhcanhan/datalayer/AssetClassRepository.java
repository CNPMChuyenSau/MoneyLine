package com.vanluom.group11.quanlytaichinhcanhan.datalayer;

import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.vanluom.group11.quanlytaichinhcanhan.database.DatasetType;
import com.vanluom.group11.quanlytaichinhcanhan.database.WhereStatementGenerator;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.AssetClass;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.EntityBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for Asset Classes.
 */
public class AssetClassRepository
    extends RepositoryBase {

    public AssetClassRepository(Context context) {
        super(context, "assetclass_v1", DatasetType.TABLE, "assetclass");

    }

    @Override
    public String[] getAllColumns() {
        return new String[] {AssetClass.ID + " AS _id",
            AssetClass.ID,
            AssetClass.PARENTID,
            AssetClass.NAME,
            AssetClass.ALLOCATION,
            AssetClass.SORTORDER
        };
    }

    public AssetClass load(int id) {
        WhereStatementGenerator where = new WhereStatementGenerator();
        where.addStatement(AssetClass.ID, "=", id);

        return first(where.getWhere());
    }

    /**
     * Loads ids for all child records.
     * @param id Id of the parent item.
     * @return List of ids of the child asset classes.
     */
    public List<Integer> loadAllChildrenIds(int id) {
        WhereStatementGenerator where = new WhereStatementGenerator();
        where.addStatement(AssetClass.PARENTID, "=", id);

        String[] fields = new String[] { AssetClass.ID };

        Select query = new Select(fields)
                .where(where.getWhere());
        List<AssetClass> children = query(AssetClass.class, query);

        List<Integer> result = new ArrayList<>();
        for (AssetClass item : children) {
            result.add(item.getId());
        }

        return result;
    }

    public AssetClass first(String selection) {
        return (AssetClass) first(AssetClass.class, null, selection, null, null);
    }

    public boolean insert(AssetClass value) {
        int id = this.insert(value.contentValues);
        value.setId(id);

        return id > 0;
    }

    public boolean bulkInsert(List<AssetClass> entities) {
//        List<ContentValues> contentValues = Queryable.from(entities)
//            .map(new Converter<AssetClass, ContentValues>() {
//                @Override
//                public ContentValues convert(AssetClass element) {
//                    return element.contentValues;
//                }
//            })
//            .toList();

        List<ContentValues> contentValues = new ArrayList<>();
        for (AssetClass entity : entities) {
            contentValues.add(entity.contentValues);
        }

        ContentValues[] values = new ContentValues[entities.size()];

        contentValues.toArray(values);

        int records = bulkInsert(values);
        return records == entities.size();
    }

    public boolean update(AssetClass value) {
        int id = value.getId();

        WhereStatementGenerator generator = new WhereStatementGenerator();
        String where = generator.getStatement(AssetClass.ID, "=", id);

        return update(value, where);
    }

    public boolean bulkUpdate(List<AssetClass> entities) {
        EntityBase[] values = new EntityBase[entities.size()];
        entities.toArray(values);

        ContentProviderResult[] results = bulkUpdate(values);
        return results.length == entities.size();
    }

    public boolean delete(int id) {
        int result = delete(AssetClass.ID + "=?", new String[]{Integer.toString(id)});
        return result > 0;
    }

    public boolean deleteAll(List<Integer> ids) {
        if (ids.size() == 0) return true;

        ContentProviderResult[] results = bulkDelete(ids);

        for (ContentProviderResult result : results) {
            Log.d("test", result.toString());
        }

        return true;
    }
}

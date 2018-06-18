
package com.vanluom.group11.quanlytaichinhcanhan.datalayer;

import android.content.ContentValues;
import android.database.Cursor;

import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.EntityBase;
import com.vanluom.group11.quanlytaichinhcanhan.sync.SyncManager;
import com.squareup.sqlbrite.BriteDatabase;

import timber.log.Timber;

/**
 * Repository base for SQLite-based data access.
 * T is the entity type (class).
 */

abstract class SqlRepositoryBase<T extends EntityBase> {
    SqlRepositoryBase(String tableName, BriteDatabase db) {
        this.tableName = tableName;
        this.database = db;
    }

    public BriteDatabase database;
    public String tableName;

    public long insert(ContentValues values) {
        return database.insert(tableName, values);
    }

    public int delete(String where, String... whereArgs) {
        int result = database.delete(tableName, where, whereArgs);

        notifySync();

        return result;
    }

    public boolean exists(Select query) {
        Cursor c = database.query(query.toString(), query.selectionArgs);
        if (c == null) return false;

        boolean result = c.getCount() > 0;

        c.close();

        return result;
    }

    public T first(Class<T> resultType, String[] projection, String selection, String[] args, String sort) {
        T entity = null;

        String sql = new Select(projection)
            .from(tableName)
            .where(selection)
            .orderBy(sort)
            .toString();

        try {
            Cursor c = database.query(sql, args);
            if (c == null) return null;

            if (c.moveToNext()) {
                try {
                    entity = resultType.newInstance();
                    //resultType.cast(entity);
                    entity.loadFromCursor(c);
                } catch (Exception e) {
                    Timber.e(e, "creating %s", resultType.getName());
                }
            }
            c.close();
        } catch (Exception ex) {
            Timber.e(ex, "fetching first record");
        }

        return entity;
    }

    public Cursor query(Select query) {
        return database.query(query.toString(), query.selectionArgs);
    }

    protected boolean update(EntityBase entity, String where, String... selectionArgs) {
        boolean result = false;

        ContentValues values = entity.contentValues;
        // remove "_id" from the values.
        values.remove("_id");

        int updateResult = database.update(tableName,
                values,
                where,
                selectionArgs
        );

        if (updateResult != 0) {
            notifySync();

            result = true;
        } else {
            Timber.w("update failed, %s, values: %s", tableName, entity.contentValues);
        }

        return result;
    }

    /**
     * Notify sync engine about the database update.
     */
    private void notifySync() {
        new SyncManager(MoneyManagerApplication.getApp()).dataChanged();
    }
}

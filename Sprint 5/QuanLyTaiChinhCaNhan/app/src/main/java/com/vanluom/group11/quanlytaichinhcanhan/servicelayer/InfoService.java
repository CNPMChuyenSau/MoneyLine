
package com.vanluom.group11.quanlytaichinhcanhan.servicelayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
//import net.sqlcipher.database.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase;

import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.InfoRepositorySql;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.Select;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Info;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Access and manipulation of the info in the Info Table
 */
public class InfoService
    extends ServiceBase {

    public InfoService(Context context) {
        super(context);

        MoneyManagerApplication.getApp().iocComponent.inject(this);
    }

    @Inject
    public InfoRepositorySql repository;

    public long insertRaw(SQLiteDatabase db, String key, Integer value) {
        ContentValues values = new ContentValues();

        values.put(Info.INFONAME, key);
        values.put(Info.INFOVALUE, value);

        return db.insert(InfoRepositorySql.TABLE_NAME, null, values);
    }

    public long insertRaw(SQLiteDatabase db, String key, String value) {
        ContentValues values = new ContentValues();

        values.put(Info.INFONAME, key);
        values.put(Info.INFOVALUE, value);

        return db.insert(InfoRepositorySql.TABLE_NAME, null, values);
    }

    /**
     * Update the values via direct access to the database.
     * @param db        Database to use
     * @param recordId  Id of the info record. Required for the update statement.
     * @param key       Info Name
     * @param value     Info Value
     * @return the number of rows affected
     */
    public long updateRaw(SQLiteDatabase db, int recordId, String key, Integer value) {
        ContentValues values = new ContentValues();
        values.put(Info.INFONAME, key);
        values.put(Info.INFOVALUE, value);

        return db.update(InfoRepositorySql.TABLE_NAME,
                values,
            Info.INFOID + "=?",
                new String[] { Integer.toString(recordId)}
        );
    }

    public long updateRaw(SQLiteDatabase db, String key, String value) {
        ContentValues values = new ContentValues();
        values.put(Info.INFONAME, key);
        values.put(Info.INFOVALUE, value);

        return db.update(InfoRepositorySql.TABLE_NAME, values,
            Info.INFONAME + "=?",
                new String[] { key });
    }

    /**
     * Retrieve value of info
     * @param info to be retrieve
     * @return value
     */
    public String getInfoValue(String info) {
        Cursor cursor;
        String ret = null;

        try {
            Select query = new Select()
                    .from(InfoRepositorySql.TABLE_NAME)
                    .where(Info.INFONAME + "=?", info);
            cursor = repository.query(query);
            if (cursor == null) return null;

            if (cursor.moveToFirst()) {
                ret = cursor.getString(cursor.getColumnIndex(Info.INFOVALUE));
            }
            cursor.close();
        } catch (Exception e) {
            Timber.e(e, "retrieving info value: %s", info);
        }

        return ret;
    }

    /**
     * Update value of info.
     * @param key to update
     * @param value value to be used
     * @return true if update success otherwise false
     */
    public boolean setInfoValue(String key, String value) {
        boolean result = false;
        // check if info exists
        boolean exists = (getInfoValue(key) != null);

        Info entity = Info.create(key, value);

        try {
            if (exists) {
                result = repository.update(entity);
            } else {
                long id = repository.insert(entity);
                result = id > 0;
            }
        } catch (Exception e) {
            Timber.e(e, "writing info value");
        }

        return result;
    }
}

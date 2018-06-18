package com.vanluom.group11.quanlytaichinhcanhan.domainmodel;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Parcel;

import java.util.Date;

/**
 * A stock history record.
 */
public class StockHistory
    extends EntityBase {

    public static final String HISTID = "HISTID";
    public static final String SYMBOL = "SYMBOL";
    public static final String DATE = "DATE";
    public static final String VALUE = "VALUE";
    public static final String UPDTYPE = "UPDTYPE";

    public StockHistory() {
        super();
    }

    @Override
    public void loadFromCursor(Cursor c) {
        super.loadFromCursor(c);

        // Reload all money values.
        DatabaseUtils.cursorDoubleToCursorValues(c, VALUE, this.contentValues);
    }

    protected StockHistory(Parcel in) {
        contentValues = in.readParcelable(ContentValues.class.getClassLoader());
    }

    public int getHistId() {
        return getInt(HISTID);
    }

    public Date getDate() {
        return getDate(DATE);
    }
}

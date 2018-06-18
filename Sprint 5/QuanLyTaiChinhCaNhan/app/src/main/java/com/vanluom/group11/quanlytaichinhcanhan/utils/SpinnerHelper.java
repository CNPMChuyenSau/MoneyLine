
package com.vanluom.group11.quanlytaichinhcanhan.utils;

import android.database.Cursor;

/**
 * Useful functions for working with Spinners (dropdown lists).
 */
public class SpinnerHelper {
    public static int getPosition(String displayText, String fieldName, Cursor cursor) {
        int position = -1;
        cursor.moveToFirst();

        while(cursor.moveToNext()) {
            String text = cursor.getString(cursor.getColumnIndex(fieldName));
            if (text.equals(displayText)) {
                position = cursor.getPosition();
                break;
            }
        }

        return position;
    }
}

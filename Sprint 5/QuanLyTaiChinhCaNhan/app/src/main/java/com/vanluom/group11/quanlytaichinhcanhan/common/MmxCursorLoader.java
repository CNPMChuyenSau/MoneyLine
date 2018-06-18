
package com.vanluom.group11.quanlytaichinhcanhan.common;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

import com.vanluom.group11.quanlytaichinhcanhan.datalayer.Select;

import timber.log.Timber;

/**
 * The cursor loader with exception handling. It should be used instead of ordinary CursorLoader.
 */
public class MmxCursorLoader
    extends CursorLoader {

    public MmxCursorLoader(Context context) {
        super(context);
    }

    public MmxCursorLoader(Context context, Uri uri, Select query) {
        // String[] projection, String selection, String[] selectionArgs, String sortOrder
        super(context, uri, query.projection, query.selection, query.selectionArgs, query.sort);

    }

    @Override
    public Cursor loadInBackground() {
        try {
            return super.loadInBackground();
        } catch (Exception e) {
            Timber.e(e, "loading data");
        }
        return null;
    }
}

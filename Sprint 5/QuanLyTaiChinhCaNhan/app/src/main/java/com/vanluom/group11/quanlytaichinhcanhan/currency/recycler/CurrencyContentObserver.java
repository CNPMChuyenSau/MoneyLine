
package com.vanluom.group11.quanlytaichinhcanhan.currency.recycler;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

/**
 * Content Observer for data changes in Currencies.
 */
public class CurrencyContentObserver
    extends ContentObserver {
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public CurrencyContentObserver(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange) {
        this.onChange(selfChange, null);
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        // todo: reload data
        // depending on the handler you might be on the UI
        // thread, so be cautious!
        Log.d("test", "test");
    }
}

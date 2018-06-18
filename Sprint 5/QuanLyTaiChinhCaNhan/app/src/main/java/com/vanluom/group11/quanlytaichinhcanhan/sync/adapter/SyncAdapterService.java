
package com.vanluom.group11.quanlytaichinhcanhan.sync.adapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Links the sync adapter with Android sync framework.
 * Define a Service that returns an IBinder for the
 * sync adapter class, allowing the sync adapter framework to call
 * onPerformSync().
 */
public class SyncAdapterService
    extends Service {

    // The authority for the sync adapter's content provider
    public static final String AUTHORITY = "com.money.manager.ex.sync.adapter";
    // An account type, in the form of a domain name
    public static final String ACCOUNT_TYPE = "com.money.manager.ex.sync";
    // The account name
    public static final String ACCOUNT = "Synchronization";

    // Storage for an instance of the sync adapter
    private static SyncAdapter sSyncAdapter = null;
    // Object to use as a thread-safe lock
    private static final Object sSyncAdapterLock = new Object();

    /*
     * Instantiate the sync adapter object.
     */
    @Override
    public void onCreate() {
        /*
         * Create the sync adapter as a singleton.
         * Set the sync adapter as syncable
         * Disallow parallel syncs
         */
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    /**
     * Return an object that allows the system to invoke
     * the sync adapter.
     */
    @Override
    public IBinder onBind(Intent intent) {
        /*
         * Get the object that allows external processes
         * to call onPerformSync(). The object is created
         * in the base class code when the SyncAdapter
         * constructors call super()
         */
        return sSyncAdapter.getSyncAdapterBinder();
    }
}
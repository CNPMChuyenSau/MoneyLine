
package com.vanluom.group11.quanlytaichinhcanhan.sync.adapter;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
//import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

import timber.log.Timber;

/**
 * Handle the transfer of data between a server and an
 * app, using the Android sync adapter framework.
 * https://developer.android.com/training/sync-adapters/creating-sync-adapter.html
 */
public class SyncAdapter
    extends AbstractThreadedSyncAdapter {

    // Global variables
    // Define a variable to contain a content resolver instance
//    ContentResolver mContentResolver;

    /**
     * Set up the sync adapter
     */
    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
//        mContentResolver = context.getContentResolver();
    }

    /**
     * Set up the sync adapter. This form of the
     * constructor maintains compatibility with Android 3.0
     * and later platform versions
     */
    public SyncAdapter(
            Context context,
            boolean autoInitialize,
            boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
//        mContentResolver = context.getContentResolver();

    }

    /**
     * Transfer code goes here.
     * @param account An Account object associated with the event that triggered the sync adapter. If your server doesn't use accounts, you don't need to use the information in this object.
     * @param bundle A Bundle containing flags sent by the event that triggered the sync adapter.
     * @param s The authority of a content provider in the system. Your app has to have access to this provider. Usually, the authority corresponds to a content provider in your own app.
     * @param contentProviderClient A ContentProviderClient for the content provider pointed to by the authority argument.
     *                              A ContentProviderClient is a lightweight public interface to a content provider.
     *                              It has the same basic functionality as a ContentResolver.
     *                              If you're using a content provider to store data for your app, you can connect to the provider with this object. Otherwise, you can ignore it.
     * @param syncResult
     */
    @Override
    public void onPerformSync(Account account, Bundle bundle, String s,
                              ContentProviderClient contentProviderClient, SyncResult syncResult) {
        Timber.d("synchronizing!");

        // todo copy the logic from the current SyncService.
    }
}
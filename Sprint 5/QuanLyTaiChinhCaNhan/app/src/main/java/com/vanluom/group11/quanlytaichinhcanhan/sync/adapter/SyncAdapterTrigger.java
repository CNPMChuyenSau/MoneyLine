
package com.vanluom.group11.quanlytaichinhcanhan.sync.adapter;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;

import timber.log.Timber;

import static android.content.Context.ACCOUNT_SERVICE;
import static com.vanluom.group11.quanlytaichinhcanhan.sync.adapter.SyncAdapterService.ACCOUNT;
import static com.vanluom.group11.quanlytaichinhcanhan.sync.adapter.SyncAdapterService.ACCOUNT_TYPE;
import static com.vanluom.group11.quanlytaichinhcanhan.sync.adapter.SyncAdapterService.AUTHORITY;

/**
 * Here is the code that can be used to invoke the sync adapter synchronization from within the app.
 * 
 *         // todo enable for sync. Use perhaps on sync preferences.
 // create the sync account
 //        CreateSyncAccount(this);
 //        new SyncAdapterTrigger().runSync();
 */

public class SyncAdapterTrigger {

    /**
     * Create a new dummy account for the sync adapter
     *
     * @param context The application context
     */
    public static Account CreateSyncAccount(Context context) {
        // Create the account type and default account
        Account newAccount = new Account(ACCOUNT, ACCOUNT_TYPE);
        // Get an instance of the Android account manager
        AccountManager accountManager = (AccountManager) context.getSystemService(ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
        } else {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
            Timber.d("can't add account for sync!");
        }
        return newAccount;
    }

    // Sync interval constants
//    public static final long SECONDS_PER_MINUTE = 60L;
//    public static final long SYNC_INTERVAL_IN_MINUTES = 60L;
//    public static final long SYNC_INTERVAL = SYNC_INTERVAL_IN_MINUTES * SECONDS_PER_MINUTE;

    /**
     *
     * @param interval Interval in miliseconds to use between runs. Minutes * seconds;
     * Ref: https://developer.android.com/reference/android/content/ContentResolver.html#addPeriodicSync(android.accounts.Account,%20java.lang.String,%20android.os.Bundle,%20long)
     */
    public void schedulePeriodicSync(long interval) {
        Account account = new Account(ACCOUNT, ACCOUNT_TYPE);

        /*
         * Turn on periodic syncing
         */
        ContentResolver.addPeriodicSync(
                account,
                AUTHORITY,
                Bundle.EMPTY,
                interval);

    }

    public void runSync() {
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);

        Account account = new Account(ACCOUNT, ACCOUNT_TYPE);

        /*
         * Request the sync for the default account, authority, and
         * manual sync preferences
         */
        ContentResolver.requestSync(account, AUTHORITY, settingsBundle);

    }
}

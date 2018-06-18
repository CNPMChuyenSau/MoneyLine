
package com.vanluom.group11.quanlytaichinhcanhan.core;

import android.content.Context;
import android.content.Intent;
import android.os.Messenger;

import com.vanluom.group11.quanlytaichinhcanhan.home.MainActivity;
import com.vanluom.group11.quanlytaichinhcanhan.investment.PriceEditActivity;
import com.vanluom.group11.quanlytaichinhcanhan.search.SearchActivity;
import com.vanluom.group11.quanlytaichinhcanhan.search.SearchParameters;
import com.vanluom.group11.quanlytaichinhcanhan.sync.SyncConstants;
import com.vanluom.group11.quanlytaichinhcanhan.sync.SyncService;

import org.parceler.Parcels;

/**
 * Generates Intents for common app functionality.
 */

public class IntentFactory {
    public static Intent getSyncServiceIntent(Context context, String action, String localFile,
                                              String remoteFile, Messenger messenger) {
        Intent syncServiceIntent = new Intent(context, SyncService.class);

        syncServiceIntent.setAction(action);

        syncServiceIntent.putExtra(SyncConstants.INTENT_EXTRA_LOCAL_FILE, localFile);
        syncServiceIntent.putExtra(SyncConstants.INTENT_EXTRA_REMOTE_FILE, remoteFile);

        if (messenger != null) {
            syncServiceIntent.putExtra(SyncService.INTENT_EXTRA_MESSENGER, messenger);
        }

        return syncServiceIntent;
    }

    public static Intent getSearchIntent(Context context, SearchParameters parameters) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(SearchActivity.EXTRA_SEARCH_PARAMETERS, Parcels.wrap(parameters));
        intent.setAction(Intent.ACTION_INSERT);
        //getContext().startActivity(intent);
        return intent;
    }

    /**
     * Creates the intent that will start the Main Activity, resetting the activity stack.
     * This will prevent going back to any previous activity.
     * @return intent
     */
    public static Intent getMainActivityNew(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        // Clear the activity stack completely.
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        return intent;
    }

    public static Intent getPriceEditIntent(Context context) {
        Intent intent = new Intent(context, PriceEditActivity.class);

        return intent;
    }
}


package com.vanluom.group11.quanlytaichinhcanhan.sync;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.vanluom.group11.quanlytaichinhcanhan.home.MainActivity;

import java.io.File;

/**
 * Common code for Dropbox- and CloudRail-based synchronization
 */
public class SyncCommon {
    public Intent getIntentForOpenDatabase(Context context, File database) {
        Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
        intent.setData(Uri.fromFile(database));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        return intent;
    }

}

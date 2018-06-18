
package com.vanluom.group11.quanlytaichinhcanhan.core;

import android.app.backup.BackupAgentHelper;
import android.app.backup.FileBackupHelper;
import android.app.backup.SharedPreferencesBackupHelper;

import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;

/**
 * Manage preferences backup.
 */
public class MoneyManagerBackupAgentHelper
    extends BackupAgentHelper {

    private static final String KEY_BACKUP_APP_PREFERENCES = "KEY_BACKUP_APP_PREFERENCES";
    private static final String KEY_BACKUP_DROPBOX_PREFERENCES = "KEY_BACKUP_DROPBOX_PREFERENCES";
//    private static final String KEY_BACKUP_RECENT_DB_PREFERENCES = "KEY_BACKUP_RECENT_DB_PREFERENCES";
    private static final String KEY_BACKUP_DB = "KEY_BACKUP_DB";

    @Override
    public void onCreate() {
        super.onCreate();

        // create helper preferences
        SharedPreferencesBackupHelper appHelper = new SharedPreferencesBackupHelper(this, getPackageName() + "_preferences");
        SharedPreferencesBackupHelper dropboxHelper = new SharedPreferencesBackupHelper(this, getPackageName() + "_dropbox_preferences");
        // create helper files
        FileBackupHelper databaseHelper = new FileBackupHelper(this, MoneyManagerApplication.getDatabasePath(getApplicationContext()));

        addHelper(KEY_BACKUP_APP_PREFERENCES, appHelper);
        addHelper(KEY_BACKUP_DROPBOX_PREFERENCES, dropboxHelper);
        // todo: addHelper(KEY_BACKUP_RECENT_DB_PREFERENCES, dr);
        addHelper(KEY_BACKUP_DB, databaseHelper);
    }
}

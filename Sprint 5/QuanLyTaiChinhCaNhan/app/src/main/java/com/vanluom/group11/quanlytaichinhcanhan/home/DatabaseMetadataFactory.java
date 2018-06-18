package com.vanluom.group11.quanlytaichinhcanhan.home;

import android.content.Context;
import android.support.annotation.NonNull;

import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.settings.AppSettings;
import com.vanluom.group11.quanlytaichinhcanhan.settings.SyncPreferences;
import com.vanluom.group11.quanlytaichinhcanhan.sync.SyncManager;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDate;

/**
 * Factory for the database metadata records.
 */

public class DatabaseMetadataFactory {

    public static DatabaseMetadata getInstance(String localPath) {
        DatabaseMetadata db = new DatabaseMetadata();
        db.localPath = localPath;
        db.remotePath = "";
        return db;
    }

    public static DatabaseMetadata getInstance(String filePath, @NonNull String remoteFileName) {
        DatabaseMetadata entry = new DatabaseMetadata();
        entry.localPath = filePath;
        entry.remotePath = remoteFileName;
        return entry;
    }

    /*
        dynamic
     */

    public DatabaseMetadataFactory(Context context) {
        this.context = context;
    }

    private Context context;

    public Context getContext() {
        return this.context;
    }

    /**
     * Creates a database entry from the current preferences. Used for transition from preferences
     * to Database metadata records.
     * @return A database record that represents the current preferences (local/remote db paths).
     */
    public DatabaseMetadata createDefaultEntry() {
        DatabaseMetadata entry = new DatabaseMetadata();

        // todo remove the local change preference after upgrade.
        entry.localPath = MoneyManagerApplication.getDatabasePath(getContext());
        entry.isLocalFileChanged = new AppSettings(getContext()).get(R.string.pref_is_local_file_changed, false);

        SyncManager syncManager = new SyncManager(getContext());
        // todo remove the remote file preference after upgrade
        entry.remotePath = new SyncPreferences(getContext()).loadPreference(R.string.pref_remote_file, "");
        MmxDate cachedRemoteChangeDate = syncManager.getRemoteLastModifiedDatePreferenceFor(entry.remotePath);
        entry.setRemoteLastChangedDate(cachedRemoteChangeDate);

        return entry;
    }
}

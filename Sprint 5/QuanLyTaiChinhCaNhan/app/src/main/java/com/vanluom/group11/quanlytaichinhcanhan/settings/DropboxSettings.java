package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * Encapsulates Dropbox preferences.
 */
public class DropboxSettings
    extends SettingsBase {

    public DropboxSettings(Context context) {
        super(context);

    }

    @Override
    protected SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    public boolean getShouldSyncOnWifi() {
        boolean result = get(R.string.pref_sync_via_wifi, false);
        return result;
    }

    public boolean getImmediatelyUploadChanges() {
        boolean result = get(R.string.pref_dropbox_upload_immediate, true);
        return result;
    }
}

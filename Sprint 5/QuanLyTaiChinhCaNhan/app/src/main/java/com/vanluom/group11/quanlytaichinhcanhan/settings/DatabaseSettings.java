package com.vanluom.group11.quanlytaichinhcanhan.settings;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * Manipulates database preferences/preferences.
 */
public class DatabaseSettings {

    public DatabaseSettings(AppSettings mainSettings) {
        mAppSettings = mainSettings;
    }

    private AppSettings mAppSettings;

    public String getDatabasePath() {
        String path = mAppSettings.get(R.string.pref_database_path, "");
        return path;
    }

    public void setDatabasePath(String path) {
        mAppSettings.set(R.string.pref_database_path, path);
    }
}

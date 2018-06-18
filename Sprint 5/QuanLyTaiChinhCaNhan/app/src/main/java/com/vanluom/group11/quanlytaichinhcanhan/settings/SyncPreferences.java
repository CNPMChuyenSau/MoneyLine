
package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.content.Context;
import android.content.SharedPreferences;

import com.vanluom.group11.quanlytaichinhcanhan.R;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * Handles sync-related preferences.
 */
public class SyncPreferences
    extends SettingsBase {

    public SyncPreferences(Context context) {
        super(context);

    }

    /**
     * Delete all preferences.
     */
    public void clear() {
        getPreferences().edit().clear().apply();
    }

    public boolean get(Integer key, boolean defaultValue) {
        return getPreferences().getBoolean(getKey(key), defaultValue);
    }

    public String get(Integer key, String defaultValue) {
        return getPreferences().getString(getKey(key), defaultValue);
    }

    @Override
    protected SharedPreferences getPreferences() {
        return getContext().getSharedPreferences(PreferenceConstants.SYNC_PREFERENCES, Context.MODE_PRIVATE);
    }

    public boolean isSyncEnabled() {
        return get(R.string.pref_sync_enabled, false);
    }

    public int getSyncInterval() {
        int defaultSchedule = 30;   // time in minutes
        String setSchedule = get(R.string.pref_sync_interval, Integer.toString(defaultSchedule));
        if (!NumberUtils.isNumber(setSchedule)) return defaultSchedule;

        return Integer.parseInt(setSchedule);
    }

    public boolean getUploadImmediately() {
        return get(R.string.pref_upload_immediately, true);
    }

    public String loadPreference(Integer key, String defaultValue) {
        String realKey = getContext().getString(key);

        return getPreferences().getString(realKey, defaultValue);
    }

    public void setSyncEnabled(boolean value) {
        set(R.string.pref_sync_enabled, value);
    }

    /**
     * Set synchronization period.
     * @param value Sync frequency in minutes.
     */
    public void setSyncInterval(int value) {
        set(R.string.pref_sync_interval, Integer.toString(value));
    }

    public boolean shouldSyncOnlyOnWifi() {
        return get(R.string.pref_sync_via_wifi, false);
    }

    // private

    private String getKey(Integer resourceId) {
        return getContext().getString(resourceId);
    }
}

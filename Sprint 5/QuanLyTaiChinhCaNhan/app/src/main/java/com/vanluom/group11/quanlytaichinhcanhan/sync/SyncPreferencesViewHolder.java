
package com.vanluom.group11.quanlytaichinhcanhan.sync;

import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.SwitchPreferenceCompat;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * View holder for sync preferences.
 */
public class SyncPreferencesViewHolder {

    SwitchPreferenceCompat syncEnabled;
    public ListPreference providerList;
    public Preference remoteFile;
    public ListPreference syncInterval;
    public Preference resetPreferences;
    public PreferenceScreen download;
    public PreferenceScreen upload;
    public SwitchPreferenceCompat syncOnStart;

    public SyncPreferencesViewHolder(PreferenceFragmentCompat view) {

        syncEnabled = (SwitchPreferenceCompat) view.findPreference(view.getString(R.string.pref_sync_enabled));
        providerList = (ListPreference) view.findPreference(view.getString(R.string.pref_sync_provider));
        remoteFile = view.findPreference(view.getString(R.string.pref_remote_file));
        syncInterval = (ListPreference) view.findPreference(view.getString(R.string.pref_sync_interval));
        resetPreferences = view.findPreference(view.getString(R.string.pref_reset_preferences));
        download = (PreferenceScreen) view.findPreference(view.getString(R.string.pref_sync_download));
        upload = (PreferenceScreen) view.findPreference(view.getString(R.string.pref_sync_upload));
        syncOnStart = (SwitchPreferenceCompat) view.findPreference(view.getString(R.string.pref_sync_on_app_start));
    }
}

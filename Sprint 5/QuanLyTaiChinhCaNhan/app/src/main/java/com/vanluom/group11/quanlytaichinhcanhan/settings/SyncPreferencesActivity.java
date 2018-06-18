package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.os.Bundle;

import com.vanluom.group11.quanlytaichinhcanhan.sync.SyncPreferenceFragment;

public class SyncPreferencesActivity
    extends BaseSettingsFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setSettingFragment(new SyncPreferenceFragment());
    }
}

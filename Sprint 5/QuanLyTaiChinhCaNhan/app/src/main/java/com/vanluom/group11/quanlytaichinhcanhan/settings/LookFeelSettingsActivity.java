
package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.os.Bundle;

public class LookFeelSettingsActivity
    extends BaseSettingsFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setSettingFragment(new LookFeelPreferenceFragment());
    }
}

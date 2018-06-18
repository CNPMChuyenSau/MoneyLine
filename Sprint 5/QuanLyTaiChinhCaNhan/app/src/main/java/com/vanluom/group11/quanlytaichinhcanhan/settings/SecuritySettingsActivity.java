
package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.os.Bundle;

public class SecuritySettingsActivity
        extends BaseSettingsFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setSettingFragment(new SecuritySettingsFragment());
    }
}

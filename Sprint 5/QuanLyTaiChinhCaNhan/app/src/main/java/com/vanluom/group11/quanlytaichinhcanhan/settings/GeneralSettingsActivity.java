
package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.os.Bundle;

import com.vanluom.group11.quanlytaichinhcanhan.R;

public class GeneralSettingsActivity
    extends BaseSettingsFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setSettingFragment(new GeneralSettingsFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTitle(R.string.preferences_general);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }
}

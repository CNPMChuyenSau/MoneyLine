package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * Budget preferences.
 */
public class BudgetSettingsFragment
    extends PreferenceFragmentCompat {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // use either setPreferenceScreen(PreferenceScreen) or addPreferencesFromResource(int).

        addPreferencesFromResource(R.xml.preferences_budget);
    }

    @Override
    public void onStart() {
        super.onStart();

//        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();

//        EventBus.getDefault().unregister(this);
    }

    // Private
}

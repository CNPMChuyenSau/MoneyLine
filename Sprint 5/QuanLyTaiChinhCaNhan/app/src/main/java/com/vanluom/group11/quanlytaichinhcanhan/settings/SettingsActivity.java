package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.core.Core;

import javax.inject.Inject;

import dagger.Lazy;

public class SettingsActivity
    extends BaseSettingsFragmentActivity {

    public static final String EXTRA_FRAGMENT = "extraFragment";

    @Inject Lazy<AppSettings> appSettingsLazy;
    @Inject Lazy<Core> coreLazy;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        MoneyManagerApplication.getApp().iocComponent.inject(this);

        showFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTitle(R.string.settings);
    }

    private void showFragment() {
        // figure out which fragment to show
        PreferenceFragmentCompat fragment = null;

        Intent intent = getIntent();

        String fragmentName = intent.getStringExtra(EXTRA_FRAGMENT);
        if (fragmentName != null && fragmentName.equals(PerDatabaseFragment.class.getSimpleName())) {
            fragment = new PerDatabaseFragment();
        }

        // default
        if (fragment == null) {
            fragment = new SettingsFragment();
        }

        setSettingFragment(fragment);
    }
}

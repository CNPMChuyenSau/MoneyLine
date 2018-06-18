package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.text.TextUtils;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.mmex_icon_font_typeface_library.MMXIconFont;
import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.about.AboutActivity;
import com.vanluom.group11.quanlytaichinhcanhan.core.UIHelper;

import timber.log.Timber;

/**
 * Root preferences fragment.
 */
public class SettingsFragment
    extends PreferenceFragmentCompat {

    public static final int REQUEST_GENERAL_PREFERENCES = 1;

    private UIHelper uiHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        initGeneralSettings();

        // Per-Database preferences
        Preference perDbPreference = findPreference(getString(R.string.pref_per_database));
        if (perDbPreference != null) {
            perDbPreference.setIcon(uiHelper.getIcon(GoogleMaterial.Icon.gmd_settings_applications)
                    .color(uiHelper.getSecondaryTextColor()));

            perDbPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent intent = new Intent(getActivity(), SettingsActivity.class);
                    intent.putExtra(SettingsActivity.EXTRA_FRAGMENT, PerDatabaseFragment.class.getSimpleName());
                    startActivity(intent);
                    return true;
                }
            });
        }

        final Preference lookAndFeelPreference = findPreference(getString(PreferenceConstants.PREF_LOOK_FEEL));
        if (lookAndFeelPreference != null) {
            lookAndFeelPreference.setIcon(uiHelper.getIcon(GoogleMaterial.Icon.gmd_wallpaper)
                    .color(uiHelper.getSecondaryTextColor()));
            lookAndFeelPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    startActivity(new Intent(getActivity(), LookFeelSettingsActivity.class));
                    return true;
                }
            });
        }

        final Preference behaviourPreference = findPreference(getString(R.string.pref_behaviour));
        if (behaviourPreference != null) {
            behaviourPreference.setIcon(uiHelper.getIcon(GoogleMaterial.Icon.gmd_play_circle_outline)
                    .color(uiHelper.getSecondaryTextColor()));
            behaviourPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    startActivity(new Intent(getActivity(), BehaviourSettingsActivity.class));
                    return true;
                }
            });
        }

        final Preference investmentPreference = findPreference(getString(R.string.pref_investment));
        if (investmentPreference != null) {
            investmentPreference.setIcon(uiHelper.getIcon(GoogleMaterial.Icon.gmd_trending_up)
                    .color(uiHelper.getSecondaryTextColor()));
            investmentPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    startActivity(new Intent(getActivity(), InvestmentSettingsActivity.class));
                    return true;
                }
            });
        }

        final Preference budgetPreference = findPreference(getString(R.string.pref_budget));
        if (budgetPreference != null) {
            budgetPreference.setIcon(uiHelper.getIcon(MMXIconFont.Icon.mmx_law)
                    .color(uiHelper.getSecondaryTextColor()));
            budgetPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    startActivity(new Intent(getActivity(), BudgetSettingsActivity.class));
                    return true;
                }
            });
        }

        final Preference passcodePreference = findPreference(getString(PreferenceConstants.PREF_SECURITY));
        if (passcodePreference != null) {
            passcodePreference.setIcon(uiHelper.getIcon(GoogleMaterial.Icon.gmd_lock)
                    .color(uiHelper.getSecondaryTextColor()));
            passcodePreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    startActivity(new Intent(getActivity(), SecuritySettingsActivity.class));
                    return true;
                }
            });
        }

        final Preference databasesPreference = findPreference(getString(PreferenceConstants.PREF_DATABASE));
        if (databasesPreference != null) {
            databasesPreference.setIcon(uiHelper.getIcon(GoogleMaterial.Icon.gmd_storage)
                    .color(uiHelper.getSecondaryTextColor()));
            databasesPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    startActivity(new Intent(getActivity(), DatabaseSettingsActivity.class));
                    return true;
                }
            });
        }

        // Synchronisation
        final Preference syncPreference = findPreference(getString(R.string.pref_synchronization));
        if (syncPreference != null) {
            syncPreference.setIcon(uiHelper.getIcon(GoogleMaterial.Icon.gmd_sync)
                    .color(uiHelper.getSecondaryTextColor()));
            syncPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    startActivity(new Intent(getActivity(), SyncPreferencesActivity.class));
                    return true;
                }
            });
        }


        final Preference infoPreference = findPreference(getString(PreferenceConstants.PREF_VERSION_NAME));
        if (infoPreference != null) {
            infoPreference.setIcon(uiHelper.getIcon(GoogleMaterial.Icon.gmd_info_outline)
                    .color(uiHelper.getSecondaryTextColor()));
            infoPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    startActivity(new Intent(getActivity(), AboutActivity.class));
                    return true;
                }
            });
        }

        // manage intent
        if (getActivity().getIntent() != null) {
            if (!TextUtils.isEmpty(getActivity().getIntent()
                    .getStringExtra(Constants.INTENT_REQUEST_PREFERENCES_SCREEN))) {
                try {
                    PreferenceScreen screen = getPreferenceScreen();
                    Preference preference = findPreference(getActivity().getIntent()
                            .getStringExtra(Constants.INTENT_REQUEST_PREFERENCES_SCREEN));
                    if (preference != null) {
                        //screen.onItemClick(null, null, preference.getOrder(), 0);
                        screen.performClick();
                    }
                } catch (Exception e) {
                    Timber.e(e, "opening preferences screen");
                }
            }
        }
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Timber.d("creating preferences");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_GENERAL_PREFERENCES:
                // always recreate activity when returning from general preferences, instead of
                // trying to figure out if something has changed.
                getActivity().recreate();
                break;
        }
    }

    private UIHelper getUiHelper() {
        if (this.uiHelper == null) {
            uiHelper = new UIHelper(getActivity());
        }
        return uiHelper;
    }

    private void initGeneralSettings() {
        // General Settings

        final Preference generalPreference = findPreference(getString(PreferenceConstants.PREF_GENERAL));
        if (generalPreference == null) return;

        generalPreference.setIcon(getUiHelper().getIcon(GoogleMaterial.Icon.gmd_build)
            .color(uiHelper.getSecondaryTextColor()));

        generalPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(getActivity(), GeneralSettingsActivity.class);
                startActivityForResult(intent, REQUEST_GENERAL_PREFERENCES);
                return true;
            }
        });
    }
}

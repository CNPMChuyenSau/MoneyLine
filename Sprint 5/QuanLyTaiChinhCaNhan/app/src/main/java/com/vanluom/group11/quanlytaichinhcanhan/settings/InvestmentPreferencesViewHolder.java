
package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * View Holder for investment preferences
 */
public class InvestmentPreferencesViewHolder {
    public InvestmentPreferencesViewHolder(PreferenceFragmentCompat container) {
        threshold = container.findPreference(container.getString(R.string.pref_asset_allocation_threshold));
        quoteProvider = (ListPreference) container.findPreference(container.getString(R.string.pref_quote_provider));
        exchangeRateProvider = (ListPreference) container.findPreference(container.getString(R.string.pref_exchange_rate_provider));
    }

    public Preference threshold;
    public ListPreference quoteProvider;
    public ListPreference exchangeRateProvider;
}

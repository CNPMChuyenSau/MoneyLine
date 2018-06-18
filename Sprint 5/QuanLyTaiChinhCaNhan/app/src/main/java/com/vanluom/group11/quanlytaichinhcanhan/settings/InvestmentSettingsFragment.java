package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.AmountInputDialog;
import com.vanluom.group11.quanlytaichinhcanhan.common.events.AmountEnteredEvent;
import com.vanluom.group11.quanlytaichinhcanhan.investment.QuoteProviders;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import info.javaperformance.money.Money;

/**
 * Look & feel preferences.
 */
public class InvestmentSettingsFragment
    extends PreferenceFragmentCompat {

    private static final String KEY_THRESHOLD = "AssetAllocationThreshold";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceManager.getDefaultSharedPreferences(getActivity());

        InvestmentPreferencesViewHolder viewHolder = new InvestmentPreferencesViewHolder(this);

        initializeAssetAllocationThreshold(viewHolder);
        initializeQuotesProvider(viewHolder);
        initializeExchangeRatesProvider(viewHolder);
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // use either setPreferenceScreen(PreferenceScreen) or addPreferencesFromResource(int).

        addPreferencesFromResource(R.xml.preferences_investment);
    }

    @Override
    public void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    // Events

    @Subscribe
    public void onEvent(AmountEnteredEvent event) {
        if (event.requestId.equals(KEY_THRESHOLD)) {
            InvestmentSettings settings = new InvestmentSettings(getActivity());
            settings.setAssetAllocationDifferenceThreshold(event.amount);
        }
    }

    // Private

    private void displayQuotesProvider(String providerName) {
        ListPreference preference = (ListPreference) findPreference(getString(R.string.pref_quote_provider));
        if (preference == null) return;

        preference.setSummary(providerName); // show current value
    }

    private void displayExchangeRatesProvider(String providerName) {
        ListPreference preference = (ListPreference) findPreference(getString(R.string.pref_exchange_rate_provider));
        if (preference == null) return;

        preference.setSummary(providerName); // show current value
    }

    private void initializeAssetAllocationThreshold(InvestmentPreferencesViewHolder viewHolder) {
        if (viewHolder.threshold == null) return;

        final InvestmentSettings settings = new InvestmentSettings(getActivity());

        Preference.OnPreferenceClickListener listener = new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Money value = settings.getAssetAllocationDifferenceThreshold();

                // show number entry form
                AmountInputDialog
                    .getInstance(KEY_THRESHOLD, value)
                    .show(getFragmentManager(), KEY_THRESHOLD);
                return true;
            }
        };
        viewHolder.threshold.setOnPreferenceClickListener(listener);
    }

    private void initializeQuotesProvider(InvestmentPreferencesViewHolder viewHolder) {
        ListPreference preference = viewHolder.quoteProvider;
        if (preference == null) return;

        // initialize
        preference.setEntries(QuoteProviders.names());
        preference.setEntryValues(QuoteProviders.names());
        preference.setDefaultValue(QuoteProviders.YahooYql.name());

        final InvestmentSettings settings = new InvestmentSettings(getContext());
        QuoteProviders currentProvider = settings.getQuoteProvider();
        preference.setSummary(currentProvider.name()); // show current value
//        preference.setValue(currentProvider.name());
        displayQuotesProvider(currentProvider.name());

        Preference.OnPreferenceChangeListener listener = new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                // update
                String name = (String) o;
                QuoteProviders provider = QuoteProviders.valueOf(name);
                settings.setQuoteProvider(provider);

                displayQuotesProvider(name);
                return true;
            }
        };
        preference.setOnPreferenceChangeListener(listener);
    }

    private void initializeExchangeRatesProvider(InvestmentPreferencesViewHolder viewHolder) {
        ListPreference preference = viewHolder.exchangeRateProvider;
        if (preference == null) return;

        // initialize
        preference.setEntries(QuoteProviders.names());
        preference.setEntryValues(QuoteProviders.names());
        preference.setDefaultValue(QuoteProviders.YahooYql.name());

        final InvestmentSettings settings = new InvestmentSettings(getContext());
        QuoteProviders currentProvider = settings.getExchangeRateProvider();
        preference.setSummary(currentProvider.name()); // show current value
//        preference.setValue(currentProvider.name());
        displayExchangeRatesProvider(currentProvider.name());

        Preference.OnPreferenceChangeListener listener = new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                // update
                String name = (String) o;
                QuoteProviders provider = QuoteProviders.valueOf(name);
                settings.setExchangeRateProvider(provider);

                displayExchangeRatesProvider(name);
                return true;
            }
        };
        preference.setOnPreferenceChangeListener(listener);
    }

}

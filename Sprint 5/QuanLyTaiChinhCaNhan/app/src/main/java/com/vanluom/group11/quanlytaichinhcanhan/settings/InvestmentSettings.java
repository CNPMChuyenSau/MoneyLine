
package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.core.InfoKeys;
import com.vanluom.group11.quanlytaichinhcanhan.investment.QuoteProviders;
import com.vanluom.group11.quanlytaichinhcanhan.servicelayer.InfoService;

import info.javaperformance.money.Money;
import info.javaperformance.money.MoneyFactory;

/**
 * Investment preferences / preferences.
 */
public class InvestmentSettings
    extends SettingsBase {

    public InvestmentSettings(Context context) {
        super(context);

    }

    @Override
    protected SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    /**
     * Threshold percentage at which the difference in actual asset allocation vs set asset
     * allocation will be painted. Green, if the current allocation is higher than the set allocation,
     * and red if it is smaller for the set percentage of the original value.
     * I.e. 20 represents 20% difference compared to the set asset allocation value.
     * @return A number that represents the percentage value.
     */
    public Money getAssetAllocationDifferenceThreshold() {
        InfoService service = new InfoService(getContext());
        String value = service.getInfoValue(InfoKeys.ASSET_ALLOCATION_DIFF_THRESHOLD);

        if (TextUtils.isEmpty(value)) {
            value = Integer.toString(Constants.NOT_SET); // "-1";
        }
        return MoneyFactory.fromString(value);
    }

    public void setAssetAllocationDifferenceThreshold(Money value) {
        InfoService service = new InfoService(getContext());
        service.setInfoValue(InfoKeys.ASSET_ALLOCATION_DIFF_THRESHOLD, value.toString());
    }

    public QuoteProviders getQuoteProvider() {
        QuoteProviders defaultValue = QuoteProviders.YahooYql;

        InfoService service = new InfoService(getContext());
        String value = service.getInfoValue(InfoKeys.QUOTE_PROVIDER);
        if (value == null) {
            return defaultValue;
        }

        QuoteProviders provider = QuoteProviders.valueOf(value);
        // default value returned if none set.
        return provider != null ? provider : defaultValue;
    }

    public void setQuoteProvider(QuoteProviders value) {
        InfoService service = new InfoService(getContext());
        service.setInfoValue(InfoKeys.QUOTE_PROVIDER, value.name());
    }

    public QuoteProviders getExchangeRateProvider() {
        QuoteProviders defaultValue = QuoteProviders.YahooYql;

        InfoService service = new InfoService(getContext());
        String value = service.getInfoValue(InfoKeys.EXCHANGE_RATE_PROVIDER);
        if (value == null) {
            return defaultValue;
        }

        QuoteProviders provider = QuoteProviders.valueOf(value);
        // default value returned if none set.
        return provider != null ? provider : defaultValue;
    }

    public void setExchangeRateProvider(QuoteProviders value) {
        InfoService service = new InfoService(getContext());
        service.setInfoValue(InfoKeys.EXCHANGE_RATE_PROVIDER, value.name());
    }

}

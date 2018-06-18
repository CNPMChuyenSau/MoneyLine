
package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.core.InfoKeys;
import com.vanluom.group11.quanlytaichinhcanhan.servicelayer.InfoService;
import com.vanluom.group11.quanlytaichinhcanhan.core.DefinedDateRange;
import com.vanluom.group11.quanlytaichinhcanhan.core.DefinedDateRangeName;
import com.vanluom.group11.quanlytaichinhcanhan.core.DefinedDateRanges;

import timber.log.Timber;

/**
 * Look & Feel preferences
 */
public class LookAndFeelSettings
    extends SettingsBase {

    public LookAndFeelSettings(Context context) {
        super(context);

    }

    @Override
    protected SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    public boolean getHideReconciledAmounts() {
        String key = getSettingsKey(R.string.pref_transaction_hide_reconciled_amounts);
        return getBooleanSetting(key, false);
    }

    public DefinedDateRangeName getShowTransactions() {
        DefinedDateRangeName defaultValue = DefinedDateRangeName.LAST_7_DAYS;

        String value = get(R.string.pref_show_transaction, defaultValue.name());

        if (TextUtils.isEmpty(value)) {
            return defaultValue;
        }

        DefinedDateRangeName result = null;

        // try directly first
        try {
            result = DefinedDateRangeName.valueOf(value);
        } catch (IllegalArgumentException e) {
            Timber.w("error parsing default date range");
        }
        if (result != null) {
            return result;
        }

        // then try by the previous setting, localized range name
        DefinedDateRanges ranges = new DefinedDateRanges(getContext());
        DefinedDateRange range = ranges.getByLocalizedName(value);
        if (range != null) {
            setShowTransactions(range.key);
            return range.key;
        }

        // if still not found, initialize to a default value.
        setShowTransactions(defaultValue);
        return defaultValue;
    }

    public void setShowTransactions(DefinedDateRangeName value) {
        String key = getSettingsKey(R.string.pref_show_transaction);
        set(key, value.toString());
    }

    public boolean getViewOpenAccounts() {
//        return get(R.string.pref_account_open_visible, true);
        InfoService infoService = new InfoService(getContext());
        String value = infoService.getInfoValue(InfoKeys.SHOW_OPEN_ACCOUNTS);
        return Boolean.valueOf(value);
    }

    public void setViewOpenAccounts(Boolean value) {
        InfoService infoService = new InfoService(getContext());
        infoService.setInfoValue(InfoKeys.SHOW_OPEN_ACCOUNTS, value.toString());
    }

    public boolean getViewFavouriteAccounts() {
//        return get(R.string.pref_account_fav_visible, true);
        InfoService infoService = new InfoService(getContext());
        String value = infoService.getInfoValue(InfoKeys.SHOW_FAVOURITE_ACCOUNTS);
        return Boolean.valueOf(value);
    }

    public void setViewFavouriteAccounts(Boolean value) {
        InfoService infoService = new InfoService(getContext());
        infoService.setInfoValue(InfoKeys.SHOW_FAVOURITE_ACCOUNTS, value.toString());
    }

    public boolean getSortTransactionsByType() {
        String key = getSettingsKey(R.string.pref_transaction_sort_by_type);
        return getBooleanSetting(key, true);
    }
}

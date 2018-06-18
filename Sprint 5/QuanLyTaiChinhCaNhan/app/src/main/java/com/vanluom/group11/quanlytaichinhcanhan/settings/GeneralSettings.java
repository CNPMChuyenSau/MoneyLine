
package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.core.InfoKeys;
import com.vanluom.group11.quanlytaichinhcanhan.core.NumericHelper;
import com.vanluom.group11.quanlytaichinhcanhan.servicelayer.InfoService;

/**
 * Settings in the General category.
 */
public class GeneralSettings
    extends SettingsBase {

    public GeneralSettings(Context context) {
        super(context);

    }

    @Override
    protected SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    public String getApplicationLanguage() {
        String result = get(R.string.pref_locale, "");
        return result;
    }

    /**
     * Set application locale.
     * @param value Language ISO code (i.e. bs, en)
     */
    public void setApplicationLanguage(String value) {
        set(R.string.pref_locale, value);
    }

    /**
     * Fetches the default account id. The default account is set per database.
     * @return Default account id.
     */
    public Integer getDefaultAccountId() {
//        String value = get(R.string.pref_default_account, "");
        InfoService service = new InfoService(getContext());
        String value = service.getInfoValue(InfoKeys.DEFAULT_ACCOUNT_ID);

        return NumericHelper.toInteger(value);
    }

    public void setDefaultAccountId(Integer accountId) {
        String value = "";
        if (accountId != null) {
            value = accountId.toString();
        }
//        set(R.string.pref_default_account, value);

        InfoService service = new InfoService(getContext());
        service.setInfoValue(InfoKeys.DEFAULT_ACCOUNT_ID, value);
    }

    public String getTheme() {
        String lightTheme = Constants.THEME_LIGHT;
        return get(R.string.pref_theme, lightTheme);
    }
}

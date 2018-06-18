
package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * Budget preferences
 */
public class BudgetSettings
    extends SettingsBase {

    public BudgetSettings(Context context) {
        super(context);
    }

    @Override
    protected SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    public Boolean getShowSimpleView() {
        return get(R.string.pref_budget_show_simple_view, false);
    }

    public void setShowSimpleView(boolean value) {
        set(R.string.pref_budget_show_simple_view, value);
    }

}

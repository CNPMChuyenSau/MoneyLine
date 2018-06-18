package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * Settings in the General category.
 */
public class BehaviourSettings
    extends SettingsBase {

    public BehaviourSettings(Context context) {
        super(context);

    }

    @Override
    protected SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    public boolean getNotificationRecurringTransaction() {
        return get(PreferenceConstants.PREF_REPEATING_TRANSACTION_NOTIFICATIONS, true);
    }

    public String getNotificationTime() {
        return get(PreferenceConstants.PREF_REPEATING_TRANSACTION_CHECK, "08:00");
    }

    public void setNotificationTime(String timeString) {
        set(PreferenceConstants.PREF_REPEATING_TRANSACTION_CHECK, timeString);
    }

    public boolean getFilterInSelectors() {
        return get(R.string.pref_behaviour_focus_filter, true);
    }

    /**
     * The period to use for the income/expense summary footer on Home screen.
     * @return
     */
    public String getIncomeExpensePeriod() {
        return get(R.string.pref_income_expense_footer_period,
                getContext().getString(R.string.last_month)
        );
    }

    public Boolean getShowTutorial() {
        return get(R.string.pref_show_tutorial, true);
    }

    public void setShowTutorial(boolean value) {
        set(R.string.pref_show_tutorial, value);
    }
}

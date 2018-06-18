package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDate;

/**
 * Look & feel preferences.
 */
public class BehaviourSettingsFragment
    extends PreferenceFragmentCompat {

    private static final String KEY_NOTIFICATION_TIME = "NotificationTime";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // use either setPreferenceScreen(PreferenceScreen) or addPreferencesFromResource(int).

        addPreferencesFromResource(R.xml.preferences_behaviour);

        initializeNotificationTime();
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

    private void initializeNotificationTime() {
        Preference preference = findPreference(getString(PreferenceConstants.PREF_REPEATING_TRANSACTION_CHECK));
        if (preference == null) return;

        Preference.OnPreferenceClickListener listener = new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                showTimePicker();
                return true;
            }
        };
        preference.setOnPreferenceClickListener(listener);
    }

    private void showTimePicker() {
        final BehaviourSettings settings = new BehaviourSettings(getActivity());

        RadialTimePickerDialogFragment.OnTimeSetListener timeSetListener = new RadialTimePickerDialogFragment.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
                String value = String.format("%02d:%02d", hourOfDay, minute);
                settings.setNotificationTime(value);
            }
        };

        // get time to display (current setting)
        String timeString = settings.getNotificationTime();
//        DateTimeFormatter formatter = DateTimeFormat.forPattern(Constants.TIME_FORMAT);
//        DateTime currentValue = formatter.parseDateTime(timeString);
        MmxDate currentValue = new MmxDate(timeString, Constants.TIME_FORMAT);

        int hour = currentValue != null ? currentValue.getHourOfDay() : 8;
        int minute = currentValue != null ? currentValue.getMinuteOfHour() : 0;

        RadialTimePickerDialogFragment timePicker = new RadialTimePickerDialogFragment()
            .setOnTimeSetListener(timeSetListener)
            .setForced24hFormat()
            .setStartTime(hour, minute)
            .setThemeDark();
        timePicker.show(getChildFragmentManager(), KEY_NOTIFICATION_TIME);
    }
}

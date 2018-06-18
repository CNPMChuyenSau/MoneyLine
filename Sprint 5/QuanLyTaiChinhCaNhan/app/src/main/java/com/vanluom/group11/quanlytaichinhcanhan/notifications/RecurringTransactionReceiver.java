
package com.vanluom.group11.quanlytaichinhcanhan.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;

import com.vanluom.group11.quanlytaichinhcanhan.settings.PreferenceConstants;

public class RecurringTransactionReceiver
	extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
        // If the notifications are disabled in preferences, do not trigger the alarm.
        boolean notify = PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(context.getString(PreferenceConstants.PREF_REPEATING_TRANSACTION_NOTIFICATIONS), true);
        if (!notify) return;

		Intent service = new Intent(context, RecurringTransactionIntentService.class);
		context.startService(service);
	}

}

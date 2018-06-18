
package com.vanluom.group11.quanlytaichinhcanhan.notifications;

import android.app.IntentService;
import android.content.Intent;

/**
 * Background service that triggers notifications about recurring transactions.
 */
public class RecurringTransactionIntentService
	extends IntentService {

	public RecurringTransactionIntentService() {
		super("com.money.manager.ex.notifications.RecurringTransactionIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// start repeating transaction
		RecurringTransactionNotifications notifications = new RecurringTransactionNotifications(getApplicationContext());
		notifications.notifyRepeatingTransaction();
	}
}

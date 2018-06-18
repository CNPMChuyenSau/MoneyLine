package com.vanluom.group11.quanlytaichinhcanhan.sync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.settings.SyncPreferences;

import timber.log.Timber;

/**
 * Receiver that is triggered by the alarm to run synchronization.
 * Triggered by the timer/heartbeat. Set up in SyncSchedulerBroadcastReceiver.
 */
public class SyncBroadcastReceiver
	extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
        Timber.d("receiving a sync intent");

		SyncManager sync = new SyncManager(context);
        if (!sync.canSync()) return;

		// check sync interval.
		if (new SyncPreferences(context).getSyncInterval() == 0) return;

		// Trigger synchronization

		Intent service = new Intent(context, SyncService.class);
		service.setAction(SyncConstants.INTENT_ACTION_SYNC);

		service.putExtra(SyncConstants.INTENT_EXTRA_LOCAL_FILE, MoneyManagerApplication.getDatabasePath(context));
		service.putExtra(SyncConstants.INTENT_EXTRA_REMOTE_FILE, sync.getRemotePath());

		context.startService(service);
	}
}

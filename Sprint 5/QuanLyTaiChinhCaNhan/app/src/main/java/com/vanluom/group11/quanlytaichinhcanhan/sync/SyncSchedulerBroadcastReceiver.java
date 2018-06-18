
package com.vanluom.group11.quanlytaichinhcanhan.sync;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.vanluom.group11.quanlytaichinhcanhan.settings.SyncPreferences;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDate;

import timber.log.Timber;

/**
 * Schedules the periodic alarm (sync heartbeat) that triggers cloud synchronization.
 * Called from the preferences when the synchronization interval changes, and on BOOT_COMPLETED.
 */
public class SyncSchedulerBroadcastReceiver
    extends BroadcastReceiver {

    public static final String ACTION_START = "com.money.manager.ex.intent.action.START_SYNC_SERVICE";
    public static final String ACTION_STOP = "com.money.manager.ex.intent.action.STOP_SYNC_SERVICE";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = "";
        // by default, the action is ACTION_START. This is assumed on device boot.

        if (intent != null && !TextUtils.isEmpty(intent.getAction())) {
            action = intent.getAction();
            Timber.d("Sync scheduler request: %s", action);
        }

        Intent syncIntent = new Intent(context, SyncBroadcastReceiver.class);
        PendingIntent pendingSyncIntent = PendingIntent.getBroadcast(context, SyncConstants.REQUEST_PERIODIC_SYNC,
                syncIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // PendingIntent.FLAG_CANCEL_CURRENT, FLAG_UPDATE_CURRENT

        AlarmManager alarmManager = getAlarmManager(context);

        // Cancel existing heartbeat.
        if (action.equals(ACTION_STOP)) {
            Timber.d("Stopping synchronization alarm.");

            alarmManager.cancel(pendingSyncIntent);
            return;
        }

        startHeartbeat(context, alarmManager, pendingSyncIntent);
    }

    private AlarmManager getAlarmManager(Context context) {
        return (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    private void startHeartbeat(Context context, AlarmManager alarmManager, PendingIntent pendingIntent) {
        SyncManager sync = new SyncManager(context);
        if (!sync.isSyncEnabled()) return;

        // get frequency in minutes.
        SyncPreferences preferences = new SyncPreferences(context);
        int minutes = preferences.getSyncInterval();
        // If the period is 0, do not schedule the alarm.
        if (minutes <= 0) return;

        MmxDate now = new MmxDate();
        int secondsInMinute = 60;

        Timber.d("Scheduling synchronisation at: %s, repeat every %s minutes", now.toString(), minutes);

        // Schedule the alarm for synchronization. Run immediately and then in the given interval.
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                now.getMillis(),
                minutes * secondsInMinute * 1000,
                pendingIntent);
    }
}

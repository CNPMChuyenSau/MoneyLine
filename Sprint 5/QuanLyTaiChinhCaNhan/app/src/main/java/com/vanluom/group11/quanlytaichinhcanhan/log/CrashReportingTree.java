
package com.vanluom.group11.quanlytaichinhcanhan.log;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import org.greenrobot.eventbus.EventBus;

import timber.log.Timber;

/**
 * Logging tree for Production builds. Reports errors.
 * https://github.com/JakeWharton/timber/blob/master/timber-sample/src/main/java/com/example/timber/ExampleApp.java
 */

public class CrashReportingTree extends Timber.Tree {
    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return;
        }

        Crashlytics.log(priority, tag, message);

        if (t != null) {
            if (priority == Log.ERROR) {
                Crashlytics.logException(t);
            }
//            else if (priority == Log.WARN) {
//                Crashlytics.log.logWarning(t);
//            }
        }

        // also, raise an event for the UI to show the message.
        String uiMessage = message;
        if (t != null) {
            uiMessage = "Error: " + t.getMessage();
        }
        EventBus.getDefault().post(new ErrorRaisedEvent(uiMessage));
    }
}

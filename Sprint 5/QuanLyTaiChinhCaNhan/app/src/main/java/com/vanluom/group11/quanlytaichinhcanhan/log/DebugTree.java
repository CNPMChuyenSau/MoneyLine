
package com.vanluom.group11.quanlytaichinhcanhan.log;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import timber.log.Timber;

/**
 * Debug logger that also posts an event for displaying the error to the UI.
 */

public class DebugTree
    extends Timber.DebugTree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (priority >= Log.WARN) {
            String uiMessage = message.split("\\n")[0];
            if (t != null) {
                String exceptionMessage = t.getMessage();
                if (exceptionMessage != null) uiMessage = "Error: " + exceptionMessage;
            }
            // send to UI if there are any subscribers.
            if (EventBus.getDefault().hasSubscriberForEvent(ErrorRaisedEvent.class)) {
                EventBus.getDefault().post(new ErrorRaisedEvent(uiMessage));
            }
        }

        super.log(priority, tag, message, t);
    }
}

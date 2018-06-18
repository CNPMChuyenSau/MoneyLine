
package com.vanluom.group11.quanlytaichinhcanhan.log;

import android.util.Log;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import timber.log.Timber;

/**
 * Logs errors to a file. Used to debug sync service.
 */

public class FileLoggingTree
        extends Timber.DebugTree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (priority >= Log.WARN) {
            String uiMessage = message;
            if (t != null) {
                uiMessage = "Error: " + t.getMessage();
            }

//            Logger logger = LoggerFactory.getLogger(this.getClass());
//            logger.error(message, t);
        }

        super.log(priority, tag, message, t);
    }
}

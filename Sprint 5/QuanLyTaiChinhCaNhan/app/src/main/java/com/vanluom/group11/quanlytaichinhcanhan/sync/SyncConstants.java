
package com.vanluom.group11.quanlytaichinhcanhan.sync;

/**
 * Constants used in synchronization.
 */
public class SyncConstants {
    // intent action
    public static final String INTENT_ACTION_SYNC = "com.money.manager.ex.sync.action.SYNC";
    public static final String INTENT_ACTION_DOWNLOAD = "com.money.manager.ex.sync.action.DOWNLOAD";
    public static final String INTENT_ACTION_UPLOAD = "com.money.manager.ex.sync.action.UPLOAD";
    // intent extra
    public static final String INTENT_EXTRA_LOCAL_FILE = "SyncServiceIntent:LocalFile";
    public static final String INTENT_EXTRA_REMOTE_FILE = "SyncServiceIntent:RemoteFile";

    public static final int NOTIFICATION_SYNC_IN_PROGRESS = 0xCCCC;
    public static final int NOTIFICATION_SYNC_OPEN_FILE = 0xDDDD;
    public static final int NOTIFICATION_SYNC_ERROR = 3;

    public static final int REQUEST_PERIODIC_SYNC = 0;
    public static final int REQUEST_DELAYED_SYNC = 1;
}

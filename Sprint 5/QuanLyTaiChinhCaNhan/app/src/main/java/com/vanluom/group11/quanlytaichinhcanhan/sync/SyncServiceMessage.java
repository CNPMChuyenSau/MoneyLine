
package com.vanluom.group11.quanlytaichinhcanhan.sync;

/**
 * Message codes implemented as an enum to use in switch statements.
 */

public enum SyncServiceMessage {
    FILE_NOT_CHANGED(0x000),
    DOWNLOAD_COMPLETE(0x000A),
    UPLOAD_COMPLETE(0x000B),
    STARTING_DOWNLOAD(0x000C),
    STARTING_UPLOAD(0x000D),
    NOT_ON_WIFI(0x000E),
    ERROR(0x000F),
    SYNC_DISABLED(1),
    CONFLICT(2);

    SyncServiceMessage(int value) {
        code = value;
    }

    public int code;

    public static SyncServiceMessage parse(int code) {
        for (SyncServiceMessage item : SyncServiceMessage.values()) {
            if (item.code == code) return item;
        }
        return null;
    }
}
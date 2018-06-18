
package com.vanluom.group11.quanlytaichinhcanhan.sync;

/**
 * Cloud storage providers
 */
public enum CloudStorageProviderEnum {
    DROPBOX,
    ONEDRIVE,
    GOOGLEDRIVE,
    BOX;

    public static boolean contains(String name) {
        for (CloudStorageProviderEnum provider : CloudStorageProviderEnum.values()) {
            if (provider.name().equalsIgnoreCase(name)) return true;
        }
        return false;
    }
}

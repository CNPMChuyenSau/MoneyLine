package com.vanluom.group11.quanlytaichinhcanhan.home;

import android.text.TextUtils;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDate;

import java.io.File;

/**
 * An entry in the recent databases list.
 */
public class DatabaseMetadata {

    public String localPath;
    public boolean isLocalFileChanged;
    // todo sync provider
    // SyncAdapterType
    public String remotePath;
    public String remoteLastChangedDate;

    public String getFileName() {
        if (TextUtils.isEmpty(this.localPath)) return "";

        File file = new File(this.localPath);
        return file.getName();
    }

    public boolean isSynchronised() {
        return !TextUtils.isEmpty(remotePath);
    }

    public void setRemoteLastChangedDate(MmxDate value) {
        if (value == null) {
            this.remoteLastChangedDate = null;
        } else {
            this.remoteLastChangedDate = value.toString(Constants.ISO_8601_FORMAT);
        }
    }
}


package com.vanluom.group11.quanlytaichinhcanhan.sync;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.core.UIHelper;
import com.vanluom.group11.quanlytaichinhcanhan.home.RecentDatabasesProvider;
import com.vanluom.group11.quanlytaichinhcanhan.sync.events.DbFileDownloadedEvent;
import com.vanluom.group11.quanlytaichinhcanhan.utils.DialogUtils;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import dagger.Lazy;
import timber.log.Timber;

/**
 * Handler for the messages received from the sync service.
 * Updates the UI based on the messages received. The messages state the progress of the
 * synchronisation.
 */

public class SyncServiceMessageHandler
    extends Handler {

    public SyncServiceMessageHandler(Context context, ProgressDialog progressDialog, String remoteFile) {
        MoneyManagerApplication.getApp().iocComponent.inject(this);

        this.context = context;
        this.progressDialog = progressDialog;
        this.remoteFile = remoteFile;
    }

    @Inject Lazy<RecentDatabasesProvider> mDatabases;
    private Context context;
    private ProgressDialog progressDialog;
    private String remoteFile;

    @Override
    public void handleMessage(Message msg) {
        SyncServiceMessage message = SyncServiceMessage.parse(msg.what);
        if (message == null) return;

        switch (message) {
            case NOT_ON_WIFI:
                //showMessage();
                closeDialog(progressDialog);
                break;

            case FILE_NOT_CHANGED:
                // close binaryDialog
                closeDialog(progressDialog);
                new UIHelper(getContext()).showToast(R.string.database_is_synchronized, Toast.LENGTH_LONG);
                break;

            case STARTING_DOWNLOAD:
                // Show progressbar only on download.
                showProgressDialog();

                new UIHelper(getContext()).showToast(R.string.sync_downloading, Toast.LENGTH_LONG);
                break;

            case DOWNLOAD_COMPLETE:
//                storeRecentDb(remoteFile);
                // close binaryDialog
                closeDialog(progressDialog);
                // Notify whoever is interested.
                EventBus.getDefault().post(new DbFileDownloadedEvent());
                break;

            case STARTING_UPLOAD:
                // Do not block the user if uploading the changes.
                closeDialog(progressDialog);
                new UIHelper(getContext()).showToast(R.string.sync_uploading, Toast.LENGTH_LONG);
                break;

            case UPLOAD_COMPLETE:
                // close binaryDialog
                closeDialog(progressDialog);
                new UIHelper(getContext()).showToast(R.string.upload_file_complete, Toast.LENGTH_LONG);
                break;

            case CONFLICT:
                closeDialog(progressDialog);
                new UIHelper(getContext()).showToast(R.string.both_files_modified);
                // todo Show the conflict notification.

                break;

            case ERROR:
                closeDialog(progressDialog);
                new UIHelper(getContext()).showToast(R.string.error, Toast.LENGTH_SHORT);
                break;

            default:
                throw new RuntimeException("unknown message");
        }
    }

    public Context getContext() {
        return this.context;
    }

    private void closeDialog(ProgressDialog progressDialog) {
        if (progressDialog != null && progressDialog.isShowing()) {
            DialogUtils.closeProgressDialog(progressDialog);
        }
    }

    private RecentDatabasesProvider getDatabases() {
        return mDatabases.get();
    }

    private void showProgressDialog() {
        if (progressDialog == null) return;

        try {
            progressDialog.show();
        } catch (Exception e) {
            Timber.e(e, "showing progress dialog on sync.");
        }
    }

//    private void storeRecentDb(String remoteFile) {
//        String localPath = MoneyManagerApplication.getDatabasePath(getContext());
//
//        DatabaseMetadata entry = getDatabases().get(localPath);
//        if (entry == null) {
//            entry = DatabaseMetadataFactory.getInstance(localPath, remoteFile);
//        }
//
//        getDatabases().add(entry);
//    }

}
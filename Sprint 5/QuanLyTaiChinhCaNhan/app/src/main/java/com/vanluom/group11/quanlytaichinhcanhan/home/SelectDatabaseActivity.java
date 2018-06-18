
package com.vanluom.group11.quanlytaichinhcanhan.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;
import com.vanluom.group11.quanlytaichinhcanhan.core.IntentFactory;
import com.vanluom.group11.quanlytaichinhcanhan.core.RequestCodes;
import com.vanluom.group11.quanlytaichinhcanhan.core.UIHelper;
import com.vanluom.group11.quanlytaichinhcanhan.settings.AppSettings;
import com.vanluom.group11.quanlytaichinhcanhan.settings.SyncPreferencesActivity;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDatabaseUtils;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxFileUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.Lazy;
import timber.log.Timber;

public class SelectDatabaseActivity
    extends MmxBaseFragmentActivity {

//    public static final int REQUEST_PICKFILE = 1;

    @Inject Lazy<RecentDatabasesProvider> mDatabasesLazy;

    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_database);

        MoneyManagerApplication.getApp().iocComponent.inject(this);

        ButterKnife.bind(this);

        // Request external storage permissions for Android 6+.
        MmxFileUtils fileUtils = new MmxFileUtils(this);
        fileUtils.requestExternalStoragePermissions(this);

        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RequestCodes.SELECT_FILE:
                if (resultCode != RESULT_OK) return;
                String selectedPath = UIHelper.getSelectedFile(data);
                if(TextUtils.isEmpty(selectedPath)) {
                    new UIHelper(this).showToast(R.string.invalid_database);
                    return;
                }

                onDatabaseSelected(selectedPath);
                break;
        }
    }

    // Permissions

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // cancellation
        //if (permissions.length == 0) return;
        Timber.d("returning from permissions request"); // permissions
    }

    @OnClick(R.id.createDatabaseButton)
    void onCreateDatabaseClick() {
        // show the create database screen
        Intent intent = new Intent(this, CreateDatabaseActivity.class);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
    }

    @OnClick(R.id.openDatabaseButton)
    void onOpenDatabaseClick() {
        MmxDatabaseUtils dbUtils = new MmxDatabaseUtils(this);
        String dbDirectory = dbUtils.getDefaultDatabaseDirectory();

        // show the file picker
        try {
            UIHelper.pickFileDialog(this, dbDirectory, RequestCodes.SELECT_FILE);
        } catch (Exception e) {
            Timber.e(e, "opening file picker");
        }
    }

    @OnClick(R.id.setupSyncButton)
    void onSetupSyncClick() {
        Intent intent = new Intent(this, SyncPreferencesActivity.class);
        startActivity(intent);
    }

    private void onDatabaseSelected(String dbPath) {
        // check if the file is a valid database
//        MmxDatabaseUtils dbUtils = new MmxDatabaseUtils(this);
        if (!MmxDatabaseUtils.isValidDbFile(dbPath)) {
            new UIHelper(this).showToast(R.string.invalid_database);
            return;
        }

        // store db setting
        new AppSettings(this).getDatabaseSettings().setDatabasePath(dbPath);
        // Add the current db to the recent db list.
        DatabaseMetadata currentDb = mDatabasesLazy.get().getCurrent();
        mDatabasesLazy.get().add(currentDb);

        // open the main activity
        Intent intent = IntentFactory.getMainActivityNew(this);
        startActivity(intent);
    }
}

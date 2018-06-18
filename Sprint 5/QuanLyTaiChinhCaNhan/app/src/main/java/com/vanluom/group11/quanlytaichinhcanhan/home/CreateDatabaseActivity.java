
package com.vanluom.group11.quanlytaichinhcanhan.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;
import com.vanluom.group11.quanlytaichinhcanhan.core.IntentFactory;
import com.vanluom.group11.quanlytaichinhcanhan.core.UIHelper;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDatabaseUtils;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoButton;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateDatabaseActivity
    extends MmxBaseFragmentActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.runButton) RobotoButton runButton;
    @BindView(R.id.dbPathTextView) RobotoTextView dbPathTextView;
    @BindView(R.id.statusReport) LinearLayout statusReportView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_database);

        ButterKnife.bind(this);
        getToolbar().setSubtitle(R.string.create_db);

        runButton.setEnabled(false);

        createDatabase();

        // todo Create account. Allow multiple times.
        // todo Default account. When the first account is created, use that. Allow changing if multiple accounts are created.
        // todo Default currency. Check if set on db creation. Set after the first account and allow changing.
    }

    private void createDatabase() {
        MmxDatabaseUtils dbUtils = new MmxDatabaseUtils(this);

        // Show the default db directory in case of errors.
        String defaultDbPath = dbUtils.getDefaultDatabasePath();
        dbPathTextView.setText(defaultDbPath);

        // Create database file.
        String dbPath = dbUtils.createDatabase();
        if (TextUtils.isEmpty(dbPath)) return;

        DatabaseMetadata db = DatabaseMetadataFactory.getInstance(dbPath);
        dbUtils.useDatabase(db);

        // show message

        statusReportView.setVisibility(View.VISIBLE);
        new UIHelper(this).showToast(R.string.create_db_success);
        dbPathTextView.setText(dbPath);

        // enable run button
        runButton.setEnabled(true);
    }

    @OnClick(R.id.runButton)
    void onRunClick() {
        // Open the main activity.
        Intent intent = IntentFactory.getMainActivityNew(this);
        startActivity(intent);

        finish();
    }

}

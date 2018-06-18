package com.vanluom.group11.quanlytaichinhcanhan.reports;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;

public class PayeesReportActivity
        extends MmxBaseFragmentActivity {

    public boolean mIsDualPanel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_chart_fragments_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            // set actionbar
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //check if is dual panel
        mIsDualPanel = findViewById(R.id.fragmentChart) != null;

        PayeeReportFragment fragment = new PayeeReportFragment();
        FragmentManager fm = getSupportFragmentManager();

        if (fm.findFragmentById(R.id.fragmentMain) == null) {
            fm.beginTransaction().add(R.id.fragmentMain, fragment, PayeeReportFragment.class.getSimpleName()).commit();
        }
    }

}

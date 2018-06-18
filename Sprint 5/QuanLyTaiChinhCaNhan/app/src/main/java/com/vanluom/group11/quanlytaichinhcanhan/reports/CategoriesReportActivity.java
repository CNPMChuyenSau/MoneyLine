package com.vanluom.group11.quanlytaichinhcanhan.reports;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;

public class CategoriesReportActivity
    extends MmxBaseFragmentActivity {
    
    public static final String REPORT_FILTERS = "CategoriesReportActivity:Filter";
    public static final String REPORT_TITLE = "CategoriesReportActivity:Title";
    public boolean mIsDualPanel = false;
    public String mFilter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null) {
            if (!TextUtils.isEmpty(getIntent().getStringExtra(REPORT_FILTERS))) {
                mFilter = getIntent().getStringExtra(REPORT_FILTERS);
            }
            if (!TextUtils.isEmpty(getIntent().getStringExtra(REPORT_TITLE))) {
                setTitle(getIntent().getStringExtra(REPORT_TITLE));
            }
        }

        setContentView(R.layout.report_chart_fragments_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            // set actionbar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //check if is dual panel
        mIsDualPanel = findViewById(R.id.fragmentChart) != null;

        //create a fragment
        CategoriesReportFragment fragment = new CategoriesReportFragment();
        FragmentManager fm = getSupportFragmentManager();
        //insert fragment
        if (fm.findFragmentById(R.id.fragmentMain) == null) {
            fm.beginTransaction()
                .add(R.id.fragmentMain, fragment, CategoriesReportFragment.class.getSimpleName())
                .commit();
        }
    }
}

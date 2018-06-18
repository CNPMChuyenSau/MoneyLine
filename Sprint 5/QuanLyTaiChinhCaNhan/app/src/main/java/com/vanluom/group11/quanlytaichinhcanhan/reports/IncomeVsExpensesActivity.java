package com.vanluom.group11.quanlytaichinhcanhan.reports;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;

public class IncomeVsExpensesActivity extends MmxBaseFragmentActivity {

    public static final int SUBTOTAL_MONTH = 99;

    public boolean mIsDualPanel = false;
    private IncomeVsExpensesListFragment listFragment = new IncomeVsExpensesListFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_chart_fragments_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            // set actionbar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // check if is dual panel
        mIsDualPanel = findViewById(R.id.fragmentChart) != null;

        FragmentManager fm = getSupportFragmentManager();
        // attach fragment activity
        if (fm.findFragmentById(R.id.fragmentMain) == null) {
            fm.beginTransaction()
                    .replace(R.id.fragmentMain, listFragment, IncomeVsExpensesListFragment.class.getSimpleName())
                    .commit();
        }
    }
}

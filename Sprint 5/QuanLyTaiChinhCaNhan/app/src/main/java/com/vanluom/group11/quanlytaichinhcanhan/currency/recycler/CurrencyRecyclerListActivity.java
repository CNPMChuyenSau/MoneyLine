
package com.vanluom.group11.quanlytaichinhcanhan.currency.recycler;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;

public class CurrencyRecyclerListActivity
    extends MmxBaseFragmentActivity {

    private static final String FRAGMENTTAG = CurrencyRecyclerListFragment.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.base_toolbar_activity);

        // change home icon to 'back'.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CurrencyRecyclerListFragment fragment = CurrencyRecyclerListFragment.createInstance();
        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.content) == null) {
            fm.beginTransaction().add(R.id.content, fragment, FRAGMENTTAG).commit();
        }
    }
}

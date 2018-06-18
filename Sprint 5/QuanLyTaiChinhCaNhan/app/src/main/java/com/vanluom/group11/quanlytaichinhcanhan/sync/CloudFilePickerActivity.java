
package com.vanluom.group11.quanlytaichinhcanhan.sync;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;

public class CloudFilePickerActivity
    extends MmxBaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_toolbar_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentManager fm = getSupportFragmentManager();

        // intent
//        if (getIntent() != null && getIntent().getExtras().containsKey(INTENT_DROBPOXFILE_PATH)) {
//            dropboxFile = getIntent().getExtras().getString(INTENT_DROBPOXFILE_PATH);
//        }

        // attach fragment to activity
        if (fm.findFragmentById(R.id.content) == null) {
            if (fm.findFragmentByTag(CloudFilePickerFragment.class.getSimpleName()) == null) {
                CloudFilePickerFragment fragment = new CloudFilePickerFragment();
                fm.beginTransaction().add(R.id.content, fragment,
                        CloudFilePickerFragment.class.getSimpleName()).commit();
            }
        }
    }
}

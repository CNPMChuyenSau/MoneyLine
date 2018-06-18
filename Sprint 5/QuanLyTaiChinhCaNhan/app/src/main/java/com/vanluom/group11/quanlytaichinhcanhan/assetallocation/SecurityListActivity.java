package com.vanluom.group11.quanlytaichinhcanhan.assetallocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;

public class SecurityListActivity
    extends MmxBaseFragmentActivity {

    public static final String EXTRA_ASSET_CLASS_ID = "assetClassId";
    private static final String FRAGMENTTAG = SecurityListFragment.class.getSimpleName() + "_Fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_security_list);
        setContentView(R.layout.base_toolbar_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get asset class id
        Integer assetClassId = getAssetClassId();
        showFragmentForAssetClass(assetClassId);
    }

    private Integer getAssetClassId() {
        Intent intent = getIntent();
        if (intent == null) return null;

        int assetClassId = intent.getIntExtra(EXTRA_ASSET_CLASS_ID, Constants.NOT_SET);
        if (assetClassId == Constants.NOT_SET) return null;

        return assetClassId;
    }

    private void showFragmentForAssetClass(Integer assetClassId) {
        if (assetClassId == null) return;

        SecurityListFragment listFragment = SecurityListFragment.create(assetClassId);

        Intent intent = getIntent();
        if (intent != null && !(TextUtils.isEmpty(intent.getAction()))) {
            listFragment.action = intent.getAction();
        }
        FragmentManager fm = getSupportFragmentManager();
        // attach fragment to activity
        if (fm.findFragmentById(R.id.content) == null) {
            fm.beginTransaction().add(R.id.content, listFragment, FRAGMENTTAG).commit();
        }
    }
}

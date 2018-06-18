package com.vanluom.group11.quanlytaichinhcanhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;
import com.vanluom.group11.quanlytaichinhcanhan.common.BaseListFragment;
import com.vanluom.group11.quanlytaichinhcanhan.fragment.PayeeListFragment;

public class PayeeActivity
    extends MmxBaseFragmentActivity {

    public static final String INTENT_RESULT_PAYEEID = "PayeeActivity:PayeeId";
    public static final String INTENT_RESULT_PAYEENAME = "PayeeActivity:PayeeName";
    @SuppressWarnings("unused")
//    private static final String LOGCAT = PayeeActivity.class.getSimpleName();
    private static final String FRAGMENTTAG = PayeeActivity.class.getSimpleName() + "_Fragment";

    PayeeListFragment listFragment = new PayeeListFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_toolbar_activity);

        // enable home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // process intent
        Intent intent = getIntent();
        String action = intent.getAction();

        if (!TextUtils.isEmpty(action)) {
            PayeeListFragment.mAction = action;
        }
        FragmentManager fm = getSupportFragmentManager();
        // attach fragment activity
        if (fm.findFragmentById(R.id.content) == null) {
            // todo: use .replace
            fm.beginTransaction()
                .add(R.id.content, listFragment, FRAGMENTTAG)
                .commit();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // set result
            BaseListFragment fragment = (BaseListFragment) getSupportFragmentManager()
                    .findFragmentByTag(FRAGMENTTAG);
            if (fragment != null) {
                fragment.getActivity().setResult(RESULT_CANCELED);
                fragment.getActivity().finish();
            }
        }
        return super.onKeyUp(keyCode, event);
    }
}

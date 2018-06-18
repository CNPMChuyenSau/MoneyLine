package com.vanluom.group11.quanlytaichinhcanhan.currency.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;
import com.vanluom.group11.quanlytaichinhcanhan.core.AnswersEvents;
import com.vanluom.group11.quanlytaichinhcanhan.utils.ActivityUtils;

/**
 * List of currencies.
 */
public class CurrencyListActivity
    extends MmxBaseFragmentActivity {

    public static final String INTENT_RESULT_CURRENCYID = "CurrencyListActivity:ACCOUNTID";
    public static final String INTENT_RESULT_CURRENCYNAME = "CurrencyListActivity:ACCOUNTNAME";
    private static final String FRAGMENTTAG = CurrencyListActivity.class.getSimpleName() + "_Fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.base_toolbar_activity);

        // change home icon to 'back'.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CurrencyListFragment fragment = new CurrencyListFragment();

        Intent intent = getIntent();
        if (intent != null && !(TextUtils.isEmpty(intent.getAction()))) {
            // restore previous device orientation if it was modified.
            if(fragment.mPreviousOrientation != Constants.NOT_SET) {
                int currentOrientation = ActivityUtils.forceCurrentOrientation(this);
                if(currentOrientation != fragment.mPreviousOrientation) {
                    ActivityUtils.restoreOrientation(this, fragment.mPreviousOrientation);
                }
            }
        }

        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.content) == null) {
            fm.beginTransaction().add(R.id.content, fragment, FRAGMENTTAG).commit();
        }

        Answers.getInstance().logCustom(new CustomEvent(AnswersEvents.CurrencyList.name()));
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // intercept key back
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            CurrencyListFragment fragment = (CurrencyListFragment)
                    getSupportFragmentManager().findFragmentByTag(FRAGMENTTAG);
            if (fragment != null) {
                fragment.setResultAndFinish();
            }
        }
        return super.onKeyUp(keyCode, event);
    }

}
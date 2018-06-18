package com.vanluom.group11.quanlytaichinhcanhan.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;
import com.vanluom.group11.quanlytaichinhcanhan.core.AnswersEvents;

/**
 * Account list activity.
 */
public class AccountListActivity
    extends MmxBaseFragmentActivity {

    public static final String INTENT_RESULT_ACCOUNTID = "AccountListActivity:ACCOUNTID";
    public static final String INTENT_RESULT_ACCOUNTNAME = "AccountListActivity:ACCOUNTNAME";
    private static final String FRAGMENTTAG = AccountListActivity.class.getSimpleName() + "_Fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.base_toolbar_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AccountListFragment listFragment = new AccountListFragment();
        Intent intent = getIntent();
        if (intent != null && !(TextUtils.isEmpty(intent.getAction()))) {
            listFragment.mAction = intent.getAction();
        }
        FragmentManager fm = getSupportFragmentManager();
        // attach fragment to activity
        if (fm.findFragmentById(R.id.content) == null) {
            fm.beginTransaction().add(R.id.content, listFragment, FRAGMENTTAG).commit();
        }

        Answers.getInstance().logCustom(new CustomEvent(AnswersEvents.AccountList.name()));
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AccountListFragment fragment = (AccountListFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENTTAG);
            if (fragment != null) {
                fragment.setResultAndFinish();
            }
        }
        return super.onKeyUp(keyCode, event);
    }
}

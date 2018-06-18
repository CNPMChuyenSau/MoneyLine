package com.vanluom.group11.quanlytaichinhcanhan.investment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;
import com.vanluom.group11.quanlytaichinhcanhan.core.AnswersEvents;

public class PortfolioActivity
    extends MmxBaseFragmentActivity {

    private static final String FRAGMENT_TAG = PortfolioFragment.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_portfolio);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDisplayHomeAsUpEnabled(true);

        // todo: pass the correct account id.
//        Intent intent = getIntent();
        // todo: action
//        if (intent != null && !(TextUtils.isEmpty(intent.getAction()))) {
//            listFragment.mAction = intent.getAction();
//        }
        FragmentManager fm = getSupportFragmentManager();
        // attach fragment to activity
        if (fm.findFragmentById(R.id.content) == null) {
            PortfolioFragment listFragment = PortfolioFragment.newInstance(Constants.NOT_SET);
            fm.beginTransaction().add(R.id.content, listFragment, FRAGMENT_TAG).commit();
        }

        Answers.getInstance().logCustom(new CustomEvent(AnswersEvents.Portfolio.name()));
    }


}

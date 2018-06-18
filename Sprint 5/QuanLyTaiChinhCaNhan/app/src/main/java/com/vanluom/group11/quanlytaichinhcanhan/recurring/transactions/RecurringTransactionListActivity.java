package com.vanluom.group11.quanlytaichinhcanhan.recurring.transactions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.vanluom.group11.quanlytaichinhcanhan.PasscodeActivity;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.core.AnswersEvents;
import com.vanluom.group11.quanlytaichinhcanhan.core.Passcode;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;

/**
 * Not used.
 */
public class RecurringTransactionListActivity
    extends MmxBaseFragmentActivity {

    public static final String INTENT_EXTRA_LAUNCH_NOTIFICATION = "RecurringTransactionListActivity:LaunchNotification";
    public static final int INTENT_REQUEST_PASSCODE = 2;
    private static final String FRAGMENTTAG = RecurringTransactionListActivity.class.getSimpleName() + "_Fragment";

    private RecurringTransactionListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_toolbar_activity);

        // check if launch from notification
        if (getIntent() != null && getIntent().getBooleanExtra(INTENT_EXTRA_LAUNCH_NOTIFICATION, false)) {
            Passcode passcode = new Passcode(getApplicationContext());
            if (passcode.hasPasscode()) {
                Intent intent = new Intent(this, PasscodeActivity.class);
                // set action and data
                intent.setAction(PasscodeActivity.INTENT_REQUEST_PASSWORD);
                intent.putExtra(PasscodeActivity.INTENT_MESSAGE_TEXT, getString(R.string.enter_your_passcode));
                // start activity
                startActivityForResult(intent, INTENT_REQUEST_PASSCODE);
            }
        }
        // set actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // set fragment and fragment manager
        FragmentManager fm = getSupportFragmentManager();
        listFragment = new RecurringTransactionListFragment();
        // attach fragment on activity
        if (fm.findFragmentById(R.id.content) == null) {
            fm.beginTransaction().add(R.id.content, listFragment, FRAGMENTTAG).commit();
        }

        Answers.getInstance().logCustom(new CustomEvent(AnswersEvents.RecurringTransactionList.name()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check request code
        switch (requestCode) {
            case INTENT_REQUEST_PASSCODE:
                boolean isAuthenticated = false;
                if (resultCode == RESULT_OK && data != null) {
                    Passcode passcode = new Passcode(getApplicationContext());
                    String passIntent = data.getStringExtra(PasscodeActivity.INTENT_RESULT_PASSCODE);
                    String passDb = passcode.getPasscode();
                    if (passIntent != null && passDb != null) {
                        isAuthenticated = passIntent.equals(passDb);
                        if (!isAuthenticated) {
                            Toast.makeText(getApplicationContext(), R.string.passocde_no_macth, Toast.LENGTH_LONG).show();
                        }
                    }
                }
                // close if not authenticated
                if (!isAuthenticated) {
                    this.finish();
                }
        }
    }
}

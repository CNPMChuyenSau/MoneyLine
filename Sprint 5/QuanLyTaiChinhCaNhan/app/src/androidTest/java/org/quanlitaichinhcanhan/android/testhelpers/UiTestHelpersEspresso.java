package org.quanlitaichinhcanhan.android.testhelpers;

import android.content.Context;
import android.preference.PreferenceManager;

import com.vanluom.group11.quanlytaichinhcanhan.settings.PreferenceConstants;

public class UiTestHelpersEspresso {

    public UiTestHelpersEspresso() {
    }

    public void clearPreferences(Context context) {
        // clear default preferences
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().commit();

        clearDropboxPreferences(context);
    }

    public void clearDropboxPreferences(Context context) {
        context.getSharedPreferences(PreferenceConstants.PREF_DROPBOX_ACCOUNT_PREFS_NAME, Context.MODE_PRIVATE)
                .edit().clear().commit();
    }

//    public void clickOnNeutralDialogButton() {
//        // Positive = 1
//        // Negative = 2
//        // Neutral = 3
//        int buttonId = android.R.id.button3;
//        clickOnView(buttonId);
//    }
//
//    public void clickOnPositiveDialogButton() {
//        // Positive = 1
//        int buttonId = android.R.id.button1;
//        clickOnView(buttonId);
//    }

//    public void clickOnView(int viewId) {
//        solo.clickOnView(solo.getView(viewId));
//    }

//    public void clickOnMaterialDialogButton(DialogButtons button) {
//        int viewId = Constants.NOT_SET;
//
//        switch (button) {
//            case POSITIVE:
//                viewId = R.id.buttonDefaultPositive;
//                break;
//            case NEGATIVE:
//                viewId = R.id.buttonDefaultNegative;
//                break;
//            case NEUTRAL:
//                viewId = R.id.buttonDefaultNeutral;
//                break;
//        }
//        clickOnView(viewId);
//    }
}
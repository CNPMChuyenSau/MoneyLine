
package com.vanluom.group11.quanlytaichinhcanhan.transactions;

import android.app.AlertDialog;
import android.support.v4.app.DialogFragment;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.vanluom.group11.quanlytaichinhcanhan.transactions.events.DialogNegativeClickedEvent;
import com.vanluom.group11.quanlytaichinhcanhan.transactions.events.DialogPositiveClickedEvent;

import org.greenrobot.eventbus.EventBus;

/**
 */
public class YesNoDialog
    extends DialogFragment {

    public static final String PURPOSE_DELETE_SPLITS_WHEN_SWITCHING_TO_TRANSFER = "delete-splits";

    public YesNoDialog() {

    }

    /**
     * Here we store the identifier in which context the binaryDialog is used.
     * Since this binaryDialog can be used for any binary outcome, there needs to be a way
     * to distinguish which one it is handling.
     * This is used in the caller to distinguish which action to take in case there are
     * multiple instances of yes-no binaryDialog.
     * If there is only one then it does not need to be used.
     */
    private String mPurpose;

    public String getPurpose() {
        return mPurpose;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle args = getArguments();
        String title = args.getString("title", "");
        String message = args.getString("message", "");
        this.mPurpose = args.getString("purpose", "");

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        EventBus.getDefault().post(new DialogPositiveClickedEvent());
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        EventBus.getDefault().post(new DialogNegativeClickedEvent());
                    }
                })
                .create();
    }
}

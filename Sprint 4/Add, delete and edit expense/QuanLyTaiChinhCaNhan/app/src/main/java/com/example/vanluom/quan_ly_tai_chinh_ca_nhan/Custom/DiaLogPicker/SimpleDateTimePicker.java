package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Custom.DiaLogPicker;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Date;

public class SimpleDateTimePicker {
    private CharSequence mDialogTitle;
    private Date mInitDate;
    private DateTimePicker.OnDateTimeSetListener mOnDateTimeSetListener;
    private FragmentManager mFragmentManager;

    private SimpleDateTimePicker(CharSequence dialogTitle, Date initDate,
                                 DateTimePicker.OnDateTimeSetListener onDateTimeSetListener,
                                 FragmentManager fragmentManager) {
        FragmentTransaction mFragmentTransaction = fragmentManager.beginTransaction();

        Fragment mDateTimeDialogFrag = fragmentManager.findFragmentByTag(
                DateTimePicker.TAG_FRAG_DATE_TIME
        );

        if (mDateTimeDialogFrag != null) {
            mFragmentTransaction.remove(mDateTimeDialogFrag);
        }

        mFragmentTransaction.addToBackStack(null);
        mDialogTitle = dialogTitle;
        mInitDate = initDate;
        mOnDateTimeSetListener = onDateTimeSetListener;
        mFragmentManager = fragmentManager;
    }

    public static SimpleDateTimePicker make(CharSequence dialogTitle, Date initDate,
                                            DateTimePicker.OnDateTimeSetListener onDateTimeSetListener,
                                            FragmentManager fragmentManager) {

        return new SimpleDateTimePicker(dialogTitle, initDate,
                onDateTimeSetListener, fragmentManager);

    }

    public void Show() {
        DateTimePicker mDateTimePicker = DateTimePicker.newInstance(mDialogTitle, mInitDate);
        mDateTimePicker.setOnDateTimeSetListener(mOnDateTimeSetListener);
        mDateTimePicker.show(mFragmentManager, DateTimePicker.TAG_FRAG_DATE_TIME);

    }
}

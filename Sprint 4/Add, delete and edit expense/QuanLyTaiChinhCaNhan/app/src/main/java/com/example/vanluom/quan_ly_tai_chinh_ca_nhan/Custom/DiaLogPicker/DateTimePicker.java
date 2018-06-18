package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Custom.DiaLogPicker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Control.DateTime;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;

import java.util.Date;
import java.util.Objects;

public class DateTimePicker extends DialogFragment {
    public static final String TAG_FRAG_DATE_TIME = "fragDateTime";
    private static final String KEY_DIALOG_TITLE = "dialogTitle";
    private static final String KEY_INIT_DATE = "initDate";
    private static final String TAG_DATE = "date";
    private static final String TAG_TIME = "time";
    private Context mContext;
    private ButtonClickListener mButtonClickListener;
    private OnDateTimeSetListener mOnDateTimeSetListener;
    private Bundle mArgument;
    private DatePicker mDatePicker;
    private TimePicker mTimePicker;
    View v;

    public DateTimePicker() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mButtonClickListener = new ButtonClickListener();
    }

    public static DateTimePicker newInstance(CharSequence dialogTitle, Date initDate) {
        DateTimePicker mDateTimePicker = new DateTimePicker();
        Bundle mBundle = new Bundle();
        mBundle.putCharSequence(KEY_DIALOG_TITLE, dialogTitle);
        mBundle.putSerializable(KEY_INIT_DATE, initDate);
        mDateTimePicker.setArguments(mBundle);
        return mDateTimePicker;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mArgument = getArguments();
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
        mBuilder.setNegativeButton(android.R.string.no, mButtonClickListener);
        mBuilder.setPositiveButton(android.R.string.yes, mButtonClickListener);
        AlertDialog mDialog = mBuilder.create();
        Objects.requireNonNull(mDialog.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);

        mDialog.setView(
                createDateTimeView(mDialog.getLayoutInflater())
        );
        return mDialog;
    }


    private View createDateTimeView(LayoutInflater layoutInflater) {
        View mView = layoutInflater.inflate(R.layout.date_time_picker, null);
        Button btnNow = (Button) mView.findViewById(R.id.btnNow);
        final TextView txtDate = (TextView) mView.findViewById(R.id.text_view_date);

        TabHost mTabHost = (TabHost) mView.findViewById(R.id.tab_host);
        mTabHost.setup();
        TabHost.TabSpec mDateTab = mTabHost.newTabSpec(TAG_DATE);
        mDateTab.setIndicator(getString(R.string.tab_date));
        mDateTab.setContent(R.id.date_content);
        mTabHost.addTab(mDateTab);
        TabHost.TabSpec mTimeTab = mTabHost.newTabSpec(TAG_TIME);
        mTimeTab.setIndicator(getString(R.string.tab_time));
        mTimeTab.setContent(R.id.time_content);
        mTabHost.addTab(mTimeTab);
        final DateTime mDateTime = new DateTime((Date) mArgument.getSerializable(KEY_INIT_DATE));
        mDatePicker = (DatePicker) mView.findViewById(R.id.date_picker);
        mTimePicker = (TimePicker) mView.findViewById(R.id.time_picker);
        mTimePicker.setCurrentHour(mDateTime.getHourOfDay());
        mTimePicker.setCurrentMinute(mDateTime.getMinuteOfHour());
        mDatePicker.init(mDateTime.getYear(), mDateTime.getMonthOfYear(),
                mDateTime.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                        txtDate.setText(i + "-" + (i1 + 1) + "-" + i2 + " " + mTimePicker.getCurrentHour() + ":" + mTimePicker.getCurrentMinute());
                    }
                });

        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                txtDate.setText(mDatePicker.getYear() + "-" + (mDatePicker.getMonth() + 1) + "-"
                        + mDatePicker.getDayOfMonth() + " " + mTimePicker.getCurrentHour() + ":" + mTimePicker.getCurrentMinute());
            }
        });
        btnNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatePicker.init(mDateTime.getYear(), mDateTime.getMonthOfYear(),
                        mDateTime.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
                            @Override
                            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                                txtDate.setText(i + "-" + (i1 + 1) + "-" + i2 + " " + mTimePicker.getCurrentHour() + ":" + mTimePicker.getCurrentMinute());
                            }
                        });
                mTimePicker.setCurrentHour(mDateTime.getHourOfDay());
                mTimePicker.setCurrentMinute(mDateTime.getMinuteOfHour());
                txtDate.setText(mDatePicker.getYear() + "-" + (mDatePicker.getMonth() + 1) + "-" +
                        mDatePicker.getDayOfMonth() + " " + mTimePicker.getCurrentHour() + ":"
                        + mTimePicker.getCurrentMinute());
            }
        });
        txtDate.setText(mDatePicker.getYear() + "-" + (mDatePicker.getMonth() + 1) + "-" +
                mDatePicker.getDayOfMonth() + " " + mTimePicker.getCurrentHour() +
                ":" + mTimePicker.getCurrentMinute());
        return mView;
    }

    public void setOnDateTimeSetListener(OnDateTimeSetListener onDateTimeSetListener) {
        mOnDateTimeSetListener = onDateTimeSetListener;
    }

    private class ButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int result) {
            if (DialogInterface.BUTTON_POSITIVE == result) {
                DateTime mDateTime = new DateTime(
                        mDatePicker.getYear(),
                        mDatePicker.getMonth(),
                        mDatePicker.getDayOfMonth(),
                        mTimePicker.getCurrentHour(),
                        mTimePicker.getCurrentMinute()
                );
                mOnDateTimeSetListener.DateTimeSet(mDateTime.getDate());
            }
        }
    }

    public interface OnDateTimeSetListener {
        void DateTimeSet(Date date);
    }
}
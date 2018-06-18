package com.vanluom.group11.quanlytaichinhcanhan.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.TextUtils;
import android.widget.TextView;

import com.vanluom.group11.quanlytaichinhcanhan.core.Core;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoView;

import timber.log.Timber;

/**
 */
public class MoneySimpleCursorAdapter
        extends SimpleCursorAdapter {

    private static final String LOGCAT = MoneySimpleCursorAdapter.class.getSimpleName();
    private String mHighlight;
    private Core mCore;

    public MoneySimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);

        mCore = new Core(context);
    }

    @Override
    public void setViewText(TextView v, String text) {
        if (v != null) {
            try {
                v.setTypeface(RobotoView.obtainTypeface(mContext, RobotoView.getUserFont()));
            } catch (Exception e) {
                Timber.e(e, "getting roboto typeface");
            }
            super.setViewText(v, text);
            // check if highlight text
            if (!TextUtils.isEmpty(getHighlightFilter())) {
                v.setText(mCore.highlight(getHighlightFilter(), v.getText().toString()));
            }
        }
    }

    public String getHighlightFilter() {
        return mHighlight;
    }

    public void setHighlightFilter(String mHighlight) {
        this.mHighlight = mHighlight;
    }
}


package com.vanluom.group11.quanlytaichinhcanhan.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * Used to display dates. Allows quickly moving up/down using side arrows.
 */
public class DateDisplay extends LinearLayout {
    public DateDisplay(Context context) {
        super(context);

        initialize(context);
    }

//    public DateDisplay(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//
//        initialize(context);
//    }

    public DateDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);

//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Options, 0, 0);
//        String titleText = a.getString(R.styleable.Options_titleText);
//        int valueColor = a.getColor(R.styleable.Options_valueColor, android.R.color.holo_blue_light);
//        a.recycle();

        initialize(context);
    }

    private void initialize(Context context) {
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.control_datedisplay, this, true);
    }
}

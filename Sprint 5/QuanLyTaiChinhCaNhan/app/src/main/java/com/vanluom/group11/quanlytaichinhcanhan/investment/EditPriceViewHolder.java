
package com.vanluom.group11.quanlytaichinhcanhan.investment;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;

import com.mikepenz.iconics.view.IconicsImageView;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * View Holder pattern for edit price binaryDialog.
 */
public class EditPriceViewHolder {

    @BindView(R.id.amountTextView) public RobotoTextView amountTextView;
    @BindView(R.id.dateTextView) public RobotoTextView dateTextView;
//    @BindView(R.id.dateControl) public DateDisplay dateDisplay;

//    @BindView(R.id.previousDayButton) @Nullable public FontIconView previousDayButton;
    @BindView(R.id.previousDayButton) @Nullable public IconicsImageView previousDayButton;

//    @BindView(R.id.nextDayButton) @Nullable public FontIconView nextDayButton;
    @BindView(R.id.nextDayButton) @Nullable public IconicsImageView nextDayButton;

    public void bind(View view) {
        ButterKnife.bind(this, view);
    }

    public void bind(Activity activity) {
        ButterKnife.bind(this, activity);
    }
}

package com.vanluom.group11.quanlytaichinhcanhan.assetallocation;

import android.view.View;
import android.widget.LinearLayout;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoTextView;

/**
 * View holder for asset allocation / class item in the list.
 */
public class AssetClassViewHolder {

    /**
     * Create and initialize an instance of the holder.
     * Ref: http://developer.android.com/training/improving-layouts/smooth-scrolling.html
     *
     * @param view Container view (.xml)
     * @return Instance of the holder with the UI control references.
     */
    public static AssetClassViewHolder initialize(View view) {
        AssetClassViewHolder instance = new AssetClassViewHolder();

        instance.container = (LinearLayout) view.findViewById(R.id.container);

        instance.assetClassTextView = (RobotoTextView) view.findViewById(R.id.assetClassTextView);
        instance.allocationTextView = (RobotoTextView) view.findViewById(R.id.allocationTextView);
        instance.valueTextView = (RobotoTextView) view.findViewById(R.id.valueTextView);
        instance.currentAllocationTextView = (RobotoTextView) view.findViewById(R.id.currentAllocationTextView);
        instance.currentValueTextView = (RobotoTextView) view.findViewById(R.id.currentValueTextView);
        instance.differenceTextView = (RobotoTextView) view.findViewById(R.id.differenceTextView);
        instance.differencePercentTextView = (RobotoTextView) view.findViewById(R.id.differencePercentTextView);

        return instance;
    }

    public LinearLayout container;
    public RobotoTextView assetClassTextView;
    public RobotoTextView allocationTextView;
    public RobotoTextView valueTextView;
    public RobotoTextView currentAllocationTextView;
    public RobotoTextView currentValueTextView;
    public RobotoTextView differenceTextView;
    public RobotoTextView differencePercentTextView;
}

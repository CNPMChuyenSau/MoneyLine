package com.vanluom.group11.quanlytaichinhcanhan.assetallocation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Common UI methods.
 */
public class UIHelpers {
    /**
     * Populates an asset class row view.
     */
    public static void populateAssetClassRow(AssetClassViewHolder viewHolder, MatrixCursorColumns values) {
        // name
        viewHolder.assetClassTextView.setText(values.name);

        // set allocation
        viewHolder.allocationTextView.setText(values.allocation);

        // set value
        if (viewHolder.valueTextView != null) {
            viewHolder.valueTextView.setText(values.value);
        }

        // current allocation
        viewHolder.currentAllocationTextView.setText(values.currentAllocation);

        // current value
        if (viewHolder.currentValueTextView != null) {
            viewHolder.currentValueTextView.setText(values.currentValue);
        }

        // difference %
        viewHolder.differencePercentTextView.setText(values.differencePercent);
        // difference (value)
        viewHolder.differenceTextView.setText(values.difference);
    }

    public static Fragment getVisibleFragment(FragmentActivity activity){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments == null) return null;

        for(Fragment fragment : fragments){
            if(fragment != null && fragment.isVisible())
                return fragment;
        }
        return null;
    }
}

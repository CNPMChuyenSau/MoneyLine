
package com.vanluom.group11.quanlytaichinhcanhan.assetallocation;

import android.view.View;
import android.widget.TextView;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoTextViewFontIcon;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ViewHolder for Asset Class editing screen.
 */
public class AssetClassEditViewHolder {
    public AssetClassEditViewHolder(View view) {
        // initialize
//        parentAssetClass = (RobotoTextViewFontIcon) view.findViewById(R.id.parentAssetClass);
        ButterKnife.bind(this, view);
    }

    @BindView(R.id.parentAssetClass) RobotoTextViewFontIcon parentAssetClass;
    @BindView(R.id.allocationEdit) TextView allocationTextView;
}

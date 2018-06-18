
package com.vanluom.group11.quanlytaichinhcanhan.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoTextView;

/**
 * View Holder for the Category list group item (Category).
 */
public class CategoryListItemViewHolderGroup {
    public CategoryListItemViewHolderGroup(View view) {
        collapseImageView = (ImageView) view.findViewById(R.id.expandable_list_indicator);
        text1 = (RobotoTextView) view.findViewById(android.R.id.text1);
        selector = (LinearLayout) view.findViewById(R.id.selector);

    }

    public ImageView collapseImageView;
    public RobotoTextView text1;
    public LinearLayout selector;
}

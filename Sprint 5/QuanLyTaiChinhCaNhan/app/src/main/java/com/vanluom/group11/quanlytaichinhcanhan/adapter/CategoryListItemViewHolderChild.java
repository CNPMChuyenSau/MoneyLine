
package com.vanluom.group11.quanlytaichinhcanhan.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoTextView;

/**
 * View Holder for subcategory list item.
 */
public class CategoryListItemViewHolderChild {
    public CategoryListItemViewHolderChild(View view) {
//        textContainer = (ViewGroup) view.findViewById(R.id.textContainer);
        text1 = (RobotoTextView) view.findViewById(android.R.id.text1);
        text2 = (TextView) view.findViewById(android.R.id.text2);
        selector = (LinearLayout) view.findViewById(R.id.selector);
        indent = (ViewGroup) view.findViewById(R.id.indent);
    }

//    public ViewGroup textContainer;
    public RobotoTextView text1;
    public TextView text2;
    public LinearLayout selector;
    public ViewGroup indent;
}

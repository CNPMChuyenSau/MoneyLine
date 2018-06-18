
package com.vanluom.group11.quanlytaichinhcanhan.core;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * Custom adapter for the account list in the transaction list toolbar.
 */
public class ToolbarSpinnerAdapter
    extends SimpleCursorAdapter {
    public ToolbarSpinnerAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);

        mContext = context;
    }

    private Context mContext;

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        View view = super.getDropDownView(position, convertView, parent);

        TextView textView = (TextView) view.findViewById(android.R.id.text1);

//        int textColor = getContext().getResources().getColor(R.color.material_grey_900);
        int textColor = ContextCompat.getColor(getContext(), R.color.material_grey_900);
//        int textColorId = core.getColourFromThemeAttribute(R.attr.toolbar_spinner_item_text_color);
//        int textColorId = core.getColourFromAttribute(R.attr.toolbar_spinner_item_text_color);
//        int textColor = mContext.getResources().getColor(textColorId);
        if (new UIHelper(getContext()).isUsingDarkTheme()) {
            textColor = ContextCompat.getColor(getContext(), R.color.material_grey_50);
//            textColor = mContext.getResources().getColor(R.color.material_grey_50);
        }

        textView.setTextColor(textColor);

        return view;
    }

    public Context getContext() {
        return mContext;
    }
}

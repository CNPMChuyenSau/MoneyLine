package com.vanluom.group11.quanlytaichinhcanhan.reports;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CursorAdapter;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.core.UIHelper;
import com.vanluom.group11.quanlytaichinhcanhan.currency.CurrencyService;
import com.vanluom.group11.quanlytaichinhcanhan.database.ViewMobileData;

import info.javaperformance.money.MoneyFactory;

/**
 * Adapter for the Categories report.
 */
public class CategoriesReportAdapter
    extends CursorAdapter {

    private LayoutInflater mInflater;

//    @SuppressWarnings("deprecation")
    public CategoriesReportAdapter(Context context, Cursor c) {
        super(context, c, false);

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtColumn1 = (TextView) view.findViewById(R.id.textViewColumn1);
        TextView txtColumn2 = (TextView) view.findViewById(R.id.textViewColumn2);

        double total = cursor.getDouble(cursor.getColumnIndex("TOTAL"));
        String column1;
        String category = cursor.getString(cursor.getColumnIndex(ViewMobileData.Category));
        if (!TextUtils.isEmpty(category)) {
            column1 = "<b>" + category + "</b>";
            String subCategory = cursor.getString(cursor.getColumnIndex(ViewMobileData.Subcategory));
            if (!TextUtils.isEmpty(subCategory)) {
                column1 += " : " + subCategory;
            }
        } else {
            column1 = "<i>" + context.getString(R.string.empty_category);
        }
        txtColumn1.setText(Html.fromHtml(column1));

        CurrencyService currencyService = new CurrencyService(mContext);

        txtColumn2.setText(currencyService.getCurrencyFormatted(currencyService.getBaseCurrencyId(), MoneyFactory.fromDouble(total)));
        UIHelper uiHelper = new UIHelper(context);
        if (total < 0) {
            txtColumn2.setTextColor(ContextCompat.getColor(context, uiHelper.resolveAttribute(R.attr.holo_red_color_theme)));
        } else {
            txtColumn2.setTextColor(ContextCompat.getColor(context, uiHelper.resolveAttribute(R.attr.holo_green_color_theme)));
        }

        //view.setBackgroundColor(core.resolveColorAttribute(cursor.getPosition() % 2 == 1 ? R.attr.row_dark_theme : R.attr.row_light_theme));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup root) {
        return mInflater.inflate(R.layout.item_generic_report_2_columns, root, false);
    }
}

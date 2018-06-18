package com.vanluom.group11.quanlytaichinhcanhan.reports;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CursorAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.core.Core;
import com.vanluom.group11.quanlytaichinhcanhan.core.UIHelper;
import com.vanluom.group11.quanlytaichinhcanhan.currency.CurrencyService;
import com.vanluom.group11.quanlytaichinhcanhan.database.ViewMobileData;

import info.javaperformance.money.MoneyFactory;

/**
 * Adapter for the Payee report.
 */
public class PayeeReportAdapter
    extends CursorAdapter {
    private LayoutInflater mInflater;

    @SuppressWarnings("deprecation")
    public PayeeReportAdapter(Context context, Cursor c) {
        super(context, c);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtColumn1 = (TextView) view.findViewById(R.id.textViewColumn1);
        TextView txtColumn2 = (TextView) view.findViewById(R.id.textViewColumn2);
        double total = cursor.getDouble(cursor.getColumnIndex("TOTAL"));
        if (!TextUtils.isEmpty(cursor.getString(cursor.getColumnIndex(ViewMobileData.PAYEE)))) {
            txtColumn1.setText(cursor.getString(cursor.getColumnIndex(ViewMobileData.PAYEE)));
        } else {
            txtColumn1.setText(context.getString(R.string.empty_payee));
        }

        CurrencyService currencyService = new CurrencyService(mContext);

        txtColumn2.setText(currencyService.getCurrencyFormatted(currencyService.getBaseCurrencyId(), MoneyFactory.fromDouble(total)));
        Core core = new Core(context);
        UIHelper uiHelper = new UIHelper(context);
        if (total < 0) {
            txtColumn2.setTextColor(ContextCompat.getColor(context, uiHelper.resolveAttribute(R.attr.holo_red_color_theme)));
        } else {
            txtColumn2.setTextColor(ContextCompat.getColor(context, uiHelper.resolveAttribute(R.attr.holo_green_color_theme)));
        }

        view.setBackgroundColor(core.resolveColorAttribute(cursor.getPosition() % 2 == 1 ? R.attr.row_dark_theme : R.attr.row_light_theme));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup root) {
        return mInflater.inflate(R.layout.item_generic_report_2_columns, root, false);
    }
}

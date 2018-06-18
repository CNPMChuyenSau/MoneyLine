package com.vanluom.group11.quanlytaichinhcanhan.reports;

import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.core.UIHelper;
import com.vanluom.group11.quanlytaichinhcanhan.currency.CurrencyService;
import com.vanluom.group11.quanlytaichinhcanhan.viewmodels.IncomeVsExpenseReportEntity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import info.javaperformance.money.MoneyFactory;

/**
 * Adapter
 */
public class IncomeVsExpensesAdapter
    extends CursorAdapter {

    private LayoutInflater mInflater;

    @SuppressWarnings("deprecation")
    public IncomeVsExpensesAdapter(Context context, Cursor c) {
        super(context, c);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtYear = (TextView) view.findViewById(R.id.textViewYear);
        TextView txtMonth = (TextView) view.findViewById(R.id.textViewMonth);
        TextView txtIncome = (TextView) view.findViewById(R.id.textViewIncome);
        TextView txtExpenses = (TextView) view.findViewById(R.id.textViewExpenses);
        TextView txtDifference = (TextView) view.findViewById(R.id.textViewDifference);
        // take data
        int year, month;
        year = cursor.getInt(cursor.getColumnIndex(IncomeVsExpenseReportEntity.YEAR));
        month = cursor.getInt(cursor.getColumnIndex(IncomeVsExpenseReportEntity.Month));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        double income = 0, expenses = 0;
        expenses = cursor.getDouble(cursor.getColumnIndex(IncomeVsExpenseReportEntity.Expenses));
        income = cursor.getDouble(cursor.getColumnIndex(IncomeVsExpenseReportEntity.Income));
        // attach data
        txtYear.setText(Integer.toString(year));
        String formatMonth = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? "MMM" : "MMMM";

        if (month != IncomeVsExpensesActivity.SUBTOTAL_MONTH) {
            txtMonth.setText(new SimpleDateFormat(formatMonth).format(calendar.getTime()));
        } else {
            txtMonth.setText(null);
        }
        CurrencyService currencyService = new CurrencyService(mContext);

        txtIncome.setText(currencyService.getCurrencyFormatted(currencyService.getBaseCurrencyId(),
                MoneyFactory.fromDouble(income)));
        txtExpenses.setText(currencyService.getCurrencyFormatted(currencyService.getBaseCurrencyId(),
                MoneyFactory.fromDouble(Math.abs(expenses))));
        txtDifference.setText(currencyService.getCurrencyFormatted(currencyService.getBaseCurrencyId(),
                MoneyFactory.fromDouble(income - Math.abs(expenses))));

        UIHelper uiHelper = new UIHelper(context);
        if (income - Math.abs(expenses) < 0) {
            txtDifference.setTextColor(context.getResources().getColor(
                uiHelper.resolveAttribute(R.attr.holo_red_color_theme)));
        } else {
            txtDifference.setTextColor(context.getResources().getColor(
                uiHelper.resolveAttribute(R.attr.holo_green_color_theme)));
        }
        //view.setBackgroundColor(core.resolveColorAttribute(cursor.getPosition() % 2 == 1 ? R.attr.row_dark_theme : R.attr.row_light_theme));
        // check if subtotal
        int typefaceStyle = month == IncomeVsExpensesActivity.SUBTOTAL_MONTH ? Typeface.BOLD : Typeface.NORMAL;

        txtDifference.setTypeface(null, typefaceStyle);
        txtExpenses.setTypeface(null, typefaceStyle);
        txtIncome.setTypeface(null, typefaceStyle);
        txtMonth.setTypeface(null, typefaceStyle);
        txtYear.setTypeface(null, typefaceStyle);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return mInflater.inflate(R.layout.tablerow_income_vs_expenses, parent, false);
    }
}

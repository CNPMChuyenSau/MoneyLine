package com.vanluom.group11.quanlytaichinhcanhan.viewmodels;

import android.database.Cursor;
import android.database.DatabaseUtils;

import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.EntityBase;

import info.javaperformance.money.Money;

/**
 * A record/row in the Income/Expense report.
 */
public class IncomeVsExpenseReportEntity
    extends EntityBase {

    public static final String YEAR = "Year";
    public static final String Month = "Month";
    public static final String Income = "Income";
    public static final String Expenses = "Expenses";
    public static final String Transfers = "Transfers";

    public static IncomeVsExpenseReportEntity from(Cursor c) {
        IncomeVsExpenseReportEntity entity = new IncomeVsExpenseReportEntity();
        entity.loadFromCursor(c);

        return entity;
    }

    @Override
    public void loadFromCursor(Cursor c) {
        super.loadFromCursor(c);

        DatabaseUtils.cursorDoubleToContentValuesIfPresent(c, contentValues, Income);
        DatabaseUtils.cursorDoubleToContentValuesIfPresent(c, contentValues, Expenses);
        DatabaseUtils.cursorDoubleToContentValuesIfPresent(c, contentValues, Transfers);
    }

    public int getYear() {
        return getInt(YEAR);
    }

    public int getMonth() {
        return getInt(Month);
    }

    public Money getIncome() {
        return getMoney(Income);
    }

    public Money getExpenses() {
        return getMoney(Expenses);
    }

    public Money getTransfers() {
        return getMoney(Transfers);
    }
}

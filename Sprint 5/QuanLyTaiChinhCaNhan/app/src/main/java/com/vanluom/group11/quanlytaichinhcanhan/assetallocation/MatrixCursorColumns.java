package com.vanluom.group11.quanlytaichinhcanhan.assetallocation;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.core.FormatUtilities;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.AssetClass;

import info.javaperformance.money.Money;
import info.javaperformance.money.MoneyFactory;

/**
 * Column names for the matrix cursor for Asset Allocation
 */
public class MatrixCursorColumns {

    public static final String ID = "_id";
    public static final String NAME =  AssetClass.NAME;
    public static final String ALLOCATION =  AssetClass.ALLOCATION;
    public static final String VALUE =  "Value";
    public static final String CURRENT_ALLOCATION = "CurrentAllocation";
    public static final String CURRENT_VALUE =  "CurrentValue";
    public static final String DIFFERENCE =  "Difference";
    public static final String DIFFERENCE_PERCENT = "DifferencePercent";
    public static final String TYPE = "Type";

    public static MatrixCursorColumns fromCursor(Context context, Cursor cursor) {
        MatrixCursorColumns values = new MatrixCursorColumns();
        FormatUtilities format = new FormatUtilities(context);
        String display;
        Money value;

        // Id
        values.id = (int) cursor.getLong(cursor.getColumnIndex(MatrixCursorColumns.ID));

        // Name. Translate "Cash" into current locale.
        values.name = cursor.getString(cursor.getColumnIndex(MatrixCursorColumns.NAME));
        if (values.name.equalsIgnoreCase("Cash")) {
            values.name = context.getString(R.string.cash);
        }
        // Allocation.
        values.allocation = cursor.getString(cursor.getColumnIndex(MatrixCursorColumns.ALLOCATION));

        String valueString = cursor.getString(cursor.getColumnIndex(MatrixCursorColumns.VALUE));
        if (!TextUtils.isEmpty(valueString)) {
            value = MoneyFactory.fromString(valueString);
            display = format.getValueFormattedInBaseCurrency(value);
        } else {
            display = "";
        }
        values.value = display;

        values.currentAllocation = cursor.getString(cursor.getColumnIndex(MatrixCursorColumns.CURRENT_ALLOCATION));

        valueString = cursor.getString(cursor.getColumnIndex(MatrixCursorColumns.CURRENT_VALUE));
        if (!TextUtils.isEmpty(valueString)) {
            value = MoneyFactory.fromString(valueString);
            display = format.getValueFormattedInBaseCurrency(value);
        } else {
            display = "";
        }
        values.currentValue = display;

        // difference %
        valueString = cursor.getString(cursor.getColumnIndex(MatrixCursorColumns.DIFFERENCE_PERCENT));
        if (!TextUtils.isEmpty(valueString)) {
            //value = MoneyFactory.fromString(valueString);
            display = valueString;
        } else {
            display = "";
        }
        values.differencePercent = display;

        valueString = cursor.getString(cursor.getColumnIndex(MatrixCursorColumns.DIFFERENCE));
        if (!TextUtils.isEmpty(valueString)) {
            value = MoneyFactory.fromString(valueString);
            display = format.getValueFormattedInBaseCurrency(value);
        } else {
            display = "";
        }
        values.difference = display;

        String typeString = cursor.getString(cursor.getColumnIndex(MatrixCursorColumns.TYPE));
        values.type = ItemType.valueOf(typeString);

        return values;
    }

    public int id;
    public String name;
    public String allocation;
    public String value;
    public String currentAllocation;
    public String currentValue;
    public String difference;
    public String differencePercent;
    public ItemType type;
}

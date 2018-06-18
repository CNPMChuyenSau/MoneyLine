
package com.vanluom.group11.quanlytaichinhcanhan.budget;

import android.text.TextUtils;

import java.util.Locale;

/**
 * Parses budget name into year/month combination and vice versa.
 */
public class BudgetNameParser {

    private static final String SEPARATOR = "-";

    public int getYear(String name) {
        if (TextUtils.isEmpty(name)) {
            return 0;
        }

        if (name.contains(SEPARATOR)) {
            return getYearFromYearMonth(name);
        } else {
            return Integer.parseInt(name);
        }
    }

    public int getMonth(String name) {
        if (TextUtils.isEmpty(name)) {
            return 0;
        }

        if (name.contains(SEPARATOR)) {
            return getMonthFromYearMonth(name);
        } else {
            return Integer.parseInt(name);
        }
    }

    public String getName(int year, int month) {
        if (month == 0) {
            return Integer.toString(year);
        } else {
            return String.format(Locale.getDefault(), "%04d-%02d", year, month);
        }
    }

    private int getYearFromYearMonth(String yearMonthString) {
        int position = yearMonthString.indexOf(SEPARATOR);
        String valueString = yearMonthString.substring(0, position);
        return Integer.parseInt(valueString);
    }

    private int getMonthFromYearMonth(String yearMonthString) {
        int position = yearMonthString.indexOf(SEPARATOR);
        String valueString = yearMonthString.substring(position + 1);
        return Integer.parseInt(valueString);
    }
}

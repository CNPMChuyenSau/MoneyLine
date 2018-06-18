package com.vanluom.group11.quanlytaichinhcanhan.core;

import android.content.Context;
import android.text.TextUtils;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.currency.CurrencyService;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Currency;

import info.javaperformance.money.Money;

/**
 * Various methods that help out working with numbers.
 */
public class NumericHelper {
    private CurrencyService mCurrencyService;

    public static boolean isNumeric(String str) {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public static String toString(Integer value) {
        if (value != null) {
            return Integer.toString(value);
        } else {
            return null;
        }
    }

    public static int toInt(String value) {
        return Integer.parseInt(value);
    }

    public static Integer toInteger(String value) {
        Integer result;
        if (!TextUtils.isEmpty(value) && NumericHelper.isNumeric(value)) {
            result = Integer.parseInt(value);
        } else {
            result = null;
        }
        return result;
    }

    // Instance methods

    public NumericHelper(Context context) {
        mContext = context;
    }

    private Context mContext;

    public int tryParse(String value) {
        int result;
        try {
            result = Integer.parseInt(value);
        } catch (Exception ex) {
            // nothing
            result = Constants.NOT_SET;
        }
        return result;
    }

    /**
     * Truncate the amount to the currency precision setting.
     * @return Amount truncated to the currency precision.
     */
    public Money truncateToCurrency(Money amount, Currency currency) {
        int scale = currency.getScale();
        int precision = getNumberOfDecimals(scale);

        return amount.truncate(precision);
    }

    /**
     * Extracts the number of decimal places from scale/precision value.
     * @param scale Scale, usually from the currency entity.
     * @return Number of decimals to use (precision?).
     */
    public int getNumberOfDecimals(int scale) {
        double decimals = Math.log(scale) / Math.log(10.0);
        int result = (int) Math.round(decimals);
        return result;
    }

    public String removeBlanks(String input) {
        return input.replace(" ", "");
    }

    /**
     * Clean up the number based on the locale preferences for grouping and decimal separators.
     * @param numberString Formatted string
     * @return (English) number string that can be used for expression.
     */
    public String cleanUpNumberString(String numberString) {
        // replace any blanks
        numberString = removeBlanks(numberString);

        FormatUtilities format = new FormatUtilities(mContext);

        // Remove grouping separator(s)
        String groupingSeparator = format.getGroupingSeparatorForAppLocale();
        numberString = numberString.replace(groupingSeparator, "");

        // Replace the decimal separator with a dot.
        String decimalSeparator = format.getDecimalSeparatorForAppLocale();
        if (!decimalSeparator.equals(".")) {
            numberString = numberString.replace(decimalSeparator, ".");
        }

        return numberString;
    }

    public CurrencyService getCurrencyService() {
        if (mCurrencyService == null) {
            mCurrencyService = new CurrencyService(mContext);
        }
        return mCurrencyService;
    }
}

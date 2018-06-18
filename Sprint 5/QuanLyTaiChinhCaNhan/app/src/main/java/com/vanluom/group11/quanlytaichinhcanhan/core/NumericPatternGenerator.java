package com.vanluom.group11.quanlytaichinhcanhan.core;

/**
 * Generate the number pattern for each currency.
 */
public class NumericPatternGenerator {

    public static String getPattern(int decimals) {
        //String pattern = "###G###G###G###D####";
//        String pattern = "###G###G###G###";
//        pattern = pattern.replace("G", groupSeparator);

        String pattern = "#,##0";

        if (decimals > 0) {
            pattern += ".";

            for (int i = 0; i < decimals; i++) {
                pattern += "0";
            }
        }

        return pattern;
    }
}

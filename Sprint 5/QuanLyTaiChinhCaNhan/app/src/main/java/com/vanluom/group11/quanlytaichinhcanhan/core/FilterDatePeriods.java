package com.vanluom.group11.quanlytaichinhcanhan.core;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * Filter date periods. Not user yet. Needs to be completed.
 */
public enum FilterDatePeriods {
    ALLTIME(R.string.all_time),
;

    public static FilterDatePeriods get(int code) {
        for (FilterDatePeriods value : FilterDatePeriods.values()) {
            int currentCode = value.getCode();
            if (currentCode == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("no transaction status found for " + code);
//        return null;
    }

    FilterDatePeriods(int code) {
        this.code = code;
    }

    private final int code;

    public int getCode() {
        return this.code;
    }

//    public TransactionTypes from(String name) {
//
//    }

    public boolean contains(String name) {
        boolean result = false;

        for (FilterDatePeriods type : FilterDatePeriods.values()) {
            if (type.toString().equalsIgnoreCase(name)) {
                result = true;
                break;
            }
        }

        return result;
    }
}


package com.vanluom.group11.quanlytaichinhcanhan.recurring.transactions;

import java.security.InvalidParameterException;

/**
 * Types of recurrence
 */
public enum Recurrence {
    ONCE (0),
    WEEKLY (1),
    BIWEEKLY (2),
    MONTHLY (3),
    BIMONTHLY (4),
    QUARTERLY (5),
    SEMIANNUALLY (6),
    ANNUALLY (7),
    FOUR_MONTHS (8),
    FOUR_WEEKS (9),
    DAILY (10),
    IN_X_DAYS (11),
    IN_X_MONTHS (12),
    EVERY_X_DAYS (13),
    EVERY_X_MONTHS (14),
    MONTHLY_LAST_DAY (15),
    MONTHLY_LAST_BUSINESS_DAY (16);

    Recurrence(int value) {
        mValue = value;
    }

    private int mValue;

    public static Recurrence valueOf(int value) {
        // set auto execute without user acknowledgement
        if (value >= 200) {
            value = value - 200;
        }
        // set auto execute on the next occurrence
        if (value >= 100) {
            value = value - 100;
        }

        for (Recurrence item : Recurrence.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
//        return null;
        throw new InvalidParameterException();
    }

    public int getValue() {
        return mValue;
    }
}

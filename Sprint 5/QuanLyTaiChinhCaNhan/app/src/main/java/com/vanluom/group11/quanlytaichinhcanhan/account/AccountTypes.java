package com.vanluom.group11.quanlytaichinhcanhan.account;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumeration of account types.
 */
public enum AccountTypes {
    CASH("Cash"),
    CHECKING ("Checking"),
    INVESTMENT ("Investment"),
    TERM ("Term"),
    CREDIT_CARD ("Credit Card");

    public static AccountTypes get(String name) {
        for (AccountTypes type : AccountTypes.values()) {
            if (type.title.equals(name)) return type;
        }
        return null;
    }

    public static String[] getNames() {
        List<String> list = new ArrayList<>();

        for (AccountTypes type : AccountTypes.values()) {
            list.add(type.title);
        }

        String[] result = new String[list.size()];
        return list.toArray(result);
    }

    public final String title;

    AccountTypes(String s) {
        title = s;
    }

    public boolean equalsName(String otherTitle) {
        return (otherTitle == null) ? false : title.equalsIgnoreCase(otherTitle);
    }

    public String toString(){
        return this.title;
    }
}

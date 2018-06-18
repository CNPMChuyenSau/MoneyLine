package com.vanluom.group11.quanlytaichinhcanhan.account;

/**
 * Enumeration of account statuses.
 */
public enum AccountStatuses {
    OPEN ("Open"),
    CLOSED ("Closed");

    public static AccountStatuses get(String title) {
        for(AccountStatuses status : AccountStatuses.values()) {
            if (status.title.equals(title)) return status;
        }
        return null;
    }

    public final String title;

    private AccountStatuses(String s) {
        title = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : title.equalsIgnoreCase(otherName);
    }

    public String toString(){
        return title;
    }
}

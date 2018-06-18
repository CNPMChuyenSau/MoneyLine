package com.vanluom.group11.quanlytaichinhcanhan.core;

/**
 * Transaction Types
 */
public enum TransactionTypes {
    Withdrawal(0),
    Deposit(1),
    Transfer(2);

    TransactionTypes(int i) {
        this.code = i;
    }

    private int code;

    public int getCode() {
        return this.code;
    }

//    public TransactionTypes from(String name) {
//
//    }

    public boolean contains(String name) {
        boolean result = false;

        for (TransactionTypes type : TransactionTypes.values()) {
            if (type.toString().equalsIgnoreCase(name)) {
                result = true;
                break;
            }
        }

        return result;
    }
}

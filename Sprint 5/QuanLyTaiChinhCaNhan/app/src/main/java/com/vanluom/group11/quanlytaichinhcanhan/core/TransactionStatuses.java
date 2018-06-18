package com.vanluom.group11.quanlytaichinhcanhan.core;

/**
 * Transaction Statuses
 */
public enum TransactionStatuses {
    NONE(""),
    RECONCILED("R"),
    VOID("V"),
    FOLLOWUP("F"),
    DUPLICATE("D");

    public static TransactionStatuses from(String statusName) {
        for (TransactionStatuses status : TransactionStatuses.values()) {
            // clean-up dashes. Needed for "follow-up".
            statusName = statusName.replace("-", "");

            if (status.name().equalsIgnoreCase(statusName.toLowerCase())) {
                return status;
            }
        }
        return null;
    }

    public static TransactionStatuses get(String code) {
        for (TransactionStatuses value : TransactionStatuses.values()) {
            String currentCode = value.getCode();
            if (currentCode.equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("no transaction status found for " + code);
    }

    TransactionStatuses(String code) {
        this.code = code;
    }

    private final String code;

    public String getCode() {
        return this.code;
    }

    public boolean contains(String name) {
        boolean result = false;

        for (TransactionStatuses type : TransactionStatuses.values()) {
            if (type.toString().equalsIgnoreCase(name)) {
                result = true;
                break;
            }
        }

        return result;
    }
}

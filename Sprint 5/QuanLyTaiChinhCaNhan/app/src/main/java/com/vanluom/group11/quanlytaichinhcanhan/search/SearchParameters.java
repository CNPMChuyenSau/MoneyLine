
package com.vanluom.group11.quanlytaichinhcanhan.search;

import org.parceler.Parcel;

import java.util.Date;

import info.javaperformance.money.Money;

/**
 * Class that contains the search parameters.
 * Used as a DTO and to store the values.
 */
@Parcel
public class SearchParameters {

    public static final String STRING_NULL_VALUE = "null";

    public SearchParameters() {
        // explicitly set the null value
        this.status = STRING_NULL_VALUE;
    }

    // Account
    public Integer accountId;

    // Transaction Type
    public boolean deposit;
    public boolean transfer;
    public boolean withdrawal;

    // Status
    public String status;

    // Amount
    public Money amountFrom;
    public Money amountTo;

    // Date
    public Date dateFrom;
    public Date dateTo;

    public Integer payeeId = null;
    public String payeeName;

    public CategorySub category;

    public String transactionNumber;
    public String notes;
}

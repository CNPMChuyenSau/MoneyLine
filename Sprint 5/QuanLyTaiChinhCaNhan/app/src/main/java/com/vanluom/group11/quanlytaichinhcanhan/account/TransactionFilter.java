
package com.vanluom.group11.quanlytaichinhcanhan.account;

import com.vanluom.group11.quanlytaichinhcanhan.core.DateRange;

import org.parceler.Parcel;

/**
 * Contains the selected filters for the account transactions list.
 * Used to pass to the UI filter binaryDialog, and to the adapter when querying the data.
 */
@Parcel
public class TransactionFilter {

    public TransactionFilter() { }

    public DateRange dateRange;
    public StatusFilter transactionStatus;
}

package com.vanluom.group11.quanlytaichinhcanhan.core;

import org.parceler.Parcel;

import java.util.Date;

/**
 * Represents a date range.
 */
@Parcel
public class DateRange {

    public DateRange() {}

    public DateRange(Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Date dateFrom;
    public Date dateTo;
}

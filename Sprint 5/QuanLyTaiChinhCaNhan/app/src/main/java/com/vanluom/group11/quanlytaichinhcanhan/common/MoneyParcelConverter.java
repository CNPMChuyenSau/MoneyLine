
package com.vanluom.group11.quanlytaichinhcanhan.common;

import android.os.Parcel;

import org.parceler.ParcelConverter;

import info.javaperformance.money.Money;
import info.javaperformance.money.MoneyFactory;

/**
 * Converts Money type to parcel for Parceler.
 */
public class MoneyParcelConverter
    implements ParcelConverter<Money> {

    @Override
    public void toParcel(Money input, Parcel parcel) {
        // store as string
        parcel.writeString(input.toString());
    }

    @Override
    public Money fromParcel(Parcel parcel) {
        String amountString = parcel.readString();
        return MoneyFactory.fromString(amountString);
    }
}

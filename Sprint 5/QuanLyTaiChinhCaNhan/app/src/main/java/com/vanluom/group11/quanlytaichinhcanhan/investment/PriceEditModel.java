
package com.vanluom.group11.quanlytaichinhcanhan.investment;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.core.FormatUtilities;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDate;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDateTimeUtils;

import javax.inject.Inject;

import dagger.Lazy;
import info.javaperformance.money.Money;

/**
 * Model for editing the security price.
 */

public class PriceEditModel {
    public PriceEditModel() {
        MoneyManagerApplication.getApp().iocComponent.inject(this);
    }

    public int accountId;

    public String symbol;
    public Money price;
    public MmxDate date;

    public int currencyId = Constants.NOT_SET;

    @Inject Lazy<MmxDateTimeUtils> dateTimeUtilsLazy;

    public void display(Context context, EditPriceViewHolder viewHolder) {
        String dateDisplay = dateTimeUtilsLazy.get().getUserFormattedDate(context, this.date.toDate());
        viewHolder.dateTextView.setText(dateDisplay);

        String amount;
        FormatUtilities format = new FormatUtilities(context);
//        if (currencyId == Constants.NOT_SET) {
//            // use base currency?
//            amount = format.getValueFormattedInBaseCurrency(price);
//        } else {
//            amount = format.format(price, currencyId);
//        }

        amount = format.format(price, Constants.PRICE_FORMAT);

        viewHolder.amountTextView.setText(amount);
    }
}


package com.vanluom.group11.quanlytaichinhcanhan.currency.events;

/**
 * A user confirmed deletion of the currency. Proceed.
 */
public class CurrencyDeletionConfirmedEvent {
    public CurrencyDeletionConfirmedEvent(int currencyId, int itemPosition) {
        this.currencyId = currencyId;
        this.itemPosition = itemPosition;
    }

    public int currencyId;
    public int itemPosition;
}

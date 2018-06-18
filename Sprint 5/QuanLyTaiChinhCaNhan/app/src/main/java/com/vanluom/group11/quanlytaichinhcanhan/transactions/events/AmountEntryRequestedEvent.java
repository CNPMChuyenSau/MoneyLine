
package com.vanluom.group11.quanlytaichinhcanhan.transactions.events;

import info.javaperformance.money.Money;

/**
 * Event raised from a list or recycler view item when it wants the amount input binaryDialog to show.
 */
public class AmountEntryRequestedEvent {

    public AmountEntryRequestedEvent(int requestId, Money amount) {
        this.requestId = requestId;
        this.amount = amount;
    }

    public int requestId;
    /**
     * Initial amount to display.
     */
    public Money amount;
}

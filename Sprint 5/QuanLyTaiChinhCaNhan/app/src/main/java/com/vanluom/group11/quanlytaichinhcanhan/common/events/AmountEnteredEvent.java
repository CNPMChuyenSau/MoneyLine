
package com.vanluom.group11.quanlytaichinhcanhan.common.events;

import info.javaperformance.money.Money;

/**
 * Raised by the numeric input / calculator when the number has been entered and confirmed by
 * clicking OK button.
 */
public class AmountEnteredEvent {

    public AmountEnteredEvent(String requestId, Money amount) {
        this.amount = amount;
        this.requestId = requestId;
    }

    /**
     * The entered amount.
     */
    public Money amount;

    /**
     * The id of the request, used to identify the caller in onEvent.
     */
    public String requestId;
}

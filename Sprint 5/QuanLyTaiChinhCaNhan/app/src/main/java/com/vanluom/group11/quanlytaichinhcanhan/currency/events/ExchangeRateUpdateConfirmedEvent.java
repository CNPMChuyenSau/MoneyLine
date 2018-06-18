
package com.vanluom.group11.quanlytaichinhcanhan.currency.events;

/**
 * Raised after confirmation of the currency exchange rates update.
 */
public class ExchangeRateUpdateConfirmedEvent {
    public ExchangeRateUpdateConfirmedEvent(boolean updateAll) {
        this.updateAll = updateAll;
    }

    /**
     * Indicates whether the user requested to update all currencies or not. The negative
     * option means that only active currencies should be updated.
     */
    public boolean updateAll;
}

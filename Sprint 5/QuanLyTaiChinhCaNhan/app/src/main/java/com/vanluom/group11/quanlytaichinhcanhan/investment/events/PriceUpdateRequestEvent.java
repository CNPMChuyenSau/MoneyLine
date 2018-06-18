
package com.vanluom.group11.quanlytaichinhcanhan.investment.events;

/**
 * Price update requested for a security.
 */
public class PriceUpdateRequestEvent {

    public PriceUpdateRequestEvent(String symbol) {
        this.symbol = symbol;
    }

    public String symbol;
}

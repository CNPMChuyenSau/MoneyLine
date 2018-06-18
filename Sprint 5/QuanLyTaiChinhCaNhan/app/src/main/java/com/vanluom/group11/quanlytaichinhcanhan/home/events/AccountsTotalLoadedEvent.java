package com.vanluom.group11.quanlytaichinhcanhan.home.events;

/**
 * When accounts total is loaded.
 * Used to display the total balance in navigation drawer.
 */
public class AccountsTotalLoadedEvent {
    public AccountsTotalLoadedEvent(String amount) {
        this.amount = amount;
    }

    public String amount;
}

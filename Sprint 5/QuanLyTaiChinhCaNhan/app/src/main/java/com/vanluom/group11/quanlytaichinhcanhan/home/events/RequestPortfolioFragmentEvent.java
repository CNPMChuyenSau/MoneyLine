
package com.vanluom.group11.quanlytaichinhcanhan.home.events;

/**
 * Request that the Portfolio fragment is displayed for the given account id.
 */
public class RequestPortfolioFragmentEvent {
    public RequestPortfolioFragmentEvent(int accountId) {
        this.accountId = accountId;
    }

    public int accountId;
}

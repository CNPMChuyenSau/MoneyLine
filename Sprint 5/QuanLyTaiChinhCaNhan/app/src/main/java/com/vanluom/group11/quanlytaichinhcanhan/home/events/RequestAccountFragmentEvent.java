
package com.vanluom.group11.quanlytaichinhcanhan.home.events;

/**
 * Request for the display of Account fragment (in Main Activity).
 */
public class RequestAccountFragmentEvent {
    public RequestAccountFragmentEvent(int accountId) {
        this.accountId = accountId;
    }

    public int accountId;
}

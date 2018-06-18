
package com.vanluom.group11.quanlytaichinhcanhan.transactions.events;

/**
 * Category is being requested.
 */
public class CategoryRequestedEvent {
    public CategoryRequestedEvent(int requestId) {
        this.requestId = requestId;
    }

    public int requestId;
}

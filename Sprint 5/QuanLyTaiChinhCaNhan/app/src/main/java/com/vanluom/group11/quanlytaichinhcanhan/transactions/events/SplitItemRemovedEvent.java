
package com.vanluom.group11.quanlytaichinhcanhan.transactions.events;

import com.vanluom.group11.quanlytaichinhcanhan.database.ISplitTransaction;

/**
 * Split item removed by the user.
 * Called from the individual split item fragment.
 */
public class SplitItemRemovedEvent {
    public SplitItemRemovedEvent(ISplitTransaction entity) {
        this.entity = entity;
    }

    public ISplitTransaction entity;
}

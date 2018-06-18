
package com.vanluom.group11.quanlytaichinhcanhan.common.events;

import android.view.View;

/**
 * Event for clicking the items in recycler view.
 */
public class ListItemClickedEvent {
    public ListItemClickedEvent(int id, String name, View view) {
        this.id = id;
        this.name = name;
        this.view = view;
    }

    public int id;
    public String name;
    /**
     * Clicked view.
     */
    public View view;
}

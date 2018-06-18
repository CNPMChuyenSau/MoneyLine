
package com.vanluom.group11.quanlytaichinhcanhan.view.recycler;

import android.view.ContextMenu;

/**
 * Context menu for recycler view.
 */
public class RecyclerViewContextMenuInfo
    implements ContextMenu.ContextMenuInfo {

    public RecyclerViewContextMenuInfo(int position, long id) {
        this.position = position;
        this.id = id;
    }

    final public int position;
    final public long id;
}
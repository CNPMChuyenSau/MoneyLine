
package com.vanluom.group11.quanlytaichinhcanhan.core;

/**
 * Ids for context menus.
 * For easier handling.
 */
public enum ContextMenuIds {
    EDIT(1),
    DELETE(2),
    COPY(3),
    VIEW_TRANSACTIONS(4),
    DownloadPrice(5),
    EditPrice(6),
    Print(7),
    SaveAsHtml(8),
    Portfolio(9);

    public static ContextMenuIds get(int id) {
        for(ContextMenuIds itemId : ContextMenuIds.values()) {
            if (itemId.getId() == id) return itemId;
        }
        return null;
    }

    ContextMenuIds(int id) {
        this.id = id;
    }

    private int id;

    public int getId() {
        return this.id;
    }
}

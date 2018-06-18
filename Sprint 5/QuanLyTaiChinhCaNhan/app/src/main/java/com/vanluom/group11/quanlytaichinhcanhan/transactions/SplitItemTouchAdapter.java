
package com.vanluom.group11.quanlytaichinhcanhan.transactions;

/**
 * Adapter for swiping Split items.
 * Only support for removing items, for now.
 * See
 * https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-b9456d2b1aaf#.mu58y13q0
 */
public interface SplitItemTouchAdapter {
    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}

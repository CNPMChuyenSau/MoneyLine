
package com.vanluom.group11.quanlytaichinhcanhan.transactions;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Callback for swipe in split items.
 * Based on article and code at
 * https://github.com/iPaulPro/Android-ItemTouchHelper-Demo/blob/master/app/src/main/java/co/paulburke/android/itemtouchhelperdemo/helper/SimpleItemTouchHelperCallback.java
 */
public class SplitItemTouchCallback
    extends ItemTouchHelper.Callback {

    public SplitItemTouchCallback(SplitCategoriesAdapter adapter) {
        mAdapter = adapter;
    }

    private final SplitCategoriesAdapter mAdapter;

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        return 0;

//        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int dragFlags = 0;

        final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

//    @Override
//    public boolean isLongPressDragEnabled() {
//        return true;
//    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//        return false;

        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }

        // Notify the adapter of the move
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        // Notify the adapter of the dismissal
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}

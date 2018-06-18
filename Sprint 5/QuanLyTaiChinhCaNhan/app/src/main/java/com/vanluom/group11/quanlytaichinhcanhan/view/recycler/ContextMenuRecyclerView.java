
package com.vanluom.group11.quanlytaichinhcanhan.view.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.View;

/**
 * RecyclerView with a context menu.
 */
public class ContextMenuRecyclerView
    extends RecyclerView {

    private RecyclerViewContextMenuInfo mContextMenuInfo;

    public ContextMenuRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ContextMenuRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ContextMenuRecyclerView(Context context) {
        super(context);
    }

    @Override
    protected ContextMenu.ContextMenuInfo getContextMenuInfo() {
        return mContextMenuInfo;
    }

    @Override
    public boolean showContextMenuForChild(View originalView) {
        final int position = getChildPosition(originalView);
        if (position >= 0) {
            final long itemId = getAdapter().getItemId(position);
            mContextMenuInfo = new RecyclerViewContextMenuInfo(position, itemId);
            return super.showContextMenuForChild(originalView);
        }
        return false;
    }
}
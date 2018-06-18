
package com.vanluom.group11.quanlytaichinhcanhan.home;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * INCOMPLETE
 * The adapter for recycler view, for Drawer menu items.
 * Used for the upgrade of the Material Dialogs library.
 */
public class DrawerMenuItemRecyclerAdapter
    extends RecyclerView.Adapter {

    public DrawerMenuItemRecyclerAdapter() {
        this.items = new ArrayList<>();
    }

    private ArrayList<DrawerMenuItem> items;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DrawerViewHolder holder = null;

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void add(DrawerMenuItem item) {
        this.items.add(item);
    }

}

package com.vanluom.group11.quanlytaichinhcanhan.assetallocation;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * Adapter for the Asset Allocation list.
 */
public class AssetAllocationAdapter
    extends CursorAdapter {

    public AssetAllocationAdapter(Context context, Cursor cursor) {
        super(context, cursor, -1);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_asset_allocation, parent, false);

        // view holder pattern
        AssetClassViewHolder holder = AssetClassViewHolder.initialize(view);
        view.setTag(holder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Object tag = view.getTag();
        if (tag == null) return;

        AssetClassViewHolder holder = (AssetClassViewHolder) tag;

        MatrixCursorColumns values = MatrixCursorColumns.fromCursor(context, cursor);

        UIHelpers.populateAssetClassRow(holder, values);
    }
}

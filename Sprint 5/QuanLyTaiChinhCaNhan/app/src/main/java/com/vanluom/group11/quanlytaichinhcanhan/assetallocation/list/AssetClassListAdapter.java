
package com.vanluom.group11.quanlytaichinhcanhan.assetallocation.list;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.AssetClass;
import com.vanluom.group11.quanlytaichinhcanhan.view.recycler.CursorRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for the Asset Class list (picker)
 */
public class AssetClassListAdapter
    extends CursorRecyclerViewAdapter<AssetClassListItemViewHolder> {

    public AssetClassListAdapter(Cursor cursor) {
        super(cursor);

        this.assetClasses = new ArrayList<>();
    }

    public List<AssetClass> assetClasses;

    @Override
    public AssetClassListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        super.onCreateViewHolder(parent, viewType);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.item_asset_class_list, parent, false);

        AssetClassListItemViewHolder viewHolder = new AssetClassListItemViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AssetClassListItemViewHolder viewHolder, Cursor cursor) {
        AssetClass assetClass = new AssetClass();

        assetClass.loadFromCursor(cursor);

        viewHolder.id = assetClass.getId();
        viewHolder.nameView.setText(assetClass.getName());
    }
}

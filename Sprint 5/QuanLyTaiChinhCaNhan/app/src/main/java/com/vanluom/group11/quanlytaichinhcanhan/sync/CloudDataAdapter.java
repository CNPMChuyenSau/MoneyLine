
package com.vanluom.group11.quanlytaichinhcanhan.sync;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloudrail.si.types.CloudMetaData;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.core.UIHelper;

import java.util.List;

/**
 * Adapter for the items in the cloud storage. Used for db file picker.
 */
public class CloudDataAdapter
    extends RecyclerView.Adapter<CloudItemViewHolder> {

    public CloudDataAdapter(Context context, List<CloudMetaData> data) {
        mContext = context;
        mData = data;
    }

    private Context mContext;
    public List<CloudMetaData> mData;

    @Override
    public CloudItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_remote_storage_content, parent, false);

        return new CloudItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CloudItemViewHolder holder, int position) {
        // get the data
        CloudMetaData item = mData.get(position);

        holder.itemPosition = position;
        holder.itemPath = item.getPath();

        holder.nameTextView.setText(item.getName());
        // Icon: folder or file
        Drawable icon = null;
        UIHelper ui = new UIHelper(getContext());

        if (item.getFolder()) {
            icon = ui.getIcon(GoogleMaterial.Icon.gmd_folder_open)
                    .sizeDp(30)
                    .color(ui.getSecondaryTextColor());
        } else {
            //icon = FontIconDrawable.inflate(getContext(), R.xml.ic_);
        }
        holder.nameTextView.setCompoundDrawables(icon, null, null, null);
        holder.nameTextView.setCompoundDrawablePadding(16);
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public Context getContext() {
        return mContext;
    }
}

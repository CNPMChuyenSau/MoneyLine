
package com.vanluom.group11.quanlytaichinhcanhan.assetallocation.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.events.ListItemClickedEvent;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoTextViewFontIcon;

import org.greenrobot.eventbus.EventBus;

/**
 * View holder for the list item.
 */
public class AssetClassListItemViewHolder
    extends RecyclerView.ViewHolder {

    public int id;

    public RobotoTextViewFontIcon nameView;

    public AssetClassListItemViewHolder(View itemView) {
        super(itemView);

        nameView = (RobotoTextViewFontIcon) itemView.findViewById(R.id.nameView);
        nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RobotoTextViewFontIcon textView = (RobotoTextViewFontIcon) v;
                EventBus.getDefault().post(new ListItemClickedEvent(id, textView.getText().toString(), v));
            }
        });
    }
}

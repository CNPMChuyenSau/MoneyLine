
package com.vanluom.group11.quanlytaichinhcanhan.sync;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.events.ListItemClickedEvent;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoTextViewFontIcon;

import org.greenrobot.eventbus.EventBus;

/**
 * View holder for the cloud items (folder, file).
 */
public class CloudItemViewHolder
    extends RecyclerView.ViewHolder {

    public CloudItemViewHolder(View itemView) {
        super(itemView);

        initialize(itemView);
    }

    public int itemPosition;
    public String itemPath;
    // view elements
    public RobotoTextViewFontIcon nameTextView;

    private void initialize(View view) {
        // e clicks on the parent element
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ListItemClickedEvent(itemPosition, itemPath, v));
            }
        });

        nameTextView = (RobotoTextViewFontIcon) view.findViewById(R.id.nameTextView);
    }
}

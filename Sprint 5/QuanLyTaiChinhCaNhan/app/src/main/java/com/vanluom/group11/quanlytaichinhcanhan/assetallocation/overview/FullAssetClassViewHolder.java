
package com.vanluom.group11.quanlytaichinhcanhan.assetallocation.overview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoTextView;

/**
 * View Holder for the full asset class.
 */
public class FullAssetClassViewHolder
    extends RecyclerView.ViewHolder {

    public FullAssetClassViewHolder(View itemView) {
        super(itemView);

        listItem = itemView.findViewById(R.id.list_item);
        assetClassTextView = (RobotoTextView) itemView.findViewById(R.id.assetClassTextView);
        setAllocationTextView = (RobotoTextView) itemView.findViewById(R.id.setAllocationTextView);
        currentAllocationTextView = (RobotoTextView) itemView.findViewById(R.id.currentAllocationTextView);
        allocationDiffTextView = (RobotoTextView) itemView.findViewById(R.id.allocationDiffTextView);

        valuetPanel = itemView.findViewById(R.id.valuePanel);
        setValueTextView = (RobotoTextView) itemView.findViewById(R.id.setValueTextView);
        currentValueTextView = (RobotoTextView) itemView.findViewById(R.id.currentValueTextView);
        valueDiffTextView = (RobotoTextView) itemView.findViewById(R.id.valueDiffTextView);

        initializeBehaviours();
    }

    /**
     * This is the root element of the view.
     */
    public View listItem;

    public RobotoTextView assetClassTextView;
    public RobotoTextView setAllocationTextView;
    public RobotoTextView currentAllocationTextView;
    public RobotoTextView allocationDiffTextView;

    public View valuetPanel;
    public RobotoTextView setValueTextView;
    public RobotoTextView currentValueTextView;
    public RobotoTextView valueDiffTextView;

    public void setLevel(int level, Context context) {
        // set the left margin based on the level.
        int indent = level * 10; // (in dp)
//        indent += 16; // (the default indent)
        float scale = context.getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (indent * scale + 0.5f);

        // The control to adjust.
        View item = this.assetClassTextView;

        // Set padding.
        item.setPadding(dpAsPixels, item.getPaddingTop(), item.getPaddingRight(), item.getPaddingBottom());
    }

    private void initializeBehaviours() {
        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valuetPanel.getVisibility() == View.VISIBLE) {
                    valuetPanel.setVisibility(View.GONE);
                } else {
                    valuetPanel.setVisibility(View.VISIBLE);
                }
            }
        });

//        listItem.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                // show context menu.
//                EventBus.getDefault().post(new AssetAllocationItemLongPressedEvent());
//                return true;
//            }
//        });
    }
}

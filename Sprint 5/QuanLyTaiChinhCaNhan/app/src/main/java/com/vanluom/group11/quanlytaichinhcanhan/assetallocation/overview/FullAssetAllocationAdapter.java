
package com.vanluom.group11.quanlytaichinhcanhan.assetallocation.overview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.core.FormatUtilities;

import java.text.DecimalFormat;
import java.util.List;

import info.javaperformance.money.Money;
import info.javaperformance.money.MoneyFactory;

/**
 * Adapter for the full Asset Allocation display.
 */
public class FullAssetAllocationAdapter
    extends RecyclerView.Adapter<FullAssetClassViewHolder> {

    public FullAssetAllocationAdapter(List<AssetClassViewModel> model, Money diffThreshold, FormatUtilities formatter) {
        this.model = model;
        this.differenceThreshold = diffThreshold;
        mFormatter = formatter;
    }

    private Context context;
    private List<AssetClassViewModel> model;
    private Money differenceThreshold = MoneyFactory.fromDouble(100);
//    private int expandedPosition = Constants.NOT_SET;
    private FormatUtilities mFormatter;

    @Override
    public FullAssetClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_full_asset_class, parent, false);
        return new FullAssetClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FullAssetClassViewHolder holder, int position) {
        AssetClassViewModel item = this.model.get(position);

        // color the background, depending on the level.
        switch (item.assetClass.getType()) {
            case Allocation:
                // reset bg color
                holder.listItem.setBackgroundColor(Color.TRANSPARENT);
                break;

            case Footer:
                holder.listItem.setBackgroundColor(Color.DKGRAY);
                break;

            default:
                int colorDepth = 50 * item.level;
                holder.listItem.setBackgroundColor(Color.argb(225, 0, 100 + colorDepth, 0));
                break;
        }

        holder.assetClassTextView.setText(item.assetClass.getName());
        holder.setAllocationTextView.setText(item.assetClass.getAllocation().toString());

        // Current Allocation
        Money currentAllocation = item.assetClass.getCurrentAllocation();
        String currentAllocationString = currentAllocation == null ? "" : currentAllocation.toString();
        holder.currentAllocationTextView.setText(currentAllocationString);

        // % diff
        Money diff = item.assetClass.getDiffAsPercentOfSet();
        DecimalFormat df = new DecimalFormat("0.00");
        String diffString = df.format(diff.toDouble());
        holder.allocationDiffTextView.setText(diffString);

        // color red/green if under/over the threshold.
        if (diff.toDouble() >= this.differenceThreshold.toDouble()) {
            holder.allocationDiffTextView.setTextColor(Color.GREEN);
        }
        if (diff.toDouble() <= this.differenceThreshold.multiply(-1).toDouble()) {
            holder.allocationDiffTextView.setTextColor(Color.RED);
        }

        holder.setValueTextView.setText(mFormatter.getValueFormattedInBaseCurrency(item.assetClass.getValue()));
        holder.currentValueTextView.setText(mFormatter.getValueFormattedInBaseCurrency(item.assetClass.getCurrentValue()));
        holder.valueDiffTextView.setText(mFormatter.getValueFormattedInBaseCurrency(item.assetClass.getDifference()));

        holder.setLevel(item.level, this.context);

//        if (position == expandedPosition) {
//            holder.valuetPanel.setVisibility(View.VISIBLE);
//        } else {
//            holder.valuetPanel.setVisibility(View.GONE);
//        }
    }

    @Override
    public void onViewRecycled(FullAssetClassViewHolder holder) {
        // reset the text color for diff
        int defaultTextColor = ContextCompat.getColor(getContext(), android.R.color.primary_text_dark);
        holder.allocationDiffTextView.setTextColor(defaultTextColor);
    }

    @Override
    public int getItemCount() {
        if (model != null) {
            return this.model.size();
        } else {
            return 0;
        }
    }

//    public void onClick(View view) {
//        FullAssetClassViewHolder holder = (FullAssetClassViewHolder) view.getTag();
//
//        // Check for an expanded view, collapse if you find one
//        if (expandedPosition >= 0) {
//            int prev = expandedPosition;
//            notifyItemChanged(prev);
//        }
//        // Set the current position to "expanded"
//        expandedPosition = holder.getPosition();
//        notifyItemChanged(expandedPosition);
//    }

    private Context getContext() {
        return this.context;
    }
}

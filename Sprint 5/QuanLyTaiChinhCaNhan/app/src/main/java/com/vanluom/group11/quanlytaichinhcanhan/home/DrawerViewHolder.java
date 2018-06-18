package com.vanluom.group11.quanlytaichinhcanhan.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * ViewHolder for the linear drawer items.
 */
public class DrawerViewHolder
    extends RecyclerView.ViewHolder {

    public DrawerViewHolder(View itemView) {
        super(itemView);

        TextView textViewItem = (TextView)itemView.findViewById(R.id.textViewItem);
        ImageView imageViewIcon = (ImageView)itemView.findViewById(R.id.imageViewIcon);
        View viewDivider = itemView.findViewById(R.id.viewDivider);

        this.textViewItem = textViewItem;
        this.imageViewIcon = imageViewIcon;
        this.viewDivider = viewDivider;
    }

    public TextView textViewItem;
    public ImageView imageViewIcon;
    public View viewDivider;

}

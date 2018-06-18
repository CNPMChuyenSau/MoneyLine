
package com.vanluom.group11.quanlytaichinhcanhan.currency.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * View holder for the currency list implemented with recycler view.
 */
public class CurrencyListItemViewHolder
    extends RecyclerView.ViewHolder {

    public CurrencyListItemViewHolder(View view) {
        super(view);

        // Setup view holder.
        row = (LinearLayout) view.findViewById(R.id.row);
        name = (TextView) view.findViewById(R.id.name);
        rate = (TextView) view.findViewById(R.id.rate);
    }

    public LinearLayout row;
    public TextView name;
    public TextView rate;
}


package com.vanluom.group11.quanlytaichinhcanhan.transactions;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.shamanland.fonticon.FontIconView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * View Holder pattern for Split Category.
 * Adapter position is provided automatically by .getAdapterPosition().
 */
public class SplitItemViewHolder
    extends RecyclerView.ViewHolder {

    @BindView(R.id.textViewCategory) public TextView txtSelectCategory;
    @BindView(R.id.editTextTotAmount) public TextView txtAmount;
    @BindView(R.id.transactionTypeButton) public FontIconView transactionTypeButton;

    public SplitItemViewHolder(View itemView) {
        super(itemView);

//        transactionTypeButton = (FontIconView) itemView.findViewById(R.id.transactionTypeButton);
//        txtSelectCategory = (TextView) itemView.findViewById(R.id.textViewCategory);
//        txtAmount = (TextView) itemView.findViewById(R.id.editTextTotAmount);

        ButterKnife.bind(this, itemView);
    }
}

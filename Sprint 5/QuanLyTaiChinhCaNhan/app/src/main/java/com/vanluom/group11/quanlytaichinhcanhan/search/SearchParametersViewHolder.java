
package com.vanluom.group11.quanlytaichinhcanhan.search;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoCheckBox;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * View holder for Search Parameters view.
 */
public class SearchParametersViewHolder {

    public SearchParametersViewHolder(View view) {
        ButterKnife.bind(this, view);
    }

    @BindView(R.id.textViewFromDate) TextView txtDateFrom;
    @BindView(R.id.checkBoxDeposit) RobotoCheckBox cbxDeposit;
    @BindView(R.id.textViewFromAmount) TextView txtAmountFrom;
    @BindView(R.id.textViewToAmount) TextView txtAmountTo;
    @BindView(R.id.editTextTransNumber) EditText txtTransNumber;
    @BindView(R.id.textViewSelectPayee) TextView txtSelectPayee;
    @BindView(R.id.textViewToDate) TextView txtDateTo;
}

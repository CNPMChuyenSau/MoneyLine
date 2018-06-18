
package com.vanluom.group11.quanlytaichinhcanhan.budget;

import android.app.Activity;
import android.widget.TextView;

import com.vanluom.group11.quanlytaichinhcanhan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * View Holder for Budget Edit screen.
 */

class BudgetEditViewHolder {
    BudgetEditViewHolder(Activity activity) {
        ButterKnife.bind(this, activity);
    }

    @BindView(R.id.budgetNameTextView) TextView budgetNameTextView;
    @BindView(R.id.budgetYearTextView) TextView budgetYearTextView;
    @BindView(R.id.budgetMonthTextView) TextView budgetMonthTextView;

    private BudgetViewModel model;

    public void bind(BudgetViewModel model) {
        this.model = model;

        refreshName();
        refreshYear();
        refreshMonth();
    }

    public void refreshName() {
        budgetNameTextView.setText(model.getName());
    }

    public void refreshYear() {
        budgetYearTextView.setText(model.getYear());
    }

    public void refreshMonth() {
        budgetMonthTextView.setText(model.getMonth());
    }
}

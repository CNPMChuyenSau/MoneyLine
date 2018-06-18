
package com.vanluom.group11.quanlytaichinhcanhan.budget;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.datalayer.BudgetRepository;
import com.vanluom.group11.quanlytaichinhcanhan.servicelayer.ServiceBase;

/**
 * Budgets business logic
 */
public class BudgetService
        extends ServiceBase {

    public BudgetService(Context context) {
        super(context);
    }

    public boolean delete(int budgetId) {
        BudgetRepository repo = new BudgetRepository(getContext());
        return repo.delete(budgetId);
    }

    /**
     * Copy budget. It will load the budget with entries and create a copy.
     * Need to get the budget destination period. The period can be only a year/month like the
     * original budget.
     * @param budgetId The budget to copy.
     */
    public void copy(int budgetId) {
        //todo complete
    }
}

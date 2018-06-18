
package com.vanluom.group11.quanlytaichinhcanhan.budget.events;

/**
 * A budget was selected in the list
 */
public class BudgetSelectedEvent {
    public BudgetSelectedEvent(long budgetYearId, String budgetName) {
        this.yearId = budgetYearId;
        this.name = budgetName;
    }

    public long yearId;
    public String name;
}

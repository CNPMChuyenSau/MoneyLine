
package com.vanluom.group11.quanlytaichinhcanhan.budget;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.vanluom.group11.quanlytaichinhcanhan.BR;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Budget;

/**
 * An observable view model, used for data binding in Budget Edit.
 */
public class BudgetViewModel
    extends BaseObservable {

    public static BudgetViewModel from(Budget budget) {
        if (budget == null) return null;

        BudgetViewModel model = new BudgetViewModel();

        model.setName(budget.getName());

        return model;
    }

    public String name;
    public int year;
    public int month;

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public String getYear() {
        if (this.year == 0) {
            return "";
        }

        return Integer.toString(this.year);
    }

    @Bindable
    public String getMonth() {
        if (this.month == 0) {
            return "";
        }

        return Integer.toString(this.month);
    }

    public void setName(String value) {
        this.name = value;

        BudgetNameParser parser = new BudgetNameParser();
        this.year = parser.getYear(value);
        this.month = parser.getMonth(value);

        notifyChange();
    }

    public void setYear(int value) {
        this.year = value;
        notifyPropertyChanged(BR.year);

        BudgetNameParser parser = new BudgetNameParser();
        this.name = parser.getName(this.year, this.month);
        notifyPropertyChanged(BR.name);
    }

    public void setMonth(int value) {
        this.month = value;
        notifyPropertyChanged(BR.month);

        BudgetNameParser parser = new BudgetNameParser();
        this.name = parser.getName(this.year, this.month);
        notifyPropertyChanged(BR.name);
    }

    public void saveTo(Budget budget) {
        budget.setName(this.name);
    }
}

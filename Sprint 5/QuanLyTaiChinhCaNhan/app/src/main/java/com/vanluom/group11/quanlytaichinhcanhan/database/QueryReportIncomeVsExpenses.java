package com.vanluom.group11.quanlytaichinhcanhan.database;

import android.content.Context;
import android.text.TextUtils;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxFileUtils;
import com.vanluom.group11.quanlytaichinhcanhan.viewmodels.IncomeVsExpenseReportEntity;

public class QueryReportIncomeVsExpenses
    extends Dataset {

    public QueryReportIncomeVsExpenses(Context context) {
        super("", DatasetType.QUERY, "report_income_vs_expenses");

        initialize(context, null);
    }

    @Override
    public String[] getAllColumns() {
        return new String[]{"ROWID AS _id",
            IncomeVsExpenseReportEntity.YEAR,
            IncomeVsExpenseReportEntity.Month,
            IncomeVsExpenseReportEntity.Income,
            IncomeVsExpenseReportEntity.Expenses,
            IncomeVsExpenseReportEntity.Transfers};
    }

    private void initialize(Context context, String whereStatement) {
        ViewMobileData mobileData = new ViewMobileData(context);
        // add where statement
        if(!TextUtils.isEmpty(whereStatement)) {
            mobileData.setWhere(whereStatement);
        }
        String mobileDataQuery = mobileData.getSource();

        // assemble the source statement by combining queries.
        String source = MmxFileUtils.getRawAsString(context, R.raw.report_income_vs_expenses);
        source = source.replace(Constants.MOBILE_DATA_PATTERN, mobileDataQuery);

        this.setSource(source);
    }
}

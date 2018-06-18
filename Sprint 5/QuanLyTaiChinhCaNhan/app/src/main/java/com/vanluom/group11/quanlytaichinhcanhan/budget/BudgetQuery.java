package com.vanluom.group11.quanlytaichinhcanhan.budget;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.database.Dataset;
import com.vanluom.group11.quanlytaichinhcanhan.database.DatasetType;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxFileUtils;

/**
 * Entity for displaying budget details list.
 * Created by Alen Siljak on 5/07/2015.
 */
public class BudgetQuery
        extends Dataset {

    public BudgetQuery(Context context) {
        super(MmxFileUtils.getRawAsString(context, R.raw.query_budgets), DatasetType.QUERY,
                BudgetQuery.class.getSimpleName());

        this.mContext = context;
    }

    public static String BUDGETENTRYID = "BUDGETENTRYID";
    public static String BUDGETYEARID = "BUDGETYEARID";
    public static String CATEGID = "CATEGID";
    public static String CATEGNAME = "CATEGNAME";
    public static String SUBCATEGID = "SUBCATEGID";
    public static String SUBCATEGNAME = "SUBCATEGNAME";
    public static String PERIOD = "PERIOD";
    public static String AMOUNT = "AMOUNT";

    private Context mContext;

    // get all columns
    @Override
    public String[] getAllColumns() {
        return new String[]{ BUDGETENTRYID + " AS _id",
                BUDGETENTRYID,
                BUDGETYEARID,
                CATEGID,
                CATEGNAME,
                SUBCATEGID,
                SUBCATEGNAME,
                PERIOD,
                AMOUNT
        };
    }

}

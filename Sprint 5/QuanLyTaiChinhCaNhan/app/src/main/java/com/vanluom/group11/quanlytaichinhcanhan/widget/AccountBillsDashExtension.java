
package com.vanluom.group11.quanlytaichinhcanhan.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;

import com.google.android.apps.dashclock.api.DashClockExtension;
import com.google.android.apps.dashclock.api.ExtensionData;
import com.vanluom.group11.quanlytaichinhcanhan.home.MainActivity;
import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.database.QueryAccountBills;
import com.vanluom.group11.quanlytaichinhcanhan.currency.CurrencyService;

import info.javaperformance.money.MoneyFactory;
import timber.log.Timber;

public class AccountBillsDashExtension extends DashClockExtension {

    @Override
    protected void onUpdateData(int arg0) {
        try {
            Context context = getApplicationContext();
            MoneyManagerApplication app = new MoneyManagerApplication();
            CurrencyService currencyService = new CurrencyService(context);

            QueryAccountBills accountBills = new QueryAccountBills(context);
            String selection = accountBills.getFilterAccountSelection();
            // create a cursor
            Cursor cursor = context.getContentResolver().query(accountBills.getUri(), null, selection, null, QueryAccountBills.ACCOUNTNAME);
            // body extensions
            String body = "";

            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String accountname = cursor.getString(cursor.getColumnIndex(QueryAccountBills.ACCOUNTNAME));
                    int currencyId = cursor.getInt(cursor.getColumnIndex(QueryAccountBills.CURRENCYID));
                    double summaryAccount = cursor.getDouble(cursor.getColumnIndex(QueryAccountBills.TOTAL));
                    String value = currencyService.getCurrencyFormatted(
                            currencyId, MoneyFactory.fromDouble(summaryAccount));
                    if (!TextUtils.isEmpty(body)) body += "\r\n";
                    // add account and summary
                    body += accountname + ": " + value;
                    // move to next row
                    cursor.moveToNext();
                }

                cursor.close();
            }

            // show data
            publishUpdate(new ExtensionData()
                .visible(true)
                .icon(R.drawable.ic_stat_notification)
                .status(currencyService.getBaseCurrencyFormatted(MoneyFactory.fromDouble(app.getSummaryAccounts(context))))
                .expandedTitle(app.getUserName())
                .expandedBody(body)
                .clickIntent(new Intent(this, MainActivity.class)));
        } catch (Exception e) {
            Timber.e(e, "updating accounts/bills widget");
        }
    }
}

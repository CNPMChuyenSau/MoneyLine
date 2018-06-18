
package com.vanluom.group11.quanlytaichinhcanhan.widget;

import android.content.Context;
import android.content.Intent;

import com.google.android.apps.dashclock.api.DashClockExtension;
import com.google.android.apps.dashclock.api.ExtensionData;
import com.vanluom.group11.quanlytaichinhcanhan.currency.CurrencyService;
import com.vanluom.group11.quanlytaichinhcanhan.home.MainActivity;
import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.R;

import info.javaperformance.money.MoneyFactory;
import timber.log.Timber;

public class SummaryDashExtension extends DashClockExtension {

    @Override
    protected void onUpdateData(int arg0) {
        try {
            Context context = getApplicationContext();
            MoneyManagerApplication app = new MoneyManagerApplication();
            CurrencyService currencyService = new CurrencyService(context);

            // summary formatted
            String summary = currencyService.getBaseCurrencyFormatted(
                    MoneyFactory.fromDouble(app.getSummaryAccounts(context)));

            publishUpdate(new ExtensionData()
                    .visible(true)
                    .icon(R.drawable.ic_stat_notification)
                    .status(summary)
                    .expandedTitle(context.getString(R.string.summary) + ": " + summary)
                    .expandedBody(app.getUserName())
                    .clickIntent(new Intent(this, MainActivity.class)));
        } catch (Exception e) {
            Timber.e(e, "updating summary dash extension");
        }
    }
}

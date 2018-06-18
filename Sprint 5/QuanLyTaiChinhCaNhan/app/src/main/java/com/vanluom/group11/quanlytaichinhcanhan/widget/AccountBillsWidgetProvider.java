package com.vanluom.group11.quanlytaichinhcanhan.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.vanluom.group11.quanlytaichinhcanhan.currency.CurrencyService;
import com.vanluom.group11.quanlytaichinhcanhan.home.MainActivity;
import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.R;

import info.javaperformance.money.MoneyFactory;

/**
 * All Accounts widget
 */
public class AccountBillsWidgetProvider
    extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        MoneyManagerApplication app = new MoneyManagerApplication();
        CurrencyService currencyService = new CurrencyService(context);

        for (int i = 0; i < appWidgetIds.length; ++i) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_account_bills);
            remoteViews.setTextViewText(R.id.textViewUserName, app.loadUserNameFromDatabase(context));
            remoteViews.setTextViewText(R.id.textViewTotalAccounts, context.getString(R.string.summary) + ": "
                    + currencyService.getBaseCurrencyFormatted(MoneyFactory.fromDouble(app.getSummaryAccounts(context))));

            // register on click in icon launch application
            Intent intentApplication = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentApplication, 0);
            remoteViews.setOnClickPendingIntent(R.id.imageButtonLogoWidget, pendingIntent);

            Intent intentRefresh = new Intent(context, AccountBillsWidgetProvider.class);
            intentRefresh.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intentRefresh.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingRefresh = PendingIntent.getBroadcast(context, 0, intentRefresh, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.imageButtonRefresh, pendingRefresh);

            //service
            Intent svcIntent = new Intent(context, AccountBillsWidgetService.class);

            svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
            remoteViews.setRemoteAdapter(R.id.listViewAccountBills, svcIntent);

            // update widget
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds[i], R.id.listViewAccountBills);
            appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}

package com.vanluom.group11.quanlytaichinhcanhan.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.vanluom.group11.quanlytaichinhcanhan.currency.CurrencyService;
import com.vanluom.group11.quanlytaichinhcanhan.home.MainActivity;
import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.R;

import info.javaperformance.money.MoneyFactory;

public class SummaryWidgetProvider
    extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        MoneyManagerApplication app = new MoneyManagerApplication();
        CurrencyService currencyService = new CurrencyService(context);

        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, SummaryWidgetProvider.class));

        for (int i = 0; i < allWidgetIds.length; ++i) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_summary);
            remoteViews.setTextViewText(R.id.textViewUserName, app.loadUserNameFromDatabase(context));
            remoteViews.setTextViewText(R.id.textViewTotalAccounts, context.getString(R.string.summary) + ": "
                    + currencyService.getBaseCurrencyFormatted(MoneyFactory.fromDouble(app.getSummaryAccounts(context))));

            // register on click in icon launch application
            Intent intentApplication = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentApplication, 0);
            remoteViews.setOnClickPendingIntent(R.id.imageButtonLogoWidget, pendingIntent);

            Intent intentRefresh = new Intent(context, SummaryWidgetProvider.class);
            intentRefresh.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intentRefresh.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingRefresh = PendingIntent.getBroadcast(context, 0, intentRefresh, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.imageButtonRefresh, pendingRefresh);

            // update widget
            try {
                appWidgetManager.updateAppWidget(allWidgetIds[i], remoteViews);
            } catch (Exception e) {
                Log.e(SummaryWidgetProvider.class.getSimpleName(), e.getMessage());
            }
        }
    }
}

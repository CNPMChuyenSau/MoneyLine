package com.vanluom.group11.quanlytaichinhcanhan.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.vanluom.group11.quanlytaichinhcanhan.transactions.CheckingTransactionEditActivity;
import com.vanluom.group11.quanlytaichinhcanhan.R;

public class ButtonAddTransactionWidgetProvider extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		for (int i = 0; i < appWidgetIds.length; ++i) {
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_button_add_transaction);
			// register on click in icon launch application
			Intent intent = new Intent(context, CheckingTransactionEditActivity.class);
			intent.setAction(Intent.ACTION_INSERT);
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
			remoteViews.setOnClickPendingIntent(R.id.buttonNewOperation, pendingIntent);
		    
			// update widget
			appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
}

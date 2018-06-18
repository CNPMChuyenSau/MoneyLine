
package com.vanluom.group11.quanlytaichinhcanhan.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.currency.CurrencyService;
import com.vanluom.group11.quanlytaichinhcanhan.database.QueryAccountBills;

import info.javaperformance.money.MoneyFactory;
import timber.log.Timber;

/**
 *
 */
public class AllAccountBillsViewFactory
    implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private CurrencyService mCurrencyService;
    private Cursor mCursor;

    public AllAccountBillsViewFactory(Context context, Intent intent) {
        //appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        this.mContext = context;
        // create application
        mCurrencyService = new CurrencyService(context);
    }

    @Override
    public int getCount() {
        try {
            return getCountInternal();
        } catch (IllegalStateException ise) {
            Timber.e(ise, "getting the record count for widget");
            return 0;
        }
    }

    private int getCountInternal() {
        if (mCursor != null) {
            return mCursor.getCount();
        } else {
            return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_item_account_bills);
        if (mCursor.moveToPosition(position)) {
            int colindex = mCursor.getColumnIndex(QueryAccountBills.ACCOUNTNAME);
            String accountname = mCursor.getString(colindex);
            remoteViews.setTextViewText(R.id.textViewItemAccountName, accountname);
            String value = mCurrencyService.getCurrencyFormatted(
                    mCursor.getInt(mCursor.getColumnIndex(QueryAccountBills.CURRENCYID)),
                    MoneyFactory.fromDouble(mCursor.getDouble(mCursor.getColumnIndex(QueryAccountBills.TOTAL))));
            remoteViews.setTextViewText(R.id.textViewItemAccountTotal, value);
        }
        return remoteViews;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
        return;
    }

    @Override
    public void onDataSetChanged() {
        if (mCursor != null) {
            mCursor.close();
        }
        QueryAccountBills accountBills = new QueryAccountBills(mContext);
        String selection = accountBills.getFilterAccountSelection();
        // create a cursor
        try {
            mCursor = mContext.getContentResolver().query(accountBills.getUri(),
                    null, selection, null, QueryAccountBills.ACCOUNTNAME);
        } catch (Exception e) {
            Timber.e(e, "reloading accounts cursor");
        }
    }

    @Override
    public void onDestroy() {
        if (mCursor != null) {
            mCursor.close();
        }
        return;
    }

    public Context getContext() {
        return mContext;
    }
}

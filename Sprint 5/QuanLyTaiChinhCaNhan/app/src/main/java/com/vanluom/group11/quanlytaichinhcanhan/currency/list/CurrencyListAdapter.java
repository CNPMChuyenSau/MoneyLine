package com.vanluom.group11.quanlytaichinhcanhan.currency.list;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.CursorAdapter;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.currency.recycler.CurrencyListItemViewHolder;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Currency;

/**
 * Adapter for the list of currencies
 */
public class CurrencyListAdapter
    extends CursorAdapter {

    public CurrencyListAdapter(Context context, Cursor cursor) {
        super(context, cursor, -1);

        mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_currency, parent, false);

        // holder
        CurrencyListItemViewHolder holder = new CurrencyListItemViewHolder(view);
        // add holder to the view.
        view.setTag(holder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        CurrencyListItemViewHolder holder = (CurrencyListItemViewHolder) view.getTag();

        // name
        String name = cursor.getString(cursor.getColumnIndex(Currency.CURRENCYNAME));
        holder.name.setText(name);

        // exchange rate
        String rate = cursor.getString(cursor.getColumnIndex(Currency.BASECONVRATE));
        holder.rate.setText(rate);
    }
}

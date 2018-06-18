package com.vanluom.group11.quanlytaichinhcanhan.investment;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.StockFields;

import java.util.HashMap;

/**
 * Cursor adapter for stock list (portfolio).
 */
public class PortfolioCursorAdapter
    extends CursorAdapter {

    public PortfolioCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, -1);

        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mHeadersAccountIndex = new HashMap<>();
        mCheckedPosition = new SparseBooleanArray();
        mContext = context;
    }

    private LayoutInflater mInflater;
    private HashMap<Integer, Integer> mHeadersAccountIndex;
    private SparseBooleanArray mCheckedPosition;

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_portfolio, parent, false);

        // holder
        PortfolioItemDataViewHolder holder = new PortfolioItemDataViewHolder();

        holder.symbolTextView = (TextView) view.findViewById(R.id.symbolTextView);
        holder.numSharesView = (TextView) view.findViewById(R.id.numSharesView);
        holder.priceTextView = (TextView) view.findViewById(R.id.priceTextView);

        // set holder to view
        view.setTag(holder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // take a holder
        PortfolioItemDataViewHolder holder = (PortfolioItemDataViewHolder) view.getTag();

        // header index
        int accountId = cursor.getInt(cursor.getColumnIndex(StockFields.HELDAT));
        if (!mHeadersAccountIndex.containsKey(accountId)) {
            mHeadersAccountIndex.put(accountId, cursor.getPosition());
        }

        // symbol
        String symbol = cursor.getString(cursor.getColumnIndex(StockFields.SYMBOL));
        holder.symbolTextView.setText(symbol);

        // number of shares
        String numberOfShares = cursor.getString(cursor.getColumnIndex(StockFields.NUMSHARES));
        holder.numSharesView.setText(numberOfShares);

        // price
        String price = cursor.getString(cursor.getColumnIndex(StockFields.CURRENTPRICE));
        holder.priceTextView.setText(price);

        // check if item is checked
        if (mCheckedPosition.get(cursor.getPosition(), false)) {
            view.setBackgroundResource(R.color.material_green_100);
        } else {
            view.setBackgroundResource(android.R.color.transparent);
        }
    }
}

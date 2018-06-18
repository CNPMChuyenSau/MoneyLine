
package com.vanluom.group11.quanlytaichinhcanhan.currency.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Currency;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Used Currencies section for the Currencies recycler view
 */
public class CurrencySection
    extends StatelessSection {

    public CurrencySection(String title, List<Currency> data) {
        super(R.layout.item_currency_list_recycler_header, R.layout.item_currency);

        this.title = title;

        if (data != null) {
            this.currencies = data;
        } else {
            this.currencies = new ArrayList<>();
        }
    }

    public List<Currency> currencies;
    public String title;

    @Override
    public int getContentItemsTotal() {
        return currencies.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new CurrencyListItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        CurrencyListItemViewHolder viewHolder = (CurrencyListItemViewHolder) holder;

//        Currency currency = currencies.get(position);
        Currency currency = getItemAtPosition(position);

        viewHolder.name.setText(currency.getName());

        String rate = Double.toString(currency.getBaseConversionRate());
        viewHolder.rate.setText(rate);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new CurrencyListItemViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        CurrencyListItemViewHolder viewHolder = (CurrencyListItemViewHolder) holder;

        viewHolder.name.setText(title);
    }

    public Currency getItemAtPosition(int position) {
        //return (new ArrayList<>(currencies.values())).get(position);
        return currencies.get(position);
    }
}

package com.vanluom.group11.quanlytaichinhcanhan.investment.yql;

import android.text.TextUtils;

import java.util.Arrays;
import java.util.List;

/**
 * YQL query generator
 * Created by Alen on 25/09/2015.
 */
public class YqlQueryGenerator {
    //    private String source = "yahoo.finance.quote";
    public final String source = "yahoo.finance.quotes";

    public String getQueryFor(List<String> symbols) {
        // http://stackoverflow.com/questions/1005073/initialization-of-an-arraylist-in-one-line
        List<String> fields = Arrays.asList("symbol", "LastTradePriceOnly", "LastTradeDate", "Currency");

        String query = getQueryFor(this.source, fields, symbols);

        return query;
    }

    public String getQueryFor(String source, List<String> fields, List<String> symbols) {
        // append quotes to all the symbols
        for (int i = 0; i < symbols.size(); i++) {
            String symbol = symbols.get(i);
            symbol = "\"" + symbol + "\"";
            symbols.set(i, symbol);
        }

        String query = "select ";
        query += TextUtils.join(",", fields);
        query += " from ";
        query += source;    // table
        query += " where symbol in (";
        query += TextUtils.join(",", symbols);
        query += ")";

        return query;
    }

}

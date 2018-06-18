
package com.vanluom.group11.quanlytaichinhcanhan.investment.morningstar;


import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Converts Yahoo to Morningstar symbols.
 */
public class SymbolConverter {

    public SymbolConverter() {
        initializeMap();
    }

    BiMap<String, String> mMap;

    public String convert(String yahooSymbol) {
        // The list can be stored elsewhere, editable, and loaded when conversion is needed.

        String[] parts = yahooSymbol.split("\\.");

        // e US exchanges
        if (parts.length <= 1) {
            // U.S. Do not use an exchange prefix for now.
            return yahooSymbol;
        }

        String symbol = parts[0];
        String yahooExchange = parts[1].toUpperCase();

        String morningstarExchange = mMap.get(yahooExchange);

        String result = morningstarExchange + ":" + symbol;
        return result;
    }

    public String getYahooSymbol(String morningstarSymbol) {
        String[] parts = morningstarSymbol.split("\\:");

        if (parts.length <= 1) {
            return morningstarSymbol;
        }

        String symbol = parts[1];
        String morningstarExchange = parts[0];

        String yahooExchange = mMap.inverse().get(morningstarExchange);

        return symbol + "." + yahooExchange;
    }

    private void initializeMap() {
        mMap = HashBiMap.create();

        mMap.put("AS", "XAMS");
        mMap.put("AX", "XASX");
        mMap.put("DE", "XETR");
        mMap.put("L", "XLON");
        mMap.put("PA", "XPAR");
        // mMap.put("VI", "");
    }
}

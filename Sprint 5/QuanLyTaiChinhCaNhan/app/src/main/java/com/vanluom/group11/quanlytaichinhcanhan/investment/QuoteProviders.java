
package com.vanluom.group11.quanlytaichinhcanhan.investment;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;

/**
 * List of quote providers for stock prices and currency rates.
 */
public enum QuoteProviders {
    YahooYql,
    YahooCsv,
    Morningstar;

    public static String[] names() {
        QuoteProviders[] providers = QuoteProviders.values();
        int count = providers.length;
        String[] result = new String[count];

        for (int i = 0; i < count; i++) {
            result[i] = providers[i].name();
        }
        return result;
    }

    public static int indexOf(QuoteProviders value) {
        QuoteProviders[] providers = QuoteProviders.values();
        int count = providers.length;

        for (int i = 0; i < count; i++) {
            if (providers[i] == value) return i;
        }
        return Constants.NOT_SET;
    }
}

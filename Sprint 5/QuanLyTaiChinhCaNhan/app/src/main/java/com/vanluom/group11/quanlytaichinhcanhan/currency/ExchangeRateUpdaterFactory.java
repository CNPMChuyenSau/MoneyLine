
package com.vanluom.group11.quanlytaichinhcanhan.currency;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.investment.ISecurityPriceUpdater;
import com.vanluom.group11.quanlytaichinhcanhan.investment.QuoteProviders;
import com.vanluom.group11.quanlytaichinhcanhan.investment.yahoocsv.YahooCsvQuoteDownloaderRetrofit;
import com.vanluom.group11.quanlytaichinhcanhan.investment.yql.YqlSecurityPriceUpdaterRetrofit;
import com.vanluom.group11.quanlytaichinhcanhan.settings.InvestmentSettings;

/**
 * Factory for exchange rate updater.
 * Set here when changing the updater.
 */
public class ExchangeRateUpdaterFactory {
    public static ISecurityPriceUpdater getUpdaterInstance(Context context) {
        ISecurityPriceUpdater updater;

        // check preferences to see which downloader to use.
        InvestmentSettings settings = new InvestmentSettings(context);
        QuoteProviders provider = settings.getExchangeRateProvider();

        switch (provider) {
//            case Morningstar:
//                updater = new MorningstarPriceUpdater(context);
//                break;
            case YahooYql:
                //updater = new YqlSecurityPriceUpdater(context, feedback);
                updater = new YqlSecurityPriceUpdaterRetrofit(context);
                break;
            case YahooCsv:
//                updater = new YahooCsvSecurityPriceUpdater(context);
                updater = new YahooCsvQuoteDownloaderRetrofit(context);
                break;
            default:
                // yql
                updater = new YqlSecurityPriceUpdaterRetrofit(context);
                break;
        }

        return updater;
    }
}

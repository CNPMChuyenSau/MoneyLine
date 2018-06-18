
package com.vanluom.group11.quanlytaichinhcanhan.investment;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.investment.morningstar.MorningstarPriceUpdater;
import com.vanluom.group11.quanlytaichinhcanhan.investment.yahoocsv.YahooCsvQuoteDownloaderRetrofit;
import com.vanluom.group11.quanlytaichinhcanhan.investment.yql.YqlSecurityPriceUpdaterRetrofit;
import com.vanluom.group11.quanlytaichinhcanhan.settings.InvestmentSettings;

/**
 * Factory for security price updater.
 * Set here when changing the updater.
 */
public class SecurityPriceUpdaterFactory {
    public static ISecurityPriceUpdater getUpdaterInstance(Context context) {
        ISecurityPriceUpdater updater;

        // check preferences to see which downloader to use.
        InvestmentSettings settings = new InvestmentSettings(context);
        QuoteProviders provider = settings.getQuoteProvider();

        switch (provider) {
            case Morningstar:
                updater = new MorningstarPriceUpdater(context);
                break;
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

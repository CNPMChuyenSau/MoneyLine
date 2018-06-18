
package com.vanluom.group11.quanlytaichinhcanhan.investment.yahoocsv;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.core.UIHelper;
import com.vanluom.group11.quanlytaichinhcanhan.investment.ISecurityPriceUpdater;
import com.vanluom.group11.quanlytaichinhcanhan.investment.PriceCsvParser;
import com.vanluom.group11.quanlytaichinhcanhan.investment.PriceUpdaterBase;
import com.vanluom.group11.quanlytaichinhcanhan.investment.events.AllPricesDownloadedEvent;
import com.vanluom.group11.quanlytaichinhcanhan.investment.events.PriceDownloadedEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import timber.log.Timber;

/**
 * Implementation of the Yahoo CSV quote provider using Retrofit.
 */
public class YahooCsvQuoteDownloaderRetrofit
    extends PriceUpdaterBase
    implements ISecurityPriceUpdater {

    /**
     * Tracks the number of records to update. Used to close progress binaryDialog when all done.
     */
    private int mCounter;
    private int mTotalRecords;

    public YahooCsvQuoteDownloaderRetrofit(Context context) {
        super(context);
    }

    @Override
    public void downloadPrices(List<String> symbols) {
        if (symbols == null || symbols.isEmpty()) return;
        mTotalRecords = symbols.size();
        if (mTotalRecords == 0) return;

        showProgressDialog(mTotalRecords);

        IYahooCsvService service = getYahooCsvService();

        Callback<String> callback = new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                onContentDownloaded(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                closeProgressDialog();

                Timber.e(t, "fetching price");
            }
        };

        for (String symbol : symbols) {
            try {
                service.getPrice(symbol).enqueue(callback);
            } catch (Exception ex) {
                Timber.e(ex, "downloading quotes");
            }
        }
    }

    private synchronized void finishIfAllDone() {
        if (mCounter != mTotalRecords) return;

        closeProgressDialog();

        // Notify user that all the prices have been downloaded.
        new UIHelper(getContext()).showToast(getContext().getString(R.string.download_complete));

        // fire an event so that the data can be reloaded.
        EventBus.getDefault().post(new AllPricesDownloadedEvent());
    }

    private void onContentDownloaded(String content) {
        mCounter++;
        setProgress(mCounter);

        if (content == null) {
            new UIHelper(getContext()).showToast(getContext().getString(R.string.error_updating_rates));
            return;
        }

        PriceCsvParser parser = new PriceCsvParser(getContext());
        PriceDownloadedEvent event;
        try {
            event = parser.parse(content);
        } catch (IllegalArgumentException e) {
            Timber.e(e, "parsing the csv contents.");
            return;
        }

        // Notify the caller by invoking the interface method.
        if (event != null) {
            EventBus.getDefault().post(event);
        }

        finishIfAllDone();
    }

    public IYahooCsvService getYahooCsvService() {
        String BASE_URL = "https://download.finance.yahoo.com";
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        return retrofit.create(IYahooCsvService.class);
    }
}

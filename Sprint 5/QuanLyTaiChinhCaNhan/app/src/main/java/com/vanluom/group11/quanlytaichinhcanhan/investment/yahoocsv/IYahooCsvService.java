package com.vanluom.group11.quanlytaichinhcanhan.investment.yahoocsv;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Service interface using Retrofit, to fetch quotes from Yahoo, using CSV.
 */
public interface IYahooCsvService {
//    @GET("/v1/public/yql?format=json&env=store://datatables.org/alltableswithkeys")
//    Call<YqlStockPriceResponse> getPrices(@Select("q") String query);

//    @GET("/v1/public/yql?q={query}&format=json&env=store://datatables.org/alltableswithkeys")
//    Call<List<SecurityPriceModel>> getPrices(@Select("query") String query, Callback<List<SecurityPriceModel>> callback);

    /**
     * "http://download.finance.yahoo.com/d/quotes.csv?f=sl1d1c4&e=.csv"
     * @param symbol Yahoo Finance symbol to update
     * //@return Contents of the CSV result from Yahoo
     */
    @GET("/d/quotes.csv?f=sl1d1c4&e=.csv")
    Call<String> getPrice(@Query("s") String symbol);
}

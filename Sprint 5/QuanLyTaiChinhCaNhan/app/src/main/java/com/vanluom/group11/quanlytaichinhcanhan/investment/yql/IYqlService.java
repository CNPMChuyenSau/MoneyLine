package com.vanluom.group11.quanlytaichinhcanhan.investment.yql;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Service interface using Retrofit, to fetch data from YQL service.
 */
public interface IYqlService {
//    @GET("/v1/public/yql?format=json&env=store://datatables.org/alltableswithkeys")
//    Call<YqlStockPriceResponse> getPrices(@Select("q") String query);

//    @GET("/v1/public/yql?q={query}&format=json&env=store://datatables.org/alltableswithkeys")
//    Call<List<SecurityPriceModel>> getPrices(@Select("query") String query, Callback<List<SecurityPriceModel>> callback);

    @GET("/v1/public/yql?format=json&env=store://datatables.org/alltableswithkeys")
    Call<JsonElement> getPrices(@Query("q") String query);
}


package com.vanluom.group11.quanlytaichinhcanhan.investment.morningstar;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Morningstar network service
 * http://quotes.morningstar.com
 * t=XPAR:BNP
 */
public interface IMorningstarService {
    @GET("/stockq/c-header")
    Observable<String> getPrice(@Query("t") String symbol);
}

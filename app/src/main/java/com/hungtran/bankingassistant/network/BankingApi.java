package com.hungtran.bankingassistant.network;

import com.hungtran.bankingassistant.model.Currency;
import com.hungtran.bankingassistant.model.ExchangeRateResponse;
import com.hungtran.bankingassistant.model.GoldAreaResponse;
import com.hungtran.bankingassistant.model.InterestRateResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface BankingApi {
    @GET("/api/getAllExchangeRate")
    Observable<ExchangeRateResponse> getExchangeRates(@Header("Authorization") String authHeader);

    @GET("/api/gold/getAllGoldPrice")
    Observable<GoldAreaResponse> getGoldArea(@Header("Authorization") String authHeader);

    @GET("/api/getAllInterestRate")
    Observable<InterestRateResponse> getInterestRate(@Header("Authorization") String authHeader);

}

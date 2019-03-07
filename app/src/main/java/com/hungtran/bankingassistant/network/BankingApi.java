package com.hungtran.bankingassistant.network;

import com.hungtran.bankingassistant.model.Currency;
import com.hungtran.bankingassistant.model.ExchangeRateResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface BankingApi {
    @GET("/api/getAllExchangeRate")
    Observable<ExchangeRateResponse> getExchangeRates(@Header("Authorization") String authHeader);
}

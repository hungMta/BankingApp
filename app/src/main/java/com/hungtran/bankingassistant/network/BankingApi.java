package com.hungtran.bankingassistant.network;

import com.hungtran.bankingassistant.model.bankLocation.BankLocationRequest;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationRequestBody;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationResponse;
import com.hungtran.bankingassistant.model.exchangeRate.ExchangeRateResponse;
import com.hungtran.bankingassistant.model.gold.GoldAreaResponse;
import com.hungtran.bankingassistant.model.interestRate.InterestRateResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BankingApi {
    @GET("/api/getAllExchangeRate")
    Observable<ExchangeRateResponse> getExchangeRates(@Header("Authorization") String authHeader);

    @GET("/api/gold/getAllGoldPrice")
    Observable<GoldAreaResponse> getGoldArea(@Header("Authorization") String authHeader);

    @GET("/api/getAllInterestRate")
    Observable<InterestRateResponse> getInterestRate(@Header("Authorization") String authHeader);

    @POST("/api/search/position")
    Observable<BankLocationResponse> searchBankLocation(@Header("Authorization") String authHeader, @Body BankLocationRequest bankLocationRequest);
}

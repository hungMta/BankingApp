package com.hungtran.bankingassistant.network;

import com.hungtran.bankingassistant.model.area.AreaResponse;
import com.hungtran.bankingassistant.model.bank.BankResponse;
import com.hungtran.bankingassistant.model.bankLocation.AvaiableBankLocationResponse;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationRequest;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationRequestBody;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationResponse;
import com.hungtran.bankingassistant.model.base.BaseResponse;
import com.hungtran.bankingassistant.model.exchangeRate.ExchangeRateResponse;
import com.hungtran.bankingassistant.model.firebase.FCMTokenRequest;
import com.hungtran.bankingassistant.model.gold.GoldAreaResponse;
import com.hungtran.bankingassistant.model.interestRate.InterestRateResponse;
import com.hungtran.bankingassistant.model.linkingBank.LinkBankRequest;
import com.hungtran.bankingassistant.model.user.AccountRequest;

import io.reactivex.Observable;
import okhttp3.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BankingApi {
    @GET("/api/getAllExchangeRate")
    Observable<ExchangeRateResponse> getExchangeRates(@Header("Authorization") String authHeader);

    @GET("/api/gold/getAllGoldPrice")
    Observable<GoldAreaResponse> getGoldArea(@Header("Authorization") String authHeader);

    @GET("/api/getAllInterestRate")
    Observable<InterestRateResponse> getInterestRate(@Header("Authorization") String authHeader);

    @POST("/api/search/position")
    Observable<BankLocationResponse> searchBankLocation(@Header("Authorization") String authHeader, @Body BankLocationRequest bankLocationRequest);

    @GET("/api/district/getAllDistrict")
    Observable<AreaResponse> getArea(@Header("Authorization") String authHeader, @Query("idArea") int idArea);

    @GET("/api/bank/getAllBankPosition")
    Observable<AvaiableBankLocationResponse> getAllBankPosition(@Header("Authorization") String authHeader);

    @POST("/api/authentication/")
    Observable<retrofit2.Response<Void>> login(@Body AccountRequest accountRequest);

    @POST("/api/user/addfirebase")
    Observable<BaseResponse> postFCMToken(@Header("Authorization") String authHeader, @Body FCMTokenRequest fcmTokenRequest);

    @POST("/login/vietcombank")
    Observable<BaseResponse> linkVCB(@Body LinkBankRequest linkBankRequest);

    @GET("/api/bank/getAllBankLinked")
    Observable<BankResponse> getAllBankLinked(@Header("Authorization") String authHeader);
}

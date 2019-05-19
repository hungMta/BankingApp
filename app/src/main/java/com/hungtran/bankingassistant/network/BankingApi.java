package com.hungtran.bankingassistant.network;

import com.hungtran.bankingassistant.model.area.AreaResponse;
import com.hungtran.bankingassistant.model.bank.BankLinkingResponse;
import com.hungtran.bankingassistant.model.bank.BankResponse;
import com.hungtran.bankingassistant.model.bankLocation.AvaiableBankLocationResponse;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationRequest;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationRequestBody;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationResponse;
import com.hungtran.bankingassistant.model.base.BaseModel;
import com.hungtran.bankingassistant.model.base.BaseResponse;
import com.hungtran.bankingassistant.model.exchangeRate.ExchangeRateResponse;
import com.hungtran.bankingassistant.model.firebase.FCMTokenRequest;
import com.hungtran.bankingassistant.model.gold.GoldAreaResponse;
import com.hungtran.bankingassistant.model.interestRate.InterestRateResponse;
import com.hungtran.bankingassistant.model.linkingBank.LinkBankRequest;
import com.hungtran.bankingassistant.model.otp.OTPModel;
import com.hungtran.bankingassistant.model.otp.OTPModelRequest;
import com.hungtran.bankingassistant.model.register.RegisterRequest;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAccountRespone;
import com.hungtran.bankingassistant.model.transactionHistory.TransactionHistoryRequest;
import com.hungtran.bankingassistant.model.transactionHistory.TransactionHistoryResponse;
import com.hungtran.bankingassistant.model.transfer.TransferMoney;
import com.hungtran.bankingassistant.model.transfer.TransferTransactionResponse;
import com.hungtran.bankingassistant.model.user.AccountRequest;
import com.hungtran.bankingassistant.model.user.AccountResponse;

import butterknife.BindView;
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
    Observable<retrofit2.Response<AccountResponse>> login(@Body AccountRequest accountRequest);

    @POST("/api/user/addfirebase")
    Observable<BaseResponse> postFCMToken(@Header("Authorization") String authHeader, @Body FCMTokenRequest fcmTokenRequest);

    @POST("/login/vietcombank")
    Observable<BaseResponse> linkVCB(@Body LinkBankRequest linkBankRequest);

    @POST("/login/bidv")
    Observable<BaseResponse> linkBIDV(@Body LinkBankRequest linkBankRequest);

    @POST("/login/agribank")
    Observable<BaseResponse> linkAGRI(@Body LinkBankRequest linkBankRequest);

    @POST("/login/vietinbank")
    Observable<BaseResponse> linkVIETTIN(@Body LinkBankRequest linkBankRequest);

    @GET("/api/bank/getAllBankLinked")
    Observable<BankResponse> getAllBankLinked(@Header("Authorization") String authHeader);

    @GET("/api/bank/getAllBankLinking")
    Observable<BankLinkingResponse> getAllBankLinking(@Header("Authorization") String authHeader);

    @GET("/api/bank/getDataAccount")
    Observable<DataAccountRespone> getDataAccount(@Header("Authorization") String authHeader, @Query("id_bank") int idBank);

    @POST("/api/transfer")
    Observable<TransferTransactionResponse> transferMoney(@Header("Authorization") String authHeader,
                                                          @Body TransferMoney transferMoney
    );

    @POST("/api/transfer/otp")
    Observable<BaseResponse> submitOTP(@Header("Authorization") String authHeader,
                                                   @Body OTPModelRequest otpModel
    );

    @POST("/api/transaction")
    Observable<TransactionHistoryResponse> getTransactionHistory(@Header("Authorization") String authHeader,
                                                                 @Body TransactionHistoryRequest request);

    @POST("/api/authentication/requestRegister")
    Observable<BaseResponse> verifyEmail(@Query("email") String email);


    @POST("/api/authentication/register")
    Observable<retrofit2.Response<Void>> registerAccount(@Body RegisterRequest registerRequest);

    @GET("/api/user/changepassword")
    Observable<BaseResponse> getOTPChangePassword(@Header("Authorization") String authHeader);

    @POST("/api/user/changepassword")
    Observable<BaseResponse> changePassword(@Header("Authorization") String authHeader, @Body RegisterRequest registerRequest);

    @POST("/api/authentication/requestForgotPassword")
    Observable<BaseResponse> requestForgotPassword(@Query("email") String email);

    @POST("/api/authentication/forgotPassword")
    Observable<BaseResponse> forgotPasswordSubmit(@Header("Authorization") String authHeader, @Body RegisterRequest registerRequest);

    @GET("/api/logout")
    Observable<BaseResponse> logout(@Header("Authorization") String authHeader);
}

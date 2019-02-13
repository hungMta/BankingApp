package com.hungtran.bankingassistant.network;

import android.content.Context;

import com.hungtran.bankingassistant.util.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hungtd on 2/18/19.
 */

public class NetworkClient {
    private static int TIME_OUT = 60;
    private static BankingApi bankingApi;
    private static OkHttpClient.Builder httpClient;
    private static Retrofit.Builder builder;
    private static HttpLoggingInterceptor httpLoggingInterceptor;
    private static Retrofit retrofit;

    public static BankingApi request() {
        builder = new Retrofit.Builder().baseUrl(Constant.API_BASE_URL).addConverterFactory(GsonConverterFactory.create());
        httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        httpClient = new OkHttpClient.Builder().connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        httpClient.addInterceptor(httpLoggingInterceptor);
        retrofit = builder.client(httpClient.build()).build();
        bankingApi = retrofit.create(BankingApi.class);
        return  bankingApi;
    }
}

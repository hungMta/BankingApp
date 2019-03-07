package com.hungtran.bankingassistant.network;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    public static final int TIMEOUT_SHORT = 30;
    private static final String BASE_URL = "http://34.73.58.250:8080";
    private static BankingApi bankingApi;
    private static OkHttpClient.Builder httpClient;
    private static RxJava2CallAdapterFactory rxAdapter;
    private static Retrofit.Builder builder;
    private static HttpLoggingInterceptor httpLoggingInterceptor;
    private static Retrofit retrofit;

    public static BankingApi resquest() {
        if (bankingApi == null) {
            builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
            httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClient = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT_SHORT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT_SHORT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT_SHORT, TimeUnit.SECONDS);
            httpClient.addInterceptor(httpLoggingInterceptor);

            rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());

            retrofit = builder.client(httpClient.build())
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(rxAdapter)
                    .build();
            bankingApi = retrofit.create(BankingApi.class);
        }
        return bankingApi;
    }
}

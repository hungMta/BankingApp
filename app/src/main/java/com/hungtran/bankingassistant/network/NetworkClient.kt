package com.hungtran.bankingassistant.network

import android.content.Context

import com.hungtran.bankingassistant.util.Constant

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by hungtd on 2/18/19.
 */

object NetworkClient {
    private val TIME_OUT = 60
    lateinit var bankingApi: BankingApi
    lateinit var httpClient: OkHttpClient.Builder
    lateinit var builder: Retrofit.Builder
    lateinit var httpLoggingInterceptor: HttpLoggingInterceptor
    lateinit var retrofit: Retrofit

    fun request(): BankingApi {
        builder = Retrofit.Builder().baseUrl(Constant.API_BASE_URL).addConverterFactory(GsonConverterFactory.create())
        httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor!!.level = HttpLoggingInterceptor.Level.BASIC
        httpClient = OkHttpClient.Builder().connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        httpClient!!.addInterceptor(httpLoggingInterceptor!!)
        retrofit = builder!!.client(httpClient!!.build()).build()
        bankingApi = retrofit!!.create(BankingApi::class.java)
        return bankingApi
    }
}

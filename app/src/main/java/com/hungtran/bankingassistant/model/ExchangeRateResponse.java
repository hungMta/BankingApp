package com.hungtran.bankingassistant.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExchangeRateResponse extends BaseResponse {
    @SerializedName("data")
    private List<ExchangeRate> exchangeRates;


    public ExchangeRateResponse() {
    }

    public List<ExchangeRate> getExchangeRates() {
        return exchangeRates;
    }

    public void setExchangeRates(List<ExchangeRate> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }
}

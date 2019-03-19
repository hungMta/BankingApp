package com.hungtran.bankingassistant.model.exchangeRate;

import com.google.gson.annotations.SerializedName;
import com.hungtran.bankingassistant.model.base.BaseResponse;

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


    public void formatMoney(){
        for (int i = 0; i < exchangeRates.size(); i ++) {
            for (int j = 0; j < exchangeRates.get(i).getCurrencies().size(); j ++) {
                exchangeRates.get(i).getCurrencies().get(j).formatMoney();
            }
        }
    }
}

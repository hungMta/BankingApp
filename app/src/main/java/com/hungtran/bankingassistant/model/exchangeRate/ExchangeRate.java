package com.hungtran.bankingassistant.model.exchangeRate;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExchangeRate {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("code_name")
    private String codeName;

    @SerializedName("time_crawling")
    private String timeCrawling;

    @SerializedName("interest_rate")
    private List<Currency> currencies;

    public ExchangeRate() {
    }

    public ExchangeRate(int id, String name, String codeName, String timeCrawling, List<Currency> currencies) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
        this.timeCrawling = timeCrawling;
        this.currencies = currencies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getTimeCrawling() {
        return timeCrawling;
    }

    public void setTimeCrawling(String timeCrawling) {
        this.timeCrawling = timeCrawling;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}

package com.hungtran.bankingassistant.model.gold;

import com.google.gson.annotations.SerializedName;

public class GoldPrice {
    @SerializedName("buy")
    private String buy;

    @SerializedName("sell")
    private String sell;

    @SerializedName("buy_change")
    private String buyChange;

    @SerializedName("sell_change")
    private String sellChange;

    @SerializedName("time_crawling")
    private String timeCrawling;


    public GoldPrice(String buy, String sell, String buyChange, String sellChange, String timeCrawling) {
        this.buy = buy;
        this.sell = sell;
        this.buyChange = buyChange;
        this.sellChange = sellChange;
        this.timeCrawling = timeCrawling;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getBuyChange() {
        return buyChange;
    }

    public void setBuyChange(String buyChange) {
        this.buyChange = buyChange;
    }

    public String getSellChange() {
        return sellChange;
    }

    public void setSellChange(String sellChange) {
        this.sellChange = sellChange;
    }

    public String getTimeCrawling() {
        return timeCrawling;
    }

    public void setTimeCrawling(String timeCrawling) {
        this.timeCrawling = timeCrawling;
    }
}

package com.hungtran.bankingassistant.model.gold;

import com.google.gson.annotations.SerializedName;

public class Gold {
    @SerializedName("name")
    private String name;

    @SerializedName("buy")
    private String buy;

    @SerializedName("sell")
    private String sell;

    public Gold(String name, String buy, String sell) {
        this.name = name;
        this.buy = buy;
        this.sell = sell;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}

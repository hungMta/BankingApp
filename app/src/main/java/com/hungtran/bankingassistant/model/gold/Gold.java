package com.hungtran.bankingassistant.model.gold;

import com.google.gson.annotations.SerializedName;

public class Gold {
    @SerializedName("name")
    private String name;

   @SerializedName("current_price")
   private GoldPrice currentPrice;

    @SerializedName("old_price_1")
    private GoldPrice oldPrice1;

    @SerializedName("old_price_2")
    private GoldPrice oldPrice2;

    public Gold(String name, GoldPrice currentPrice, GoldPrice oldPrice1, GoldPrice oldPrice2) {
        this.name = name;
        this.currentPrice = currentPrice;
        this.oldPrice1 = oldPrice1;
        this.oldPrice2 = oldPrice2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GoldPrice getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(GoldPrice currentPrice) {
        this.currentPrice = currentPrice;
    }

    public GoldPrice getOldPrice1() {
        return oldPrice1;
    }

    public void setOldPrice1(GoldPrice oldPrice1) {
        this.oldPrice1 = oldPrice1;
    }

    public GoldPrice getOldPrice2() {
        return oldPrice2;
    }

    public void setOldPrice2(GoldPrice oldPrice2) {
        this.oldPrice2 = oldPrice2;
    }
}

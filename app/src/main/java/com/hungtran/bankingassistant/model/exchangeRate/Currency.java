package com.hungtran.bankingassistant.model.exchangeRate;

import com.google.gson.annotations.SerializedName;
import com.hungtran.bankingassistant.util.DataHelper;

public class Currency {

    @SerializedName("id_currency")
    private int id;

    @SerializedName("code_name")
    private String codeName;

    @SerializedName("detail")
    private String detail;

    @SerializedName("buy_cash")
    private double buyCash;

    @SerializedName("sell_cash")
    private double sellCash;

    @SerializedName("buy_card")
    private double buyCard;

    @SerializedName("sell_card")
    private double sellCard;

    @SerializedName("image_currency")
    private String imageURL;

    private String buyCashString;
    private String buyCardString;
    private String sellCashString;
    private String sellCardString;

    public Currency() {
    }

    public Currency(int id, String codeName, String detail, double buyCash, double sellCash, double buyCard, double sellCard, String imageURL) {
        this.id = id;
        this.codeName = codeName;
        this.detail = detail;
        this.buyCash = buyCash;
        this.sellCash = sellCash;
        this.buyCard = buyCard;
        this.sellCard = sellCard;
        this.imageURL = imageURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getBuyCash() {
        return buyCash;
    }

    public void setBuyCash(double buyCash) {
        this.buyCash = buyCash;
    }

    public double getSellCash() {
        return sellCash;
    }

    public void setSellCash(double sellCash) {
        this.sellCash = sellCash;
    }

    public double getBuyCard() {
        return buyCard;
    }

    public void setBuyCard(double buyCard) {
        this.buyCard = buyCard;
    }

    public double getSellCard() {
        return sellCard;
    }

    public void setSellCard(double sellCard) {
        this.sellCard = sellCard;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


    public String getBuyCashString() {
        return buyCashString;
    }

    public void setBuyCashString(String buyCashString) {
        this.buyCashString = buyCashString;
    }

    public String getBuyCardString() {
        return buyCardString;
    }

    public void setBuyCardString(String buyCardString) {
        this.buyCardString = buyCardString;
    }

    public String getSellCashString() {
        return sellCashString;
    }

    public void setSellCashString(String sellCashString) {
        this.sellCashString = sellCashString;
    }

    public String getSellCardString() {
        return sellCardString;
    }

    public void setSellCardString(String sellCardString) {
        this.sellCardString = sellCardString;
    }

    public void formatMoney(){
        this.buyCardString = DataHelper.formatMoney(this.buyCard);
        this.buyCashString = DataHelper.formatMoney(this.buyCash);
        this.sellCashString = DataHelper.formatMoney(this.sellCash);
        this.sellCardString = DataHelper.formatMoney(this.sellCard);
    }
}

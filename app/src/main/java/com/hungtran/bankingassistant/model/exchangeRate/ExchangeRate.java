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

    @SerializedName("current_exchangerate")
    private List<Currency> currentExchangerate;

    @SerializedName("old_exchangerate_1")
    private List<Currency> oldExchangerate1;

    @SerializedName("old_exchangerate_2")
    private List<Currency> oldExchangerate2;

    @SerializedName("image_banking")
    private String img;

    public ExchangeRate() {
    }

    public ExchangeRate(int id, String name, String codeName, String timeCrawling,
                        List<Currency> currentExchangerate, List<Currency> oldExchangerate1,
                        List<Currency> oldExchangerate2, String img) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
        this.timeCrawling = timeCrawling;
        this.currentExchangerate = currentExchangerate;
        this.oldExchangerate1 = oldExchangerate1;
        this.oldExchangerate2 = oldExchangerate2;
        this.img = img;
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

    public List<Currency> getCurrentExchangerate() {
        return currentExchangerate;
    }

    public void setCurrentExchangerate(List<Currency> currentExchangerate) {
        this.currentExchangerate = currentExchangerate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<Currency> getOldExchangerate1() {
        return oldExchangerate1;
    }

    public void setOldExchangerate1(List<Currency> oldExchangerate1) {
        this.oldExchangerate1 = oldExchangerate1;
    }

    public List<Currency> getOldExchangerate2() {
        return oldExchangerate2;
    }

    public void setOldExchangerate2(List<Currency> oldExchangerate2) {
        this.oldExchangerate2 = oldExchangerate2;
    }
}

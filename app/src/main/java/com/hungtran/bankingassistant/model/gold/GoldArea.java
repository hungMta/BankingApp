package com.hungtran.bankingassistant.model.gold;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoldArea {
    @SerializedName("area")
    private String area;

    @SerializedName("time_crawling")
    private String timeCrawling;

    @SerializedName("system")
    private List<Gold> goldList;

    public GoldArea(String area, String timeCrawling, List<Gold> goldList) {
        this.area = area;
        this.timeCrawling = timeCrawling;
        this.goldList = goldList;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTimeCrawling() {
        return timeCrawling;
    }

    public void setTimeCrawling(String timeCrawling) {
        this.timeCrawling = timeCrawling;
    }

    public List<Gold> getGoldList() {
        return goldList;
    }

    public void setGoldList(List<Gold> goldList) {
        this.goldList = goldList;
    }
}

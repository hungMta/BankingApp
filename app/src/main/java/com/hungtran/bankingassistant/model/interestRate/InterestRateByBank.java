package com.hungtran.bankingassistant.model.interestRate;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hungtd on 3/11/19.
 */

public class InterestRateByBank {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("code_name")
    private String codeName;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("time_crawling")
    private String timeCrawling;

    @SerializedName("sending_offline")
    private InterestRateByCurrency sendingOffline;

    @SerializedName("sending_online")
    private InterestRateByCurrency sendingOnline;


    public InterestRateByBank() {
    }

    public InterestRateByBank(int id, String name, String codeName, String fullName, String timeCrawling, InterestRateByCurrency sendingOffline, InterestRateByCurrency sendingOnline) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
        this.fullName = fullName;
        this.timeCrawling = timeCrawling;
        this.sendingOffline = sendingOffline;
        this.sendingOnline = sendingOnline;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTimeCrawling() {
        return timeCrawling;
    }

    public void setTimeCrawling(String timeCrawling) {
        this.timeCrawling = timeCrawling;
    }

    public InterestRateByCurrency getSendingOffline() {
        return sendingOffline;
    }

    public void setSendingOffline(InterestRateByCurrency sendingOffline) {
        this.sendingOffline = sendingOffline;
    }

    public InterestRateByCurrency getSendingOnline() {
        return sendingOnline;
    }

    public void setSendingOnline(InterestRateByCurrency sendingOnline) {
        this.sendingOnline = sendingOnline;
    }
}

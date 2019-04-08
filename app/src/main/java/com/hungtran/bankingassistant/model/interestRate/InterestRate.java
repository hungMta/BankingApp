package com.hungtran.bankingassistant.model.interestRate;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hungtd on 3/11/19.
 */

public class InterestRate implements Serializable {

    @SerializedName("unlimited")
    private String unlimited;

    @SerializedName("month1")
    private String month1;

    @SerializedName("month2")
    private String month2;

    @SerializedName("month3")
    private String month3;

    @SerializedName("month6")
    private String month6;

    @SerializedName("month9")
    private String month9;

    @SerializedName("month12")
    private String month12;

    @SerializedName("month18")
    private String month18;

    @SerializedName("month24")
    private String month24;

    @SerializedName("month36")
    private String month36;

    public InterestRate() {
    }

    public InterestRate(String unlimited, String month1, String month2, String month3, String month6, String month9, String month12, String month18, String month24, String month36) {
        this.unlimited = unlimited;
        this.month1 = month1;
        this.month2 = month2;
        this.month3 = month3;
        this.month6 = month6;
        this.month9 = month9;
        this.month12 = month12;
        this.month18 = month18;
        this.month24 = month24;
        this.month36 = month36;
    }

    public String getUnlimited() {
        return unlimited;
    }

    public void setUnlimited(String unlimited) {
        this.unlimited = unlimited;
    }

    public String getMonth1() {
        return month1;
    }

    public void setMonth1(String month1) {
        this.month1 = month1;
    }

    public String getMonth2() {
        return month2;
    }

    public void setMonth2(String month2) {
        this.month2 = month2;
    }

    public String getMonth3() {
        return month3;
    }

    public void setMonth3(String month3) {
        this.month3 = month3;
    }

    public String getMonth6() {
        return month6;
    }

    public void setMonth6(String month6) {
        this.month6 = month6;
    }

    public String getMonth9() {
        return month9;
    }

    public void setMonth9(String month9) {
        this.month9 = month9;
    }

    public String getMonth12() {
        return month12;
    }

    public void setMonth12(String month12) {
        this.month12 = month12;
    }

    public String getMonth18() {
        return month18;
    }

    public void setMonth18(String month18) {
        this.month18 = month18;
    }

    public String getMonth24() {
        return month24;
    }

    public void setMonth24(String month24) {
        this.month24 = month24;
    }

    public String getMonth36() {
        return month36;
    }

    public void setMonth36(String month36) {
        this.month36 = month36;
    }
}

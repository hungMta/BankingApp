package com.hungtran.bankingassistant.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoldAreaResponse extends BaseResponse{
    @SerializedName("data")
    List<GoldArea> goldAreas;

    public GoldAreaResponse(List<GoldArea> goldAreas) {
        this.goldAreas = goldAreas;
    }

    public List<GoldArea> getGoldAreas() {
        return goldAreas;
    }

    public void setGoldAreas(List<GoldArea> goldAreas) {
        this.goldAreas = goldAreas;
    }
}

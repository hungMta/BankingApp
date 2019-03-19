package com.hungtran.bankingassistant.model.gold;

import com.google.gson.annotations.SerializedName;
import com.hungtran.bankingassistant.model.base.BaseResponse;

import java.util.List;

public class GoldAreaResponse extends BaseResponse {
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

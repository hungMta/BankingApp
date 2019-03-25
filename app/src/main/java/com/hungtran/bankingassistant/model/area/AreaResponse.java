package com.hungtran.bankingassistant.model.area;

import com.google.gson.annotations.SerializedName;
import com.hungtran.bankingassistant.model.base.BaseResponse;

import java.io.Serializable;
import java.util.List;

public class AreaResponse extends BaseResponse implements Serializable {

    @SerializedName("data")
    List<Area> areaList;

    public AreaResponse(List<Area> areaList) {
        this.areaList = areaList;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }
}

package com.hungtran.bankingassistant.model.respone.DataAccount;

import com.google.gson.annotations.SerializedName;
import com.hungtran.bankingassistant.model.base.BaseResponse;

public class DataAccountRespone extends BaseResponse {

    @SerializedName("data")
    private DataAcount dataAcount;

    public DataAcount getDataAcount() {
        return dataAcount;
    }

    public void setDataAcount(DataAcount dataAcount) {
        this.dataAcount = dataAcount;
    }
}

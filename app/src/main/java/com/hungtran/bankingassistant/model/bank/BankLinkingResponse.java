package com.hungtran.bankingassistant.model.bank;

import com.google.gson.annotations.SerializedName;
import com.hungtran.bankingassistant.model.base.BaseResponse;

import java.util.List;

public class BankLinkingResponse extends BaseResponse {

    @SerializedName("data")
    private List<Bank> list;

    public List<Bank> getList() {
        return list;
    }

    public void setList(List<Bank> list) {
        this.list = list;
    }
}

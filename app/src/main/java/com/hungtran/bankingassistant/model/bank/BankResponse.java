package com.hungtran.bankingassistant.model.bank;

import com.google.gson.annotations.SerializedName;
import com.hungtran.bankingassistant.model.base.BaseResponse;

import java.util.List;

public class BankResponse extends BaseResponse {

    @SerializedName("data")
    List<List<Bank>> banks;


    public List<List<Bank>> getBanks() {
        return banks;
    }

    public void setBanks(List<List<Bank>> banks) {
        this.banks = banks;
    }
}

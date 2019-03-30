package com.hungtran.bankingassistant.model.bankLocation;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvaiableBankLocationResponse {

    @SerializedName("data")
    private List<Bank> banks;

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }
}

package com.hungtran.bankingassistant.model.bankLocation;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvaiableBankLocationResponse {

    @SerializedName("data")
    private List<BankLc> bankLcs;

    public List<BankLc> getBankLcs() {
        return bankLcs;
    }

    public void setBankLcs(List<BankLc> bankLcs) {
        this.bankLcs = bankLcs;
    }
}

package com.hungtran.bankingassistant.model.bankLocation;

import com.google.gson.annotations.SerializedName;
import com.hungtran.bankingassistant.model.base.BaseResponse;

import java.util.List;

public class BankLocationResponse extends BaseResponse {

    @SerializedName("data")
    private List<BankLocation> bankLocations;

    public BankLocationResponse(List<BankLocation> bankLocations) {
        this.bankLocations = bankLocations;
    }

    public List<BankLocation> getBankLocations() {
        return bankLocations;
    }

    public void setBankLocations(List<BankLocation> bankLocations) {
        this.bankLocations = bankLocations;
    }
}

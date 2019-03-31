package com.hungtran.bankingassistant.model.bankLocation;

import com.google.gson.annotations.SerializedName;

public class BankLocationRequest {
    @SerializedName("data")
    private BankLocationRequestBody bankLocationRequestBody;

    public BankLocationRequest(BankLocationRequestBody bankLocationRequestBody) {
        this.bankLocationRequestBody = bankLocationRequestBody;
    }

    public BankLocationRequestBody getBankLocationRequestBody() {
        return bankLocationRequestBody;
    }

    public void setBankLocationRequestBody(BankLocationRequestBody bankLocationRequestBody) {
        this.bankLocationRequestBody = bankLocationRequestBody;
    }
}

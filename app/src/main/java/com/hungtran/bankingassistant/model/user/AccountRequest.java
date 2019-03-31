package com.hungtran.bankingassistant.model.user;

import com.google.gson.annotations.SerializedName;

public class AccountRequest {
    @SerializedName("data")
    private Account account;

    public AccountRequest(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

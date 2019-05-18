package com.hungtran.bankingassistant.model.noti;

import com.google.gson.annotations.SerializedName;

public class NotiReceverModel {
    @SerializedName("name")
    private String name;

    @SerializedName("bank_account")
    private String bankAccount;

    public NotiReceverModel(String name, String bankAccount) {
        this.name = name;
        this.bankAccount = bankAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}

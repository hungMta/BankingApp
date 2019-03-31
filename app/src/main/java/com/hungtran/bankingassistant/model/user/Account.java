package com.hungtran.bankingassistant.model.user;

import com.google.gson.annotations.SerializedName;

public class Account {
    @SerializedName("username")
    private String userName;

    @SerializedName("password")
    private String password;

    public Account() {
    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

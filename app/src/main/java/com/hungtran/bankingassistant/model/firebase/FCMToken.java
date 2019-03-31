package com.hungtran.bankingassistant.model.firebase;

import com.google.gson.annotations.SerializedName;

public class FCMToken {

    @SerializedName("id_firebase")
    private String token;

    public FCMToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

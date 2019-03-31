package com.hungtran.bankingassistant.model.firebase;

import com.google.gson.annotations.SerializedName;

public class FCMTokenRequest {

    @SerializedName("data")
    private FCMToken fcmToken;

    public FCMTokenRequest(FCMToken fcmToken) {
        this.fcmToken = fcmToken;
    }

    public FCMToken getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(FCMToken fcmToken) {
        this.fcmToken = fcmToken;
    }
}

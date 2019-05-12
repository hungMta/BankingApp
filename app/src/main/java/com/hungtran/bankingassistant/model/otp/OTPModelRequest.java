package com.hungtran.bankingassistant.model.otp;

import com.google.gson.annotations.SerializedName;

public class OTPModelRequest {
    @SerializedName("data")
    private OTPModel otpModel;


    public OTPModelRequest(OTPModel otpModel) {
        this.otpModel = otpModel;
    }

    public OTPModel getOtpModel() {
        return otpModel;
    }

    public void setOtpModel(OTPModel otpModel) {
        this.otpModel = otpModel;
    }
}

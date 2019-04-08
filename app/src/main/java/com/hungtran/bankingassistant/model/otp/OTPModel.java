package com.hungtran.bankingassistant.model.otp;

import com.google.gson.annotations.SerializedName;

public class OTPModel {
    @SerializedName("otp")
    private int otp;

    @SerializedName("transaction_id")
    private int transactionId;

    public OTPModel(int otp, int transactionId) {
        this.otp = otp;
        this.transactionId = transactionId;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}

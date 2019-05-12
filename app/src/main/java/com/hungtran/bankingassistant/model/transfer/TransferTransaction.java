package com.hungtran.bankingassistant.model.transfer;

import com.google.gson.annotations.SerializedName;

public class TransferTransaction {

    @SerializedName("transaction_id")
    private int transactionId;


    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}

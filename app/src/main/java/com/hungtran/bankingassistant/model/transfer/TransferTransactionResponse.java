package com.hungtran.bankingassistant.model.transfer;

import com.google.gson.annotations.SerializedName;
import com.hungtran.bankingassistant.model.base.BaseResponse;

public class TransferTransactionResponse extends BaseResponse {
    @SerializedName("data")
    private TransferTransaction transferTransaction;

    public TransferTransaction getTransferTransaction() {
        return transferTransaction;
    }
}

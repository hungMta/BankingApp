package com.hungtran.bankingassistant.model.transactionHistory;

import com.google.gson.annotations.SerializedName;
import com.hungtran.bankingassistant.model.base.BaseResponse;

import java.util.List;

public class TransactionHistoryResponse extends BaseResponse {

    @SerializedName("data")
    private List<TransactionHistory> histories;

    public List<TransactionHistory> getHistories() {
        return histories;
    }

    public void setHistories(List<TransactionHistory> histories) {
        this.histories = histories;
    }
}

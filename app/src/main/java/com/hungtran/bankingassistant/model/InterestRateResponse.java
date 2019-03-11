package com.hungtran.bankingassistant.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hungtd on 3/11/19.
 */

public class InterestRateResponse extends BaseResponse {
    @SerializedName("data")
    List<InterestRateByBank> interestRateByBankList;

    public InterestRateResponse(List<InterestRateByBank> interestRateByBankList) {
        this.interestRateByBankList = interestRateByBankList;
    }

    public List<InterestRateByBank> getInterestRateByBankList() {
        return interestRateByBankList;
    }

    public void setInterestRateByBankList(List<InterestRateByBank> interestRateByBankList) {
        this.interestRateByBankList = interestRateByBankList;
    }
}

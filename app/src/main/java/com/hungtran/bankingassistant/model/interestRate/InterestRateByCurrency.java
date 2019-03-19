package com.hungtran.bankingassistant.model.interestRate;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hungtd on 3/11/19.
 */

public class InterestRateByCurrency {

    @SerializedName("interestrate_vnd")
    private InterestRate interestRateVnd;

    @SerializedName("interestrate_usd")
    private InterestRate interestRateUsd;

    @SerializedName("interestrate_eur")
    private InterestRate interestRateEur;

    public InterestRateByCurrency() {
    }

    public InterestRateByCurrency(InterestRate interestRateVnd, InterestRate interestRateUsd, InterestRate interestRateEur) {
        this.interestRateVnd = interestRateVnd;
        this.interestRateUsd = interestRateUsd;
        this.interestRateEur = interestRateEur;
    }

    public InterestRate getInterestRateVnd() {
        return interestRateVnd;
    }

    public void setInterestRateVnd(InterestRate interestRateVnd) {
        this.interestRateVnd = interestRateVnd;
    }

    public InterestRate getInterestRateUsd() {
        return interestRateUsd;
    }

    public void setInterestRateUsd(InterestRate interestRateUsd) {
        this.interestRateUsd = interestRateUsd;
    }

    public InterestRate getInterestRateEur() {
        return interestRateEur;
    }

    public void setInterestRateEur(InterestRate interestRateEur) {
        this.interestRateEur = interestRateEur;
    }
}

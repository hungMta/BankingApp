package com.hungtran.bankingassistant.model.interestRate;

public class CalculateInterestMoneyModel {
    private int timeCount;
    private long initialMoney;
    private long receivingInterestMoney;
    private long totalReceivingMoney;
    private long principalPaymentPerMonth;
    private long interestPaymentPerMonth;
    private long totalPaymentPerMonth;

    private String inititalMoneyString;
    private String receivingInterestMoneyString;
    private String totalReceivingMoneyString;
    private String principalPaymentPerMonthString;
    private String interestPaymentPerMonthString;
    private String totalPaymentPerMonthString;

    public CalculateInterestMoneyModel() {
    }


    public int getTimeCount() {
        return timeCount;
    }

    public void setTimeCount(int timeCount) {
        this.timeCount = timeCount;
    }

    public long getInitialMoney() {
        return initialMoney;
    }

    public void setInitialMoney(long initialMoney) {
        this.initialMoney = initialMoney;
    }

    public long getReceivingInterestMoney() {
        return receivingInterestMoney;
    }

    public void setReceivingInterestMoney(long receivingInterestMoney) {
        this.receivingInterestMoney = receivingInterestMoney;
    }

    public long getTotalReceivingMoney() {
        return totalReceivingMoney;
    }

    public void setTotalReceivingMoney(long totalReceivingMoney) {
        this.totalReceivingMoney = totalReceivingMoney;
    }

    public String getInititalMoneyString() {
        return inititalMoneyString;
    }

    public void setInititalMoneyString(String inititalMoneyString) {
        this.inititalMoneyString = inititalMoneyString;
    }

    public String getReceivingInterestMoneyString() {
        return receivingInterestMoneyString;
    }

    public void setReceivingInterestMoneyString(String receivingInterestMoneyString) {
        this.receivingInterestMoneyString = receivingInterestMoneyString;
    }

    public String getTotalReceivingMoneyString() {
        return totalReceivingMoneyString;
    }

    public void setTotalReceivingMoneyString(String totalReceivingMoneyString) {
        this.totalReceivingMoneyString = totalReceivingMoneyString;
    }

    public long getPrincipalPaymentPerMonth() {
        return principalPaymentPerMonth;
    }

    public void setPrincipalPaymentPerMonth(long principalPaymentPerMonth) {
        this.principalPaymentPerMonth = principalPaymentPerMonth;
    }

    public long getInterestPaymentPerMonth() {
        return interestPaymentPerMonth;
    }

    public void setInterestPaymentPerMonth(long interestPaymentPerMonth) {
        this.interestPaymentPerMonth = interestPaymentPerMonth;
    }

    public long getTotalPaymentPerMonth() {
        return totalPaymentPerMonth;
    }

    public void setTotalPaymentPerMonth(long totalPaymentPerMonth) {
        this.totalPaymentPerMonth = totalPaymentPerMonth;
    }

    public String getPrincipalPaymentPerMonthString() {
        return principalPaymentPerMonthString;
    }

    public void setPrincipalPaymentPerMonthString(String principalPaymentPerMonthString) {
        this.principalPaymentPerMonthString = principalPaymentPerMonthString;
    }

    public String getInterestPaymentPerMonthString() {
        return interestPaymentPerMonthString;
    }

    public void setInterestPaymentPerMonthString(String interestPaymentPerMonthString) {
        this.interestPaymentPerMonthString = interestPaymentPerMonthString;
    }

    public String getTotalPaymentPerMonthString() {
        return totalPaymentPerMonthString;
    }

    public void setTotalPaymentPerMonthString(String totalPaymentPerMonthString) {
        this.totalPaymentPerMonthString = totalPaymentPerMonthString;
    }
}

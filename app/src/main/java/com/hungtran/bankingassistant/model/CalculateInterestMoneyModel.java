package com.hungtran.bankingassistant.model;

public class CalculateInterestMoneyModel {
    private int timeCount;
    private long initialMoney;
    private long receivingInterestMoney;
    private long totalReceivingMoney;

    private String inititalMoneyString;
    private String receivingInterestMoneyString;
    private String totalReceivingMoneyString;

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
}

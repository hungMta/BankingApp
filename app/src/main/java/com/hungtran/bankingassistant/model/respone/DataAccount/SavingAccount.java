package com.hungtran.bankingassistant.model.respone.DataAccount;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SavingAccount implements Serializable {

    @SerializedName("number_saving")
    private String numberSaving;

    @SerializedName("saving_money")
    private String savingMoney;

    @SerializedName("term")
    private String term;

    @SerializedName("interest_rate")
    private String interestRate;

    @SerializedName("create")
    private String createDate;

    @SerializedName("date_due")
    private String dueDate;

    @SerializedName("branch")
    private String branch;

    @SerializedName("currency")
    private String currency;


    public SavingAccount(String numberSaving, String savingMoney, String term, String interestRate, String createDate, String dueDate, String branch, String currency) {
        this.numberSaving = numberSaving;
        this.savingMoney = savingMoney;
        this.term = term;
        this.interestRate = interestRate;
        this.createDate = createDate;
        this.dueDate = dueDate;
        this.branch = branch;
        this.currency = currency;
    }

    public String getNumberSaving() {
        return numberSaving;
    }

    public void setNumberSaving(String numberSaving) {
        this.numberSaving = numberSaving;
    }

    public String getSavingMoney() {
        return savingMoney;
    }

    public void setSavingMoney(String savingMoney) {
        this.savingMoney = savingMoney;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

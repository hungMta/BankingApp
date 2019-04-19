package com.hungtran.bankingassistant.model.transfer;

import com.google.gson.annotations.SerializedName;

public class TransferMoney  {

    @SerializedName("type")
    private int type;

    @SerializedName("from_account_number")
    private String fromAccountNumber;

    @SerializedName("to_account_number")
    private String toAccountNumber;

    @SerializedName("id_bank_from")
    private int idBankFrom;

    @SerializedName("id_bank_to")
    private int idBankTo;

    @SerializedName("name_to")
    private String nameTo;

    @SerializedName("term")
    private int term;

    @SerializedName("money")
    private double money;

    @SerializedName("interest_rate")
    private double interestRate;

    @SerializedName("currency")
    private String currency;

    @SerializedName("branch")
    private String branch;

    @SerializedName("saving_id")
    private int savingId;

    public TransferMoney() {
    }

    public TransferMoney(int type, String fromAccountNumber, String toAccountNumber, int idBankFrom, int idBankTo, String nameTo, int term, double money, double interestRate, String currency, String branch) {
        this.type = type;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.idBankFrom = idBankFrom;
        this.idBankTo = idBankTo;
        this.nameTo = nameTo;
        this.term = term;
        this.money = money;
        this.interestRate = interestRate;
        this.currency = currency;
        this.branch = branch;
    }

    public int getSavingId() {
        return savingId;
    }

    public void setSavingId(int savingId) {
        this.savingId = savingId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public int getIdBankFrom() {
        return idBankFrom;
    }

    public void setIdBankFrom(int idBankFrom) {
        this.idBankFrom = idBankFrom;
    }

    public int getIdBankTo() {
        return idBankTo;
    }

    public void setIdBankTo(int idBankTo) {
        this.idBankTo = idBankTo;
    }

    public String getNameTo() {
        return nameTo;
    }

    public void setNameTo(String nameTo) {
        this.nameTo = nameTo;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}

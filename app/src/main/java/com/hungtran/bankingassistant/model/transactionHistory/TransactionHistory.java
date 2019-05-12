package com.hungtran.bankingassistant.model.transactionHistory;

import com.google.gson.annotations.SerializedName;

public class TransactionHistory {

    private int id;

    @SerializedName("id_bank_sending")
    private int idBankSending;

    @SerializedName("id_bank_receive")
    private int idBankReceive;

    @SerializedName("id_user_sending")
    private Integer idUserSending;

    @SerializedName("id_user_receive")
    private Integer idUserReceive;

    @SerializedName("id_bank_account_send")
    private String idBankAccountSend;

    @SerializedName("id_bank_account_receive")
    private String idBankAccountReceive;

    @SerializedName("type")
    private int type;

    @SerializedName("money")
    private long money;

    @SerializedName("message")
    private String message;

    @SerializedName("interest_rate")
    private double interestRate;

    @SerializedName("term")
    private int term;

    @SerializedName("saving_id")
    private Integer savingId;

    @SerializedName("name_to")
    private String nameTo;

    @SerializedName("transaction_date")
    private long transactionDate;

    @SerializedName("isDrawl")
    private Integer isDrawl;

    @SerializedName("money_interest")
    private String moneyInterest;

    @SerializedName("savingId_receive")
    private Integer savingIdReceive;

    public TransactionHistory(int id, int idBankSending, int idBankReceive, int idUserSending, int idUserReceive, String idBankAccountSend, String idBankAccountReceive, int type, long money, double interestRate, int term, Integer savingId, String nameTo, long transactionDate, Integer isDrawl, String moneyInterest, Integer savingIdReceive) {
        this.id = id;
        this.idBankSending = idBankSending;
        this.idBankReceive = idBankReceive;
        this.idUserSending = idUserSending;
        this.idUserReceive = idUserReceive;
        this.idBankAccountSend = idBankAccountSend;
        this.idBankAccountReceive = idBankAccountReceive;
        this.type = type;
        this.money = money;
        this.interestRate = interestRate;
        this.term = term;
        this.savingId = savingId;
        this.nameTo = nameTo;
        this.transactionDate = transactionDate;
        this.isDrawl = isDrawl;
        this.moneyInterest = moneyInterest;
        this.savingIdReceive = savingIdReceive;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBankSending() {
        return idBankSending;
    }

    public void setIdBankSending(int idBankSending) {
        this.idBankSending = idBankSending;
    }

    public int getIdBankReceive() {
        return idBankReceive;
    }

    public void setIdBankReceive(int idBankReceive) {
        this.idBankReceive = idBankReceive;
    }

    public Integer getIdUserSending() {
        return idUserSending;
    }

    public void setIdUserSending(Integer idUserSending) {
        this.idUserSending = idUserSending;
    }

    public Integer getIdUserReceive() {
        return idUserReceive;
    }

    public void setIdUserReceive(Integer idUserReceive) {
        this.idUserReceive = idUserReceive;
    }

    public String getIdBankAccountSend() {
        return idBankAccountSend;
    }

    public void setIdBankAccountSend(String idBankAccountSend) {
        this.idBankAccountSend = idBankAccountSend;
    }

    public String getIdBankAccountReceive() {
        return idBankAccountReceive;
    }

    public void setIdBankAccountReceive(String idBankAccountReceive) {
        this.idBankAccountReceive = idBankAccountReceive;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Integer getSavingId() {
        return savingId;
    }

    public void setSavingId(Integer savingId) {
        this.savingId = savingId;
    }

    public String getNameTo() {
        return nameTo;
    }

    public void setNameTo(String nameTo) {
        this.nameTo = nameTo;
    }

    public long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(long transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getIsDrawl() {
        return isDrawl;
    }

    public void setIsDrawl(Integer isDrawl) {
        this.isDrawl = isDrawl;
    }

    public String getMoneyInterest() {
        return moneyInterest;
    }

    public void setMoneyInterest(String moneyInterest) {
        this.moneyInterest = moneyInterest;
    }

    public Integer getSavingIdReceive() {
        return savingIdReceive;
    }

    public void setSavingIdReceive(Integer savingIdReceive) {
        this.savingIdReceive = savingIdReceive;
    }
}

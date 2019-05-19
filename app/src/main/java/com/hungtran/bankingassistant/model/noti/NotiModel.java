package com.hungtran.bankingassistant.model.noti;

import com.google.gson.annotations.SerializedName;

public class NotiModel {
    @SerializedName("type")
    private int type;

    @SerializedName("name_bank")
    private String nameBank;

    @SerializedName("saving_id")
    private int savingId;

    @SerializedName("money")
    private long money;

    @SerializedName("message")
    private String message;

    @SerializedName("name_bank_send")
    private String nameBankSend;

    @SerializedName("name_bank_receive")
    private String nameBankReceive;

    @SerializedName("from")
    private NotiReceverModel notiReceverModel;

    public NotiModel(int type, String nameBank, int savingId, long money, String message,
                     String nameBankSend, String nameBankReceive, NotiReceverModel notiReceverModel) {
        this.type = type;
        this.nameBank = nameBank;
        this.savingId = savingId;
        this.money = money;
        this.message = message;
        this.nameBankSend = nameBankSend;
        this.nameBankReceive = nameBankReceive;
        this.notiReceverModel = notiReceverModel;
    }



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public int getSavingId() {
        return savingId;
    }

    public void setSavingId(int savingId) {
        this.savingId = savingId;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNameBankSend() {
        return nameBankSend;
    }

    public void setNameBankSend(String nameBankSend) {
        this.nameBankSend = nameBankSend;
    }

    public String getNameBankReceive() {
        return nameBankReceive;
    }

    public void setNameBankReceive(String nameBankReceive) {
        this.nameBankReceive = nameBankReceive;
    }

    public NotiReceverModel getNotiReceverModel() {
        return notiReceverModel;
    }

    public void setNotiReceverModel(NotiReceverModel notiReceverModel) {
        this.notiReceverModel = notiReceverModel;
    }
}

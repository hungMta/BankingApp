package com.hungtran.bankingassistant.model.transactionHistory;

import com.google.gson.annotations.SerializedName;

public class TransactionHistoryRequest {
    @SerializedName("type")
    private int type;

    @SerializedName("id_bank_send")
    private int idBankSend;

    @SerializedName("id_bank_receive")
    private int idBankReceive;

    public TransactionHistoryRequest() {
    }

    public TransactionHistoryRequest(int type, int idBankSend, int idBankReceive) {
        this.type = type;
        this.idBankSend = idBankSend;
        this.idBankReceive = idBankReceive;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIdBankSend() {
        return idBankSend;
    }

    public void setIdBankSend(int idBankSend) {
        this.idBankSend = idBankSend;
    }

    public int getIdBankReceive() {
        return idBankReceive;
    }

    public void setIdBankReceive(int idBankReceive) {
        this.idBankReceive = idBankReceive;
    }
}

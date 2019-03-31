package com.hungtran.bankingassistant.model.respone;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyError implements Serializable {

    @SerializedName("result")
    private int result;

    @SerializedName("message")
    private String message;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

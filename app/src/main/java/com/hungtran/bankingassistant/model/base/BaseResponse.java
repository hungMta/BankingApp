package com.hungtran.bankingassistant.model.base;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("result")
    private int result;

    @SerializedName("message")
    private  String message;


}

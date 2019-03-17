package com.hungtran.bankingassistant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankLocation {

    @Expose
    private int id;

    @Expose
    private int idBank;

    @Expose
    private String name;

    @Expose
    private String address;

}

package com.hungtran.bankingassistant.model.base;

/**
 * Created by hungtd on 3/5/19.
 */

public class Bank {

    private int id;
    private String name;


    public Bank() {
    }

    public Bank(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

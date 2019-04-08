package com.hungtran.bankingassistant.model.respone.DataAccount;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DataAcount implements Serializable {

    @SerializedName("number_account")
    private String numberAccount;

    @SerializedName("indentity")
    private String indentity;

    @SerializedName("atm_money")
    private String atmMoney;

    @SerializedName("phone")
    private String phone;

    @SerializedName("address")
    private String address;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("list_saving")
    private List<SavingAccount> savingAccountList;

    public DataAcount() {
    }

    public DataAcount(String numberAccount, String indentity, String atmMoney, String phone, String address, String name, String email, List<SavingAccount> savingAccountList) {
        this.numberAccount = numberAccount;
        this.indentity = indentity;
        this.atmMoney = atmMoney;
        this.phone = phone;
        this.address = address;
        this.name = name;
        this.email = email;
        this.savingAccountList = savingAccountList;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public String getIndentity() {
        return indentity;
    }

    public void setIndentity(String indentity) {
        this.indentity = indentity;
    }

    public String getAtmMoney() {
        return atmMoney;
    }

    public void setAtmMoney(String atmMoney) {
        this.atmMoney = atmMoney;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SavingAccount> getSavingAccountList() {
        return savingAccountList;
    }

    public void setSavingAccountList(List<SavingAccount> savingAccountList) {
        this.savingAccountList = savingAccountList;
    }
}

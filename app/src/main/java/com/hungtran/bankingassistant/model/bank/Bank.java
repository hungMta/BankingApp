package com.hungtran.bankingassistant.model.bank;

import java.io.Serializable;

/**
 * Created by hungtd on 3/5/19.
 */

public class Bank implements Serializable {

    private int id;
    private String name;
    private int category;
    private String code;
    private String fullname;
    private String image;

    private int id_bank;
    private String full_name;
    private String url_linking;

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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_bank() {
        return id_bank;
    }

    public void setId_bank(int id_bank) {
        this.id_bank = id_bank;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUrl_linking() {
        return url_linking;
    }

    public void setUrl_linking(String url_linking) {
        this.url_linking = url_linking;
    }
}

package com.hungtran.bankingassistant.model.bankLocation;

import android.media.Image;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BankLc implements Serializable {

    private int id;
    private int category;
    private String code;

    @SerializedName("fullname")
    private String fullName;
    private String name;
    private String image;

    private boolean isChecked;

    public BankLc(int id, int category, String code, String fullName, String name, String image) {
        this.id = id;
        this.category = category;
        this.code = code;
        this.fullName = fullName;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}

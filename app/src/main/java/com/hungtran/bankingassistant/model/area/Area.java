package com.hungtran.bankingassistant.model.area;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Area implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("idArea")
    private  int idArea;

    @SerializedName("name")
    private String name;

    @SerializedName("detail")
    private String detail;

    public Area() {
    }

    public Area(int id, int idArea, String name, String detail) {
        this.id = id;
        this.idArea = idArea;
        this.name = name;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}

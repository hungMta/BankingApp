package com.hungtran.bankingassistant.model.bankLocation;

import com.google.gson.annotations.SerializedName;

public class BankLocationRequestBody {

    @SerializedName("type")
    private int type;

    @SerializedName("id_bank")
    private int id;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("atm")
    private int atm;

    @SerializedName("branch")
    private int branch;

    @SerializedName("district")
    private String district;

    @SerializedName("city")
    private String city;

    @SerializedName("address")
    private String address;

    public BankLocationRequestBody() {
    }

    public BankLocationRequestBody(int type, int id, double longitude, double latitude, int atm, int branch, String district, String city, String address) {
        this.type = type;
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.atm = atm;
        this.branch = branch;
        this.district = district;
        this.city = city;
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getAtm() {
        return atm;
    }

    public void setAtm(int atm) {
        this.atm = atm;
    }

    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

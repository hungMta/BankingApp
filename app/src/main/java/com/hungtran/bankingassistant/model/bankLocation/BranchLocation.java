package com.hungtran.bankingassistant.model.bankLocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BranchLocation {

    @Expose
    private int id;

    @Expose
    private int idBank;

    @Expose
    private String name;

    @Expose
    private String address;

    @Expose
    private double latitude;

    @SerializedName("longitude")
    private double longtitude;

    @Expose
    private int status;

    @Expose
    private double distance;


    public BranchLocation() {
    }

    public BranchLocation(int id, int idBank, String name, String address, double latitude, double longtitude, int status, double distance) {
        this.id = id;
        this.idBank = idBank;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.status = status;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBank() {
        return idBank;
    }

    public void setIdBank(int idBank) {
        this.idBank = idBank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}

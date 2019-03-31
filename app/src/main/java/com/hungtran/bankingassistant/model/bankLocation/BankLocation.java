package com.hungtran.bankingassistant.model.bankLocation;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BankLocation  {
    @SerializedName("id_bank")
    private int id;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("name")
    private String name;


    @SerializedName("position")
    private List<BranchLocation> branchLocations;

    public BankLocation() {
    }

    public BankLocation(int id, String fullName, String name, List<BranchLocation> branchLocations) {
        this.id = id;
        this.fullName = fullName;
        this.name = name;
        this.branchLocations = branchLocations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<BranchLocation> getBranchLocations() {
        return branchLocations;
    }

    public void setBranchLocations(List<BranchLocation> branchLocations) {
        this.branchLocations = branchLocations;
    }
}

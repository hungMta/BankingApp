package com.hungtran.bankingassistant.model.linkingBank;

import com.google.gson.annotations.SerializedName;

public class LinkBankRequest {

    @SerializedName("data")
    private LinkingBank linkingBank;

    public LinkBankRequest(LinkingBank linkingBank) {
        this.linkingBank = linkingBank;
    }

    public LinkingBank getLinkingBank() {
        return linkingBank;
    }

    public void setLinkingBank(LinkingBank linkingBank) {
        this.linkingBank = linkingBank;
    }
}

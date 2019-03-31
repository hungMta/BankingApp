package com.hungtran.bankingassistant.ui.wallet;

public class WalletFragment {


    private static WalletFragment instance;

    public static WalletFragment getInstance() {
        if (instance == null) {
            instance = new WalletFragment();
        }
        return instance;
    }

}

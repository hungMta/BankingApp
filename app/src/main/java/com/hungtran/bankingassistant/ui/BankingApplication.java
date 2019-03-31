package com.hungtran.bankingassistant.ui;

import android.app.Application;

import com.hungtran.bankingassistant.util.base.SharePreference;

public class BankingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharePreference.initHelper(this);
    }
}

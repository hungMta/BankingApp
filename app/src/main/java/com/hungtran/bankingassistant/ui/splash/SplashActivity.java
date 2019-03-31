package com.hungtran.bankingassistant.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.ui.login.LoginActivty;
import com.hungtran.bankingassistant.ui.main.MainActivity;
import com.hungtran.bankingassistant.ui.main.MainActivityNew;
import com.hungtran.bankingassistant.util.base.BaseActivity;

public class SplashActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.acitvity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (isLogined()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LoginActivty.class);
            startActivity(intent);
        }
        finish();
    }

    private boolean isLogined() {
        return false;
    }
}

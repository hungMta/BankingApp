package com.hungtran.bankingassistant.adapters;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hungtran.bankingassistant.ui.maket.exchangeRate.ExchangeRateFragment;
import com.hungtran.bankingassistant.ui.maket.gold.GoldFragment;
import com.hungtran.bankingassistant.ui.maket.interestRate.InterestRateFragment;

public class RateViewPagerAdapter extends FragmentPagerAdapter {

    public RateViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0: fragment = new InterestRateFragment(); break;
            case 1: fragment = new ExchangeRateFragment(); break;
            case 2: fragment = new GoldFragment(); break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

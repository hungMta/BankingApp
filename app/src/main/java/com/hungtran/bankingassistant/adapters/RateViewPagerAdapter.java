package com.hungtran.bankingassistant.adapters;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.ui.maket.exchangeRate.ExchangeRateFragment;
import com.hungtran.bankingassistant.ui.maket.gold.GoldFragment;
import com.hungtran.bankingassistant.ui.maket.interestRate.InterestRateFragment;
import com.hungtran.bankingassistant.util.FragmentUtils;

public class RateViewPagerAdapter extends FragmentStatePagerAdapter {
    private  FragmentManager mFragmentManager;
    public RateViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int i) {
        String name = FragmentUtils.makeFragmentName(R.id.rateViewPager,  i);
        Fragment fragment = mFragmentManager.findFragmentByTag(name);
        if(fragment == null)
        switch (i) {
            case 0: fragment = InterestRateFragment.getInstance(); break;
            case 1: fragment = ExchangeRateFragment.getInstance(); break;
            case 2: fragment = GoldFragment.getInstance(); break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

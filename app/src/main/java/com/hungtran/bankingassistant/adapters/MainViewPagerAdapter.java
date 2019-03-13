package com.hungtran.bankingassistant.adapters;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.ui.calculator.CalculatorFragment;
import com.hungtran.bankingassistant.ui.coin.CoinFragment;
import com.hungtran.bankingassistant.ui.maket.MaketFragment;
import com.hungtran.bankingassistant.ui.map.MapFragment;
import com.hungtran.bankingassistant.ui.news.NewsFragment;
import com.hungtran.bankingassistant.util.FragmentUtils;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private FragmentManager mFragmentManager;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int i) {

        return null;
    }


    @Override
    public int getCount() {
        return 5;
    }
}

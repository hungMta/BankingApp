package com.hungtran.bankingassistant.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hungtran.bankingassistant.ui.coin.CoinFragment;
import com.hungtran.bankingassistant.ui.maket.MaketFragment;
import com.hungtran.bankingassistant.ui.map.MapFragment;
import com.hungtran.bankingassistant.ui.news.NewsFragment;
import com.hungtran.bankingassistant.ui.setting.SettingFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0: fragment = new NewsFragment(); break;
            case 1: fragment = new MaketFragment(); break;
            case 2: fragment = new MapFragment(); break;
            case 3: fragment = new CoinFragment(); break;
            case 4: fragment = new SettingFragment(); break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}

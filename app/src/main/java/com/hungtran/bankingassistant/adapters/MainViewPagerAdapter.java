package com.hungtran.bankingassistant.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.ui.coin.CoinFragment;
import com.hungtran.bankingassistant.ui.maket.MaketFragment;
import com.hungtran.bankingassistant.ui.map.MapFragment;
import com.hungtran.bankingassistant.ui.news.NewsFragment;
import com.hungtran.bankingassistant.ui.setting.SettingFragment;
import com.hungtran.bankingassistant.util.FragmentUtils;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private FragmentManager mFragmentManager;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int i) {
        String name = FragmentUtils.makeFragmentName(R.id.viewPager,  i);
        Fragment fragment = mFragmentManager.findFragmentByTag(name);
        if(fragment == null) {
            switch (i) {
                case 0:
                    fragment = NewsFragment.getInstance(); break;
                case 1:
                    fragment = MaketFragment.getInstance();  break;
                case 2:
                    fragment = MapFragment.getInstance(); break;
                case 3:
                    fragment = CoinFragment.getInstance(); break;
                case 4:
                    fragment = SettingFragment.getInstance(); break;
            }
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}

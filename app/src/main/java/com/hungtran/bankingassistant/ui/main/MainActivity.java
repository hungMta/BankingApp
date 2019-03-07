package com.hungtran.bankingassistant.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.MainViewPagerAdapter;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements  TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setupTabbar();
        String m1 = DataHelper.formatMoney(2657.59);
        String m2 = DataHelper.formatMoney(8393);
        String m3 = DataHelper.formatMoney(1234.5);
        String m4 = DataHelper.formatMoney(888);
        String m5 = DataHelper.formatMoney(23150);
        String m6 = DataHelper.formatMoney(17326.08);
        String m7 = DataHelper.formatMoney(324.91);
        return;
    }

    private void setupTabbar() {
        MainViewPagerAdapter viewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setOffscreenPageLimit(5);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.tab_news_selector).setText(getResources().getString(R.string.news));
        mTabLayout.getTabAt(1).setIcon(R.drawable.tab_rate_selector).setText(getResources().getString(R.string.market));
        mTabLayout.getTabAt(2).setIcon(R.drawable.tab_place_selector).setText(getResources().getString(R.string.place));
        mTabLayout.getTabAt(3).setIcon(R.drawable.tab_coin_selector).setText(getResources().getString(R.string.coin));
        mTabLayout.getTabAt(4).setIcon(R.drawable.tab_setting_selector).setText(getResources().getString(R.string.setting));
        mTabLayout.addOnTabSelectedListener(this);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}

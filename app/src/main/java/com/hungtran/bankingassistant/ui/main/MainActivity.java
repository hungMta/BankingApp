package com.hungtran.bankingassistant.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.MainViewPagerAdapter;
import com.hungtran.bankingassistant.ui.calculator.CalculatorFragment;
import com.hungtran.bankingassistant.ui.coin.CoinFragment;
import com.hungtran.bankingassistant.ui.maket.MaketFragment;
import com.hungtran.bankingassistant.ui.map.MapFragment;
import com.hungtran.bankingassistant.ui.news.NewsFragment;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements  TabLayout.OnTabSelectedListener{

//    @BindView(R.id.viewPager)
//    ViewPager mViewPager;

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.frameLayout)
    FrameLayout mFrameLayout;

    private NewsFragment mNewsFragment;
    private MaketFragment mMaketFragment;
    private MapFragment mMapFragment;
    private CoinFragment mCoinFragment;
    private CalculatorFragment mCalculatorFragment;
    FragmentTransaction mFragmentTransaction;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setupTabbar();
    }

    private void setupTabbar() {
//        MainViewPagerAdapter viewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
//        mViewPager.setAdapter(viewPagerAdapter);
//        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.tab_news_selector).setText(getResources().getString(R.string.news)));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.tab_rate_selector).setText(getResources().getString(R.string.market)));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.tab_place_selector).setText(getResources().getString(R.string.place)));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.tab_coin_selector).setText(getResources().getString(R.string.coin)));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.tab_calculator_selector).setText(getResources().getString(R.string.calculator)));

//        mTabLayout.getTabAt(0).setIcon(R.drawable.tab_news_selector).setText(getResources().getString(R.string.news));
//        mTabLayout.getTabAt(1).setIcon(R.drawable.tab_rate_selector).setText(getResources().getString(R.string.market));
//        mTabLayout.getTabAt(2).setIcon(R.drawable.tab_place_selector).setText(getResources().getString(R.string.place));
//        mTabLayout.getTabAt(3).setIcon(R.drawable.tab_coin_selector).setText(getResources().getString(R.string.coin));
//        mTabLayout.getTabAt(4).setIcon(R.drawable.tab_calculator_selector).setText(getResources().getString(R.string.calculator));
        mTabLayout.addOnTabSelectedListener(this);
//        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;
        switch (tab.getPosition()) {
            case 0:
                if (mNewsFragment == null) {
                    mNewsFragment = NewsFragment.getInstance();
                }
                fragment = mNewsFragment;
                break;
            case 1:
                Fragment oldFragment = getSupportFragmentManager().findFragmentByTag(String.valueOf(tab.getPosition()));
                if (oldFragment != null) {
                    getSupportFragmentManager().beginTransaction().remove(oldFragment).commit();
                }
                mMaketFragment = MaketFragment.getInstance();
                fragment = mMaketFragment;
                break;
            case 2:
                if (mMapFragment == null) {
                    mMapFragment = MapFragment.getInstance();
                }
                fragment = mMapFragment;
                break;
            case 3:
                if (mCoinFragment == null) {
                    mCoinFragment = CoinFragment.getInstance();
                }
                fragment = mCoinFragment;
                break;
            case 4:
                if (mCalculatorFragment == null) {
                    mCalculatorFragment = CalculatorFragment.getInstance();
                }
                fragment = mCalculatorFragment;
                break;
        }
        mFragmentTransaction.replace(R.id.frameLayout, fragment, String.valueOf(tab.getPosition()));
        mFragmentTransaction.commit();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

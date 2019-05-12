package com.hungtran.bankingassistant.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.FrameLayout;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.MainViewPagerAdapter;
import com.hungtran.bankingassistant.ui.calculator.CalculatorFragment;
import com.hungtran.bankingassistant.ui.coin.CoinFragment;
import com.hungtran.bankingassistant.ui.maket.MaketFragment;
import com.hungtran.bankingassistant.ui.map.MapActivity;
import com.hungtran.bankingassistant.ui.map.MapFragment;
import com.hungtran.bankingassistant.ui.news.NewsFragment;
import com.hungtran.bankingassistant.ui.setting.SettingFragment;
import com.hungtran.bankingassistant.ui.wallet.WalletActivity;
import com.hungtran.bankingassistant.ui.wallet.WalletFragment;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import java.util.Date;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener, WalletActivity.OnWalletActivityListener, MapActivity.OnMapActivityListener, MainContract.View {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.frameLayout)
    FrameLayout mFrameLayout;

    private NewsFragment mNewsFragment;
    private MaketFragment mMaketFragment;
    private MapFragment mMapFragment;
    private CoinFragment mCoinFragment;
    private WalletFragment mWalletFragment;
    private SettingFragment mSettingFragment;
    private CalculatorFragment mCalculatorFragment;
    FragmentTransaction mFragmentTransaction;
    private int currentTab = 0;
    private boolean isNeedAddFragment = false;
    private MainPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setupTabbar();
        mPresenter = new MainPresenter(this, this);
        mPresenter.submitFirebaseToken();
    }

    private void setupTabbar() {
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.tab_rate_selector).setText(getResources().getString(R.string.market)));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.tab_place_selector).setText(getResources().getString(R.string.place)));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.tab_account_selector).setText(getResources().getString(R.string.account)));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.tab_setting_selector).setText(getResources().getString(R.string.profile)));
        mTabLayout.addOnTabSelectedListener(this);
        isNeedAddFragment = true;

        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mMaketFragment = MaketFragment.getInstance();
        mFragmentTransaction.replace(R.id.frameLayout, mMaketFragment, "0");
        mFragmentTransaction.commit();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition() == 1) {
            Intent intent = new Intent(this, MapActivity.class);
            MapActivity.setOnMapActivityListener(this);
            startActivity(intent);
        } else if (tab.getPosition() == 2) {
            Intent intent = new Intent(this, WalletActivity.class);
            WalletActivity.setOnWalletActivityListener(this);
            startActivity(intent);
        } else {
            currentTab = tab.getPosition();
            if (!isNeedAddFragment) {
                isNeedAddFragment = true;
                return;
            }
            mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            Fragment fragment = null;
            switch (tab.getPosition()) {
                case 0:
                    Fragment oldFragment = getSupportFragmentManager().findFragmentByTag(String.valueOf(tab.getPosition()));
                    if (oldFragment != null) {
                        getSupportFragmentManager().beginTransaction().remove(oldFragment).commit();
                    }
                    mMaketFragment = MaketFragment.getInstance();
                    fragment = mMaketFragment;
                    break;

                case 3:
                    if (mSettingFragment == null) {
                        mSettingFragment = SettingFragment.getInstance();
                    }
                    fragment = mSettingFragment;
                    break;
            }
            mFragmentTransaction.replace(R.id.frameLayout, fragment, String.valueOf(tab.getPosition()));
            mFragmentTransaction.commit();
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onMapActivtyDestroy() {
        resetTab();
    }

    @Override
    public void onWalletActivtyDestroy() {
        resetTab();
    }

    private void resetTab() {
        isNeedAddFragment = false;
        TabLayout.Tab tab = mTabLayout.getTabAt(currentTab);
        tab.select();
    }
}

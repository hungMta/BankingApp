package com.hungtran.bankingassistant.ui.maket;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.RateViewPagerAdapter;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.android.segmented.SegmentedGroup;

public class MaketFragment extends BaseFragment implements SegmentedGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener{

    @BindView(R.id.segmented)
    SegmentedGroup mSegmentedGroup;

    @BindView(R.id.rateViewPager)
    ViewPager mViewPager;

    @BindView(R.id.btnInterestRate)
    RadioButton btnInterestRate;

    private static MaketFragment instance;
    RateViewPagerAdapter rateViewPagerAdapter;

    public static MaketFragment getInstance() {
        if (instance == null) {
            instance = new MaketFragment();
        }
        return new MaketFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_maket;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mViewPager.setOffscreenPageLimit(3);
        setupViewPager();
        mSegmentedGroup.setTintColor(Color.parseColor(Constant.PRIMARY_COLOR));
        mSegmentedGroup.setOnCheckedChangeListener(this);
        mSegmentedGroup.check(R.id.btnInterestRate);
    }

    private void setupViewPager() {
        rateViewPagerAdapter = new RateViewPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(rateViewPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.btnInterestRate:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.btnExchangeRate:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.btnGold:
                mViewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
       switch (i) {
           case 0: mSegmentedGroup.check(R.id.btnInterestRate); break;
           case 1: mSegmentedGroup.check(R.id.btnExchangeRate); break;
           case 2: mSegmentedGroup.check(R.id.btnGold); break;
       }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

package com.hungtran.bankingassistant.ui.maket;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.RateViewPagerAdapter;
import com.hungtran.bankingassistant.util.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.android.segmented.SegmentedGroup;

public class MaketFragment extends BaseFragment {

    @BindView(R.id.segmented)
    SegmentedGroup mSegmented;

    @BindView(R.id.rateViewPager)
    ViewPager mViewPager;

    @BindView(R.id.btnInterestRate)
    RadioButton btnInterestRate;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_rate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mSegmented.setTintColor(R.color.colorPrimary);
        btnInterestRate.setChecked(true);
        setupViewPager();
    }

    private void setupViewPager() {
        RateViewPagerAdapter rateViewPagerAdapter = new RateViewPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(rateViewPagerAdapter);
    }
}

package com.hungtran.bankingassistant.ui.maket.interestRate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.ui.maket.interestRate.enterprise.EnterpriseInterestRateFragment;
import com.hungtran.bankingassistant.ui.maket.interestRate.personal.PersonalInterestRateFragment;
import com.hungtran.bankingassistant.util.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.android.segmented.SegmentedGroup;

public class InterestRateFragment extends BaseFragment implements SegmentedGroup.OnCheckedChangeListener {

    @BindView(R.id.rateFrameLayout)
    FrameLayout mFrameLayout;
    @BindView(R.id.rateSegmented)
    SegmentedGroup mSegmentedGroup;

    private static InterestRateFragment instance;
    private EnterpriseInterestRateFragment mEnterpriseInterestRateFragment;
    private PersonalInterestRateFragment mPersonalInterestRateFragment;

    private FragmentTransaction mFragmentTransaction;


    public static InterestRateFragment getInstance() {
        if (instance == null) {
            instance = new InterestRateFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_interest_rate;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
//        setupFrameLayout();
        mSegmentedGroup.setTintColor(Color.parseColor("#008577"));
        mSegmentedGroup.setOnCheckedChangeListener(this);
        mSegmentedGroup.check(R.id.btnRatePersonalRate);
    }

    private void setupFrameLayout() {
        mFragmentTransaction = getFragmentManager().beginTransaction();
        mPersonalInterestRateFragment = PersonalInterestRateFragment.getInstance();
        mFragmentTransaction.replace(R.id.rateFrameLayout, mPersonalInterestRateFragment);
        mFragmentTransaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.btnRatePersonalRate:
                mFragmentTransaction = getFragmentManager().beginTransaction();
                mPersonalInterestRateFragment = PersonalInterestRateFragment.getInstance();
                mFragmentTransaction.replace(R.id.rateFrameLayout, mPersonalInterestRateFragment, PersonalInterestRateFragment.class.getName());
                mFragmentTransaction.commit();
                break;
            case R.id.btnRateEnterpriseRate:
                mFragmentTransaction = getFragmentManager().beginTransaction();
                mEnterpriseInterestRateFragment = EnterpriseInterestRateFragment.getInstance();
                mFragmentTransaction.replace(R.id.rateFrameLayout, mEnterpriseInterestRateFragment, EnterpriseInterestRateFragment.class.getName());
                mFragmentTransaction.commit();
                break;
        }
    }
}

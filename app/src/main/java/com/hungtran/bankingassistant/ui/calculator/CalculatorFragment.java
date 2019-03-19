package com.hungtran.bankingassistant.ui.calculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.util.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by hungtd on 3/7/19.
 */

public class CalculatorFragment extends BaseFragment implements View.OnClickListener, SegmentedGroup.OnCheckedChangeListener {
    private static CalculatorFragment instance;
    @BindView(R.id.segmented)
    SegmentedGroup mSegmentedGroup;
    private FragmentTransaction mFragmentTransaction;

    public static CalculatorFragment getInstance() {
        if (instance == null) {
            instance = new CalculatorFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_calculator;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mSegmentedGroup.setTintColor(Color.parseColor("#008577"));
        mSegmentedGroup.setOnCheckedChangeListener(this);
        mSegmentedGroup.check(R.id.btnSavingMoney);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.btnSavingMoney:
                mFragmentTransaction = getFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.calculatorFrameLayout, new CalculateSavingMoneyFragment());
                mFragmentTransaction.commit();
                break;
            case R.id.btnLoadMoney:
                mFragmentTransaction = getFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.calculatorFrameLayout, new CalculateLoanMoneyFragment());
                mFragmentTransaction.commit();
                break;
        }
    }
}

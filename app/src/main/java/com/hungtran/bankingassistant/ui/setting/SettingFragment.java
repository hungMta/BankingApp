package com.hungtran.bankingassistant.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.ui.calculator.CalculatorActivity;
import com.hungtran.bankingassistant.ui.changePassword.ChangePasswordAcitvity;
import com.hungtran.bankingassistant.ui.login.LoginActivty;
import com.hungtran.bankingassistant.util.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.layoutCalculator)
    LinearLayout mLayoutCalculator;

    @BindView(R.id.layoutChangePassword)
    LinearLayout mLayoutChangePassword;

    @BindView(R.id.layoutLogout)
    LinearLayout mLayoutLogout;

    private static SettingFragment instance;

    public static SettingFragment getInstance() {
        if (instance == null) {
            instance = new SettingFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mLayoutCalculator.setOnClickListener(this);
        mLayoutChangePassword.setOnClickListener(this);
        mLayoutLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layoutCalculator:
                Intent intent = new Intent(getContext(), CalculatorActivity.class);
                startActivity(intent);
                break;
            case R.id.layoutChangePassword:
                Intent intent1 = new Intent(getContext(), ChangePasswordAcitvity.class);
                startActivity(intent1);
                break;
            case R.id.layoutLogout:
                Intent intent2 = new Intent(getContext(), LoginActivty.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
                break;
        }
    }
}



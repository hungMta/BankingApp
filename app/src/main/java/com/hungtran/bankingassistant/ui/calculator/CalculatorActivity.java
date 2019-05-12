package com.hungtran.bankingassistant.ui.calculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalculatorActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_calculator;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        CalculatorFragment calculatorFragment = CalculatorFragment.getInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, calculatorFragment)
                .commit();

        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

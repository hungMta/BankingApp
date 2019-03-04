package com.hungtran.bankingassistant.ui.maket.interestRate.enterprise;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.util.base.BaseFragment;

public class EnterpriseInterestRateFragment extends BaseFragment {
    private static EnterpriseInterestRateFragment instance;

    public static EnterpriseInterestRateFragment getInstance() {
        if (instance == null) {
            instance = new EnterpriseInterestRateFragment();
        }
        return instance;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_enterprise_interest_rate;
    }
}

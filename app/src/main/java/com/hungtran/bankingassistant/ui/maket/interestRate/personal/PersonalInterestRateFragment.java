package com.hungtran.bankingassistant.ui.maket.interestRate.personal;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.util.base.BaseFragment;

public class PersonalInterestRateFragment extends BaseFragment {

    private static PersonalInterestRateFragment instance;

    public static PersonalInterestRateFragment getInstance(){
        if (instance == null) {
            instance = new PersonalInterestRateFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal_interest_rate;
    }
}

package com.hungtran.bankingassistant.ui.maket.interestRate.personal;

import com.hungtran.bankingassistant.model.InterestRateResponse;
import com.hungtran.bankingassistant.util.BasePresenter;
import com.hungtran.bankingassistant.util.BaseView;

/**
 * Created by hungtd on 3/11/19.
 */

public interface PersonalInterestRateContract  {

    interface View extends BaseView<PersonalInterestRateContract.Presenter> {
        void onGetPersonalInterestRateSuccess(InterestRateResponse interestRateResponse);
    }

    interface Presenter extends BasePresenter {
        void getPersonalInterestRate();
    }
}

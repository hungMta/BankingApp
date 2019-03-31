package com.hungtran.bankingassistant.ui.maket.interestRate.personal;

import android.content.Context;

import com.hungtran.bankingassistant.model.interestRate.InterestRateResponse;
import com.hungtran.bankingassistant.util.BasePresenter;
import com.hungtran.bankingassistant.util.BaseView;

/**
 * Created by hungtd on 3/11/19.
 */

public interface PersonalInterestRateContract  {

    interface View extends BaseView<PersonalInterestRateContract.Presenter> {
        void onGetPersonalInterestRateSuccess(InterestRateResponse interestRateResponse);
        void onGetRateType(int id, int rateType);
    }

    interface Presenter extends BasePresenter {
        void getPersonalInterestRate();
        void getRateTypeFromString(Context context, int id, String timeDeposit);
        String getStringFromRateType(Context context, int rateType);
    }
}

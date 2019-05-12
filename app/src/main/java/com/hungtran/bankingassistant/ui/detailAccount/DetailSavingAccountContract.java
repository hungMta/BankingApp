package com.hungtran.bankingassistant.ui.detailAccount;

import com.hungtran.bankingassistant.model.interestRate.InterestRate;

public interface DetailSavingAccountContract  {
    interface View {
        void getInterestRateSuccess(InterestRate interestRate);
        void getInterestRateFail(String message);
        void hideProgressBar();
    }

    interface Presenter {
        void getInterestRate(int idBank);
    }
}

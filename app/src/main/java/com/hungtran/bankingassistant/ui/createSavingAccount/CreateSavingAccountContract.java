package com.hungtran.bankingassistant.ui.createSavingAccount;

import com.hungtran.bankingassistant.model.interestRate.InterestRate;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;

public interface CreateSavingAccountContract {

    interface View {
        void getInterestRateSuccess(InterestRate interestRate);
        void getInterestRateFail();
        void hideProgressBar();

        void openOTPActivity(int transactionId);
        void createSavingAccountFail(String message);
    }

    interface Prenseter {
        void getInterestRate(int idBank);

        void createSavingAccount(DataAcount dataAcount, int idBank, int term, double money, double interestRate);
    }

}

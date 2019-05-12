package com.hungtran.bankingassistant.ui.changeSavingMoneyBank;

import com.hungtran.bankingassistant.model.bank.Bank;
import com.hungtran.bankingassistant.model.interestRate.InterestRate;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;
import com.hungtran.bankingassistant.model.respone.DataAccount.SavingAccount;

import java.util.List;

public interface ChangeSavingMoneyBankContract {

    interface View {
        void getAvaibleBankLinkingSuccess(List<Bank> banks);

        void getAvaibleBankLinkingFail();

        void hideProgressBar();

        void openOTPActivity(int transactionId);

        void changeSavineMoneyBankFail(String message);

        void getInterestRateSuccess(InterestRate interestRate);

        void getInterestRateFail();
    }

    interface Presenter {
        void getAvaibleBankLinking();

        void getInterestRate(int idBank);

        void trasnferMoney(DataAcount dataAcount, SavingAccount savingAccount,
                           int idFromBank,
                           int idToBank, String receivingAccount, String receivingName,
                           int term, double interestRate);
    }
}

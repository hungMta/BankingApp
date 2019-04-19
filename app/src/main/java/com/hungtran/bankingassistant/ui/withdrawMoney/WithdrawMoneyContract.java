package com.hungtran.bankingassistant.ui.withdrawMoney;

import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;
import com.hungtran.bankingassistant.model.respone.DataAccount.SavingAccount;

public interface WithdrawMoneyContract {

    interface View {

        void hideProgressBar();

        void openOTPActivity(int transactionId);

        void withDrawMoneyFail(String message);
    }

    interface Presenter {
        void withDrawMoney(DataAcount dataAcount, SavingAccount savingAccount, int idBank, long withDrawMoney);
    }

}

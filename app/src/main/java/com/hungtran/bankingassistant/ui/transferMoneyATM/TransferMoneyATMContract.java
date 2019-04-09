package com.hungtran.bankingassistant.ui.transferMoneyATM;

import com.hungtran.bankingassistant.model.bank.Bank;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;

import java.util.List;

public interface TransferMoneyATMContract {
    interface View {
        void getAvaibleBankLinkingSuccess(List<Bank> banks);

        void getAvaibleBankLinkingFail();

        void hideProgressBar();

        void openOTPActivity(int transactionId);
        void transferMoneyFail(String message);
    }

    interface Presenter {
        void getAvaibleBankLinking();
        void trasnferMoney(DataAcount dataAcount, int idFromBank, int idToBank, String receivingName, double money);
    }
}

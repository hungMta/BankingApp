package com.hungtran.bankingassistant.ui.AvaibleBankLinkingAcitivity;

import com.hungtran.bankingassistant.model.bank.Bank;

import java.util.List;

public interface AvaiableBankLinkingContract {

    interface View {
        void getAvaibleBankLinkingSuccess(List<Bank> banks);

        void getAvaibleBankLinkingFail();

        void hideProgressBar();
    }

    interface Presenter {
        void getAvaibleBankLinking();
    }

}

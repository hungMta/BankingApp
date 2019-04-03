package com.hungtran.bankingassistant.ui.linkingBankMockup;

import com.hungtran.bankingassistant.model.bank.Bank;
import com.hungtran.bankingassistant.model.linkingBank.LinkingBank;

public interface LinkingBankMockupContract {

    interface View {
        void linkingBankSuccess();

        void linkingBankError();

        void hideProgressBar();
    }

    interface Presenter {
        void linkingBank(Bank targetBank, LinkingBank linkingBank);
    }
}

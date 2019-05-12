package com.hungtran.bankingassistant.ui.wallet;

import com.hungtran.bankingassistant.model.bank.Bank;

import java.util.List;

public interface WalletContract {
    interface View {
        void hideProgressBar();
        void loadLinkingBankListSuccess(List<Bank> list);
        void loadLinkingBankListFail();
    }

    interface Presenter {
        void loadLinkingBankList();
    }
}

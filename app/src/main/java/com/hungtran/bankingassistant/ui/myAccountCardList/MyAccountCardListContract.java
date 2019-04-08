package com.hungtran.bankingassistant.ui.myAccountCardList;

import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;

public interface MyAccountCardListContract {
    interface View {
        void getDataAccountSuccess(DataAcount dataAcount);
        void getDataAccountFail();
        void hideProgressBar();
    }

    interface Presenter {
        void getDataAccount(int idBank);
    }
}

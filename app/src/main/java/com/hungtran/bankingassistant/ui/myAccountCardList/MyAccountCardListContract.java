package com.hungtran.bankingassistant.ui.myAccountCardList;

public interface MyAccountCardListContract {
    interface View {
        void getDataAccountSuccess();
        void getDataAccountFail();
        void hideProgressBar();
    }

    interface Presenter {
        void getDataAccount(int idBank);
    }
}

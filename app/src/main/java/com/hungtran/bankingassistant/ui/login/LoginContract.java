package com.hungtran.bankingassistant.ui.login;

import com.hungtran.bankingassistant.model.user.Account;

public class LoginContract {

    interface View {
        void loginSuccess();

        void loginError(String message);

        void hideProgressBar();
    }

    interface Presenter {
        void login(Account account);
    }
}

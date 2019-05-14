package com.hungtran.bankingassistant.ui.register;

/**
 * Created by hungtd on 5/14/19.
 */

public interface RegisterContract {

    interface View {
        void hideProgressBar();

        void verifyEmailFail(String message);

        void verifyEmailSuccess();

        void registerSuccess();

        void registerFail(String message);
    }


    interface Presenter {
        void verifyEmail(String email);

        void register();
    }

}

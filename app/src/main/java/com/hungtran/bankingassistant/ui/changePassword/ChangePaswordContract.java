package com.hungtran.bankingassistant.ui.changePassword;

/**
 * Created by hungtd on 5/14/19.
 */

public interface ChangePaswordContract {

    interface View {
        void getOTPFail(String message);

        void getOTPSuccess();

        void hideProgress();
    }

    interface Presenter {
        void getOTP();
    }
}

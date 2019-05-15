package com.hungtran.bankingassistant.ui.forgetPassword;

/**
 * Created by hungtd on 5/15/19.
 */

public interface ForgetPasswordContract {

    interface View {
        void getOTPSuccess();

        void getOTPFail(String message);

        void hideProgress();
    }

    interface Presenter {
        void getOTP();
    }
}

package com.hungtran.bankingassistant.ui.pressOTP;

import com.hungtran.bankingassistant.model.register.RegisterRequest;

public interface OTPContract {
    interface View {
        void submitOTPSuccess();
        void submitOTPFail(String message);
        void hideProgressBar();

        void registerSuccess();
        void registerFail(String message);
    }

    interface Presenter {
        void submitOTP(int transactionId, int OTP);

        void registerAccount(RegisterRequest registerRequest);
    }
}

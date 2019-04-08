package com.hungtran.bankingassistant.ui.pressOTP;

public interface OTPContract {
    interface View {
        void submitOTPSuccess();
        void submitOTPFail(String message);
        void hideProgressBar();
    }

    interface Presenter {
        void submitOTP(int transactionId, int OTP);
    }
}

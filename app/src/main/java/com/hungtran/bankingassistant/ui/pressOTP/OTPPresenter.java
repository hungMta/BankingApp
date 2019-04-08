package com.hungtran.bankingassistant.ui.pressOTP;

import android.content.Context;
import android.util.Log;

import com.hungtran.bankingassistant.model.interestRate.InterestRateByBank;
import com.hungtran.bankingassistant.model.interestRate.InterestRateResponse;
import com.hungtran.bankingassistant.model.otp.OTPModel;
import com.hungtran.bankingassistant.model.otp.OTPModelRequest;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.SharePreference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static android.support.constraint.Constraints.TAG;

public class OTPPresenter implements OTPContract.Presenter {

    private OTPContract.View mView;

    private Context mContext;

    public OTPPresenter(OTPContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void submitOTP(int transactionId, int OTP) {
        OTPModel otpModel = new OTPModel(OTP, transactionId);
        OTPModelRequest otpModelRequest = new OTPModelRequest(otpModel);
        submitOTPObserverable(otpModelRequest).subscribeWith(submitOTPObserver());
    }

    private Observable<retrofit2.Response<Void>> submitOTPObserverable(OTPModelRequest otpModel) {
        return ServiceGenerator.resquest()
                .submitOTP(SharePreference.getStringVal(Constant.TOKEN_KEY), otpModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<retrofit2.Response<Void>> submitOTPObserver() {
        return new DisposableObserver<retrofit2.Response<Void>>() {
            @Override
            public void onNext(retrofit2.Response<Void> response) {
                if (response.code() == 200) {
                    mView.submitOTPSuccess();
                } else {
                    mView.submitOTPFail(Constant.ERROR_OTP);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
                mView.hideProgressBar();
            }

            @Override
            public void onComplete() {
                mView.hideProgressBar();
            }
        };
    }
}

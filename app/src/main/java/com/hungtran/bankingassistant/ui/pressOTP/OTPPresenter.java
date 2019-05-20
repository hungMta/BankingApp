package com.hungtran.bankingassistant.ui.pressOTP;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.hungtran.bankingassistant.model.base.BaseResponse;
import com.hungtran.bankingassistant.model.error.AppError;
import com.hungtran.bankingassistant.model.error.AppErrors;
import com.hungtran.bankingassistant.model.interestRate.InterestRateByBank;
import com.hungtran.bankingassistant.model.interestRate.InterestRateResponse;
import com.hungtran.bankingassistant.model.otp.OTPModel;
import com.hungtran.bankingassistant.model.otp.OTPModelRequest;
import com.hungtran.bankingassistant.model.register.RegisterRequest;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.SharePreference;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

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

    @Override
    public void registerAccount(RegisterRequest registerRequest) {
        registerObsererable(registerRequest).subscribeWith(registerObserver());
    }

    @Override
    public void changePassword(RegisterRequest registerRequest) {
        changePasswordObservable(registerRequest).subscribeWith(changePasswordObserver());
    }

    @Override
    public void forgotPassword(RegisterRequest registerRequest) {
        forgotPasswordObservable(registerRequest).subscribeWith(forgotPasswordObserver());
    }

    private Observable<BaseResponse> forgotPasswordObservable(RegisterRequest registerRequest) {
        return ServiceGenerator.resquest().forgotPasswordSubmit(registerRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<BaseResponse> forgotPasswordObserver() {
        return new DisposableObserver<BaseResponse>() {
            @Override
            public void onNext(BaseResponse response) {
                mView.forgotPasswordSuccess();
            }

            @Override
            public void onError(Throwable e) {
                try {
                    String error = ((HttpException) e).response().errorBody().string().toString();
                    String message = AppError.mapError(error);
                    mView.forgotPasswordFail(message);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                mView.hideProgressBar();
            }

            @Override
            public void onComplete() {
                mView.hideProgressBar();
            }
        };
    }

    private Observable<BaseResponse> changePasswordObservable(RegisterRequest registerRequest) {
        return ServiceGenerator.resquest().changePassword(SharePreference.getStringVal(Constant.TOKEN_KEY), registerRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<BaseResponse> changePasswordObserver() {
        return new DisposableObserver<BaseResponse>() {
            @Override
            public void onNext(BaseResponse response) {
                mView.changePasswordSuccess();
            }

            @Override
            public void onError(Throwable e) {
                String error = "";
                try {
                    error = ((HttpException) e).response().errorBody().string().toString();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                String message = AppError.mapError(error);
                mView.changePasswordFail(message);
                mView.hideProgressBar();
            }

            @Override
            public void onComplete() {
                mView.hideProgressBar();
            }
        };
    }


    private Observable<retrofit2.Response<Void>> registerObsererable(RegisterRequest registerRequest) {
        return ServiceGenerator.resquest()
                .registerAccount(registerRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<retrofit2.Response<Void>> registerObserver() {
        return new DisposableObserver<retrofit2.Response<Void>>() {

            @Override
            public void onNext(Response<Void> voidResponse) {
                mView.registerSuccess();
            }

            @Override
            public void onError(Throwable e) {
                String error = null;
                try {
                    error = ((HttpException) e).response().errorBody().string().toString();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                String message = AppError.mapError(error);
                mView.registerFail(message);
                mView.hideProgressBar();
            }

            @Override
            public void onComplete() {
                mView.hideProgressBar();
            }
        };
    }


    private Observable<BaseResponse> submitOTPObserverable(OTPModelRequest otpModel) {
        return ServiceGenerator.resquest()
                .submitOTP(SharePreference.getStringVal(Constant.TOKEN_KEY), otpModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<BaseResponse> submitOTPObserver() {
        return new DisposableObserver<BaseResponse>() {
            @Override
            public void onNext(BaseResponse response) {
                mView.submitOTPSuccess();
//                if (response.code() == 200) {
//                    mView.submitOTPSuccess();
//                } else {
//                    try {
//                        assert response.errorBody() != null;
//                        String responseError = response.errorBody().string().toString();
//                        Log.d(TAG, "onNext: ");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    if (response.errorBody() != null) {
//                        mView.submitOTPFail(AppError.mapError(response.errorBody()));
//                    } else {
//                        mView.submitOTPFail(Constant.ERROR_OTP);
//                    }
//                }
            }

            @Override
            public void onError(Throwable e) {

                try {
                    String error = "";
                    error = ((HttpException) e).response().errorBody().string().toString();
                    String message = AppError.mapError(error);
                    mView.submitOTPFail(message);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                mView.hideProgressBar();
                Log.d(TAG, "onError: " + e.getMessage());
//                try {
//                    String errorMsg = AppError.mapError(e);
//                    mView.submitOTPFail(errorMsg);
//                } catch (Exception e1) {
//                    String errorMsg = Constant.ERROR_UNKNOWN;
//                    mView.submitOTPFail(errorMsg);
//                } finally {
//                    mView.hideProgressBar();
//                }
            }

            @Override
            public void onComplete() {
                mView.hideProgressBar();
            }
        };
    }
}

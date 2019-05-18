package com.hungtran.bankingassistant.ui.forgetPassword;

import com.hungtran.bankingassistant.model.base.BaseResponse;
import com.hungtran.bankingassistant.model.error.AppError;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.SharePreference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hungtd on 5/15/19.
 */

public class ForgetPasswordPresenter implements ForgetPasswordContract.Presenter {


    ForgetPasswordContract.View mView;

    public ForgetPasswordPresenter(ForgetPasswordContract.View view) {
        this.mView = view;
    }

    @Override
    public void getOTP() {
        getOTPObservable().subscribeWith(getOTPObserver());
    }


    public Observable<BaseResponse> getOTPObservable() {
        return ServiceGenerator.resquest().requestForgotPassword(SharePreference.getStringVal(Constant.TOKEN_KEY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<BaseResponse> getOTPObserver() {
        return new DisposableObserver<BaseResponse>() {
            @Override
            public void onNext(BaseResponse response) {
                mView.getOTPSuccess();
            }

            @Override
            public void onError(Throwable e) {
                mView.getOTPFail(AppError.mapError(e));
                mView.hideProgress();
            }

            @Override
            public void onComplete() {
                mView.hideProgress();
            }
        };
    }
}

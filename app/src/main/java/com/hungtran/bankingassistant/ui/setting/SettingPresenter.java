package com.hungtran.bankingassistant.ui.setting;

import android.util.Log;

import com.hungtran.bankingassistant.model.base.BaseResponse;
import com.hungtran.bankingassistant.model.transactionHistory.TransactionHistoryRequest;
import com.hungtran.bankingassistant.model.transactionHistory.TransactionHistoryResponse;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.SharePreference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by hungtd on 2/18/19.
 */

public class SettingPresenter implements SettingContract.Presenter {

    public static final String TAG = "hungtd";

    private SettingContract.View mView;

    public SettingPresenter(SettingContract.View view) {
        this.mView = view;
    }

    @Override
    public void logout() {
        logoutObservable().subscribeWith(logoutObserver());
    }

    private Observable<BaseResponse> logoutObservable() {
        return ServiceGenerator.resquest()
                .logout(SharePreference.getStringVal(Constant.TOKEN_KEY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<BaseResponse> logoutObserver() {
        return new DisposableObserver<BaseResponse>() {
            @Override
            public void onNext(BaseResponse response) {
                Log.d(TAG, "onNext: logout success");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        };
    }
}

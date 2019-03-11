package com.hungtran.bankingassistant.ui.maket.interestRate.personal;

import android.util.Log;

import com.hungtran.bankingassistant.model.ExchangeRateResponse;
import com.hungtran.bankingassistant.model.InterestRateResponse;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hungtd on 3/11/19.
 */

public class PersonalInterestRatePresenter implements PersonalInterestRateContract.Presenter {

    private static String TAG = "HUNGTD";
    private PersonalInterestRateContract.View mView;


    public PersonalInterestRatePresenter(PersonalInterestRateContract.View view) {
        this.mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void getPersonalInterestRate() {
        getInterestRateObserverable().subscribeWith(getInterestRateObserver());
    }

    private Observable<InterestRateResponse> getInterestRateObserverable() {
        return ServiceGenerator.resquest()
                .getInterestRate(Constant.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<InterestRateResponse> getInterestRateObserver() {
        return new DisposableObserver<InterestRateResponse>() {
            @Override
            public void onNext(InterestRateResponse interestRateResponse) {
                Log.d(TAG, "onNext: " + interestRateResponse);
                mView.onGetPersonalInterestRateSuccess(interestRateResponse);
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

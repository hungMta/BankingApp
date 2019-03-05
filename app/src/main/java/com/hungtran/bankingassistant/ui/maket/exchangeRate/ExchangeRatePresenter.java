package com.hungtran.bankingassistant.ui.maket.exchangeRate;

import android.util.Log;

import com.hungtran.bankingassistant.model.ExchangeRateResponse;
import com.hungtran.bankingassistant.network.ServiceGenerator;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ExchangeRatePresenter implements ExchangeRateContract.Presenter {

    private static String TAG = "HUNGTD";


    private ExchangeRateContract.View mView;

    public ExchangeRatePresenter(ExchangeRateContract.View view) {
        this.mView = view;
    }

    @Override
    public void getExchangeRates() {
        getExchangeRateObservable().subscribeWith(getExchangeRateObserver());
    }

    @Override
    public void start() {

    }

    private Observable<ExchangeRateResponse> getExchangeRateObservable() {
        return ServiceGenerator.resquest()
                .getExchangeRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<ExchangeRateResponse> getExchangeRateObserver() {
        return new DisposableObserver<ExchangeRateResponse>() {
            @Override
            public void onNext(ExchangeRateResponse exchangeRateResponse) {
                Log.d(TAG, "onNext: " + exchangeRateResponse);
                exchangeRateResponse.formatMoney();
                mView.showExchangeRates(exchangeRateResponse.getExchangeRates());
                mView.hideProgressBar();
            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgressBar();
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                mView.hideProgressBar();
            }
        };
    }
}

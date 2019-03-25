package com.hungtran.bankingassistant.ui.maket.gold;

import android.util.Log;

import com.hungtran.bankingassistant.model.gold.Gold;
import com.hungtran.bankingassistant.model.gold.GoldArea;
import com.hungtran.bankingassistant.model.gold.GoldAreaResponse;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class GoldPresenter implements GoldContract.Presenter{

    private static String TAG = "HUNGTD";

    private GoldContract.View mView;

    public GoldPresenter(GoldContract.View view) {
        this.mView = view;
    }

    @Override
    public void getGoldPrice() {
        getGoldAreaObservable().subscribeWith(getGoldAreaObserver());
//        mView.onGetGoldPriceSuccess(fakeData());
    }

    private Observable<GoldAreaResponse> getGoldAreaObservable() {
        return ServiceGenerator.resquest()
                .getGoldArea(Constant.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<GoldAreaResponse> getGoldAreaObserver() {
        return  new DisposableObserver<GoldAreaResponse>() {
            @Override
            public void onNext(GoldAreaResponse goldAreaResponse) {
                Log.d(TAG, "onNext: " + goldAreaResponse);
                mView.onGetGoldPriceSuccess(goldAreaResponse);
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

    @Override
    public void start() {

    }
}

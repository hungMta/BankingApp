package com.hungtran.bankingassistant.ui.map;

import android.util.Log;

import com.hungtran.bankingassistant.model.area.AreaResponse;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationRequest;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationRequestBody;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationResponse;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hungtd on 2/18/19.
 */

public class MapPresenter implements MapConstract.Presenter {

    private static final String TAG = "HUNGTD";
    private MapConstract.View mView;

    public MapPresenter(MapConstract.View view) {
        this.mView = view;
    }


    @Override
    public void searchBankPosition(BankLocationRequestBody bankLocationRequestBody) {
        getBankLocationObservabable(bankLocationRequestBody).subscribeWith(getBankLocationObserver());
    }

    @Override
    public void getArea() {
        getAreaObservabable().subscribeWith(getAreaObserver());
    }


    private Observable<BankLocationResponse> getBankLocationObservabable(BankLocationRequestBody bankLocationRequestBody) {
        BankLocationRequest bankLocationRequest = new BankLocationRequest(bankLocationRequestBody);
        return ServiceGenerator.resquest()
                .searchBankLocation(Constant.TOKEN, bankLocationRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<BankLocationResponse> getBankLocationObserver() {
        return new DisposableObserver<BankLocationResponse>() {
            @Override
            public void onNext(BankLocationResponse bankLocationResponse) {
                mView.searchBankPositionResult(bankLocationResponse);
            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgress();
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                mView.hideProgress();
                Log.d(TAG, "onComplete: ");
            }
        };
    }

    private Observable<AreaResponse> getAreaObservabable() {
        return ServiceGenerator.resquest().getArea(Constant.TOKEN, 2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<AreaResponse> getAreaObserver(){
        return new DisposableObserver<AreaResponse>() {
            @Override
            public void onNext(AreaResponse areaResponse) {
                mView.getAreaSuccess(areaResponse);
            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgress();
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                mView.hideProgress();
                Log.d(TAG, "onComplete: ");
            }
        };
    }
}

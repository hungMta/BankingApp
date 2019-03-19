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

    private GoldAreaResponse fakeData() {
        Gold gold = new Gold("SJC", "123.45", "123.45");
        Gold gold2 = new Gold("PNG", "111.45", "111.45");
        List<Gold> golds = new ArrayList<>();
        golds.add(gold);
        golds.add(gold2);
        GoldArea goldArea1 = new GoldArea("HCM", "12312312",golds);


        GoldArea goldArea2 = new GoldArea("HN", "12312312",golds);


        GoldArea goldArea3 = new GoldArea("DN", "12312312",golds);


        GoldArea goldArea4 = new GoldArea("ABC", "12312312",golds);

        List<GoldArea> list = new ArrayList<>();
        list.add(goldArea1);
        list.add(goldArea2);
        list.add(goldArea3);
        list.add(goldArea4);
        GoldAreaResponse goldAreaResponse = new GoldAreaResponse(list);
        return  goldAreaResponse;
    }
}

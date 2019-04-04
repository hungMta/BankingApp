package com.hungtran.bankingassistant.ui.myAccountCardList;

import android.content.Context;
import android.util.Log;

import com.hungtran.bankingassistant.model.bank.Bank;
import com.hungtran.bankingassistant.model.bank.BankResponse;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAccountRespone;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.SharePreference;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MyAccountCardListPresenter implements MyAccountCardListContract.Presenter {
    private MyAccountCardListContract.View mView;
    private Context mContext;

    public MyAccountCardListPresenter(Context context, MyAccountCardListContract.View view) {
        this.mContext = context;
        this.mView = view;
    }

    @Override
    public void getDataAccount(int idBank) {
        getDataAccountObservable(idBank).subscribeWith(getDataAccountObserver());
    }

    private Observable<DataAccountRespone> getDataAccountObservable(int idBank) {
        return ServiceGenerator.resquest().getDataAccount(SharePreference.getStringVal(Constant.TOKEN_KEY),idBank)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<DataAccountRespone> getDataAccountObserver() {
        return new DisposableObserver<DataAccountRespone>() {
            @Override
            public void onNext(DataAccountRespone response) {
                Log.d("hungtd", "onNext:  successs");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("hungtd", "onNext:  error " + e.getMessage());
                mView.hideProgressBar();
            }

            @Override
            public void onComplete() {
                Log.d("hungtd", "onComplete: ");
                mView.hideProgressBar();
            }
        };
    }
}

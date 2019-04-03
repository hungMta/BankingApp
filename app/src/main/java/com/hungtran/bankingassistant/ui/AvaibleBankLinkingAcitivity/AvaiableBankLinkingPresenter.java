package com.hungtran.bankingassistant.ui.AvaibleBankLinkingAcitivity;

import android.content.Context;
import android.util.Log;

import com.hungtran.bankingassistant.model.bank.Bank;
import com.hungtran.bankingassistant.model.bank.BankLinkingResponse;
import com.hungtran.bankingassistant.model.bank.BankResponse;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.SharePreference;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AvaiableBankLinkingPresenter implements AvaiableBankLinkingContract.Presenter {

    AvaiableBankLinkingContract.View mView;
    Context mContext;

    public AvaiableBankLinkingPresenter(AvaiableBankLinkingContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void getAvaibleBankLinking() {
     getAllBankLinkingObservable().subscribeWith(getAllBankLinkingObserver());
    }


    private Observable<BankLinkingResponse> getAllBankLinkingObservable() {
        return ServiceGenerator.resquest().getAllBankLinking(SharePreference.getStringVal(Constant.TOKEN_KEY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<BankLinkingResponse> getAllBankLinkingObserver() {
        return new DisposableObserver<BankLinkingResponse>() {
            @Override
            public void onNext(BankLinkingResponse response) {
                Log.d("hungtd", "onNext:  successs");
                mView.getAvaibleBankLinkingSuccess(response.getList());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("hungtd", "onNext:  error " + e.getMessage());
                mView.hideProgressBar();
                mView.getAvaibleBankLinkingFail();
            }

            @Override
            public void onComplete() {
                Log.d("hungtd", "onComplete: ");
                mView.hideProgressBar();
            }
        };
    }
}

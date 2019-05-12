package com.hungtran.bankingassistant.ui.wallet;

import android.content.Context;
import android.util.Log;

import com.hungtran.bankingassistant.model.bank.Bank;
import com.hungtran.bankingassistant.model.bank.BankResponse;
import com.hungtran.bankingassistant.model.base.BaseResponse;
import com.hungtran.bankingassistant.model.firebase.FCMTokenRequest;
import com.hungtran.bankingassistant.network.LinkingBankServiceGenerator;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.SharePreference;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class WalletPresenter implements WalletContract.Presenter {

    private WalletContract.View mView;
    private Context mContext;


    public WalletPresenter(Context context, WalletContract.View view) {
        this.mContext = context;
        this.mView = view;
    }

    @Override
    public void loadLinkingBankList() {
        getAllBankLinkedObservable().subscribeWith(getAllBankLinkedObserver());
    }

    private Observable<BankResponse> getAllBankLinkedObservable() {
        return ServiceGenerator.resquest().getAllBankLinked(SharePreference.getStringVal(Constant.TOKEN_KEY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<BankResponse> getAllBankLinkedObserver() {
        return new DisposableObserver<BankResponse>() {
            @Override
            public void onNext(BankResponse response) {
                Log.d("hungtd", "onNext:  successs");
                List<Bank> list = new ArrayList<>();
                for (List<Bank> list1: response.getBanks()) {
                    list.add(list1.get(1));
                }
                mView.loadLinkingBankListSuccess(list);
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

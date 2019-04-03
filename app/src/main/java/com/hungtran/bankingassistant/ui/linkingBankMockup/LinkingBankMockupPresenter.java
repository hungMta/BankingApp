package com.hungtran.bankingassistant.ui.linkingBankMockup;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.hungtran.bankingassistant.model.bank.Bank;
import com.hungtran.bankingassistant.model.base.BaseResponse;
import com.hungtran.bankingassistant.model.firebase.FCMTokenRequest;
import com.hungtran.bankingassistant.model.linkingBank.LinkBankRequest;
import com.hungtran.bankingassistant.model.linkingBank.LinkingBank;
import com.hungtran.bankingassistant.network.LinkingBankServiceGenerator;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.SharePreference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LinkingBankMockupPresenter implements LinkingBankMockupContract.Presenter {

    private LinkingBankMockupContract.View mView;
    private Context mContext;

    public LinkingBankMockupPresenter(Context context, LinkingBankMockupContract.View view) {
        this.mContext = context;
        this.mView = view;
    }


    @Override
    public void linkingBank(Bank targetBank, LinkingBank linkingBank) {
        LinkBankRequest linkBankRequest = new LinkBankRequest(linkingBank);
        linkingBankObservable(targetBank, linkBankRequest).subscribeWith(linkingBankObserver());
    }

    private Observable<BaseResponse> linkingBankObservable(Bank targetBank, LinkBankRequest linkBankRequest) {
        switch (targetBank.getId_bank()) {
            case Constant.ID_VCB:
                return LinkingBankServiceGenerator.resquest().linkVCB(linkBankRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            case Constant.ID_BIDV:
                return LinkingBankServiceGenerator.resquest().linkBIDV(linkBankRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            case Constant.ID_AGRI:
                return LinkingBankServiceGenerator.resquest().linkAGRI(linkBankRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            case Constant.ID_VIETTIN:
                return LinkingBankServiceGenerator.resquest().linkVIETTIN(linkBankRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        }
        return LinkingBankServiceGenerator.resquest().linkVCB(linkBankRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<BaseResponse> linkingBankObserver() {
        return new DisposableObserver<BaseResponse>() {
            @Override
            public void onNext(BaseResponse response) {
                Log.d("hungtd", "onNext: successs");
                mView.linkingBankSuccess();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("hungtd", "onNext:  error " + e.getMessage());
                mView.linkingBankError();
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

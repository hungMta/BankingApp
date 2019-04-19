package com.hungtran.bankingassistant.ui.detailAccount;

import android.util.Log;

import com.hungtran.bankingassistant.model.interestRate.InterestRateByBank;
import com.hungtran.bankingassistant.model.interestRate.InterestRateResponse;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.SharePreference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static android.support.constraint.Constraints.TAG;

public class DetailSavingAccountPresenter implements DetailSavingAccountContract.Presenter {

    private DetailSavingAccountContract.View mView;
    private int mIdBank;

    public DetailSavingAccountPresenter(DetailSavingAccountContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getInterestRate(int idBank) {
        mIdBank = idBank;
        getInterestRateObserverable().subscribeWith(getInterestRateObserver());
    }

    private Observable<InterestRateResponse> getInterestRateObserverable() {
        return ServiceGenerator.resquest()
                .getInterestRate(SharePreference.getStringVal(Constant.TOKEN_KEY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<InterestRateResponse> getInterestRateObserver() {
        return new DisposableObserver<InterestRateResponse>() {
            @Override
            public void onNext(InterestRateResponse interestRateResponse) {
                Log.d(TAG, "onNext: " + interestRateResponse);
//                mView.onGetPersonalInterestRateSuccess(interestRateResponse);
                for (InterestRateByBank interestRateByBank : interestRateResponse.getInterestRateByBankList()
                        ) {
                    if (interestRateByBank.getId() == mIdBank) {
                        mView.getInterestRateSuccess(interestRateByBank.getSendingOnline().getInterestRateVnd());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
                mView.getInterestRateFail(e.getMessage());
                mView.hideProgressBar();
            }

            @Override
            public void onComplete() {
                mView.hideProgressBar();
            }
        };
    }

}

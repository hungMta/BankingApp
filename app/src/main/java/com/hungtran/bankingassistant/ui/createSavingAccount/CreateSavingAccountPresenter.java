package com.hungtran.bankingassistant.ui.createSavingAccount;

import android.content.Context;
import android.util.Log;

import com.hungtran.bankingassistant.model.interestRate.InterestRateByBank;
import com.hungtran.bankingassistant.model.interestRate.InterestRateResponse;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;
import com.hungtran.bankingassistant.model.transfer.TransferMoney;
import com.hungtran.bankingassistant.model.transfer.TransferTransactionResponse;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.SharePreference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static android.support.constraint.Constraints.TAG;

public class CreateSavingAccountPresenter
        implements CreateSavingAccountContract.Prenseter {

    private int mIdBank;
    private CreateSavingAccountContract.View mView;
    private Context mContext;

    public CreateSavingAccountPresenter(CreateSavingAccountContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void getInterestRate(int idBank) {
        mIdBank = idBank;
        getInterestRateObserverable().subscribeWith(getInterestRateObserver());
    }

    @Override
    public void createSavingAccount(DataAcount dataAcount, int idBank, int term, long money, double interestRate) {
        TransferMoney transferMoney = new TransferMoney();
        transferMoney.setType(Constant.TRANSFER_ATM_SAVING);
        transferMoney.setFromAccountNumber(dataAcount.getNumberAccount());
        transferMoney.setToAccountNumber(dataAcount.getNumberAccount());
        transferMoney.setIdBankFrom(idBank);
        transferMoney.setIdBankTo(idBank);
        transferMoney.setMoney(money);
        transferMoney.setTerm(term);
        transferMoney.setInterestRate(interestRate);
        transferMoney.setCurrency("VND");
        transferMoney.setBranch("Chi nhánh online");
        transferMoney.setNameTo(dataAcount.getName());
        createSavingAccountObserverable(transferMoney).subscribeWith(createSavingAccountObserver());
    }


    private Observable<TransferTransactionResponse> createSavingAccountObserverable(TransferMoney transferMoney) {
        return ServiceGenerator.resquest()
                .transferMoney(SharePreference.getStringVal(Constant.TOKEN_KEY), transferMoney)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<TransferTransactionResponse> createSavingAccountObserver() {
        return new DisposableObserver<TransferTransactionResponse>() {
            @Override
            public void onNext(TransferTransactionResponse transactionResponse) {
                mView.openOTPActivity(transactionResponse.getTransferTransaction().getTransactionId());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
                mView.createSavingAccountFail(e.getMessage());
                mView.hideProgressBar();
            }

            @Override
            public void onComplete() {
                mView.hideProgressBar();
            }
        };
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
                mView.getInterestRateFail();
                mView.hideProgressBar();
            }

            @Override
            public void onComplete() {
                mView.hideProgressBar();
            }
        };
    }
}

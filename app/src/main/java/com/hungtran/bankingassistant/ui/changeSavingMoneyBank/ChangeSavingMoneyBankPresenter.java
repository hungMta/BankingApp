package com.hungtran.bankingassistant.ui.changeSavingMoneyBank;

import android.util.Log;

import com.hungtran.bankingassistant.model.bank.BankLinkingResponse;
import com.hungtran.bankingassistant.model.interestRate.InterestRateByBank;
import com.hungtran.bankingassistant.model.interestRate.InterestRateResponse;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;
import com.hungtran.bankingassistant.model.respone.DataAccount.SavingAccount;
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

public class ChangeSavingMoneyBankPresenter implements ChangeSavingMoneyBankContract.Presenter {

    private ChangeSavingMoneyBankContract.View mView;
    private int mIdBank;

    public ChangeSavingMoneyBankPresenter(ChangeSavingMoneyBankContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void getAvaibleBankLinking() {
        getAllBankLinkingObservable().subscribeWith(getAllBankLinkingObserver());

    }

    @Override
    public void getInterestRate(int idBank) {
        mIdBank = idBank;
        getInterestRateObserverable().subscribeWith(getInterestRateObserver());
    }

    @Override
    public void trasnferMoney(DataAcount dataAcount, SavingAccount savingAccount,
                              int idFromBank, int idToBank, String receivingAccount,
                              String receivingName, int term, double interestRate) {
        TransferMoney transferMoney = new TransferMoney();
        transferMoney.setType(Constant.TRANSFER_SAVING_SAVING);
        transferMoney.setFromAccountNumber(dataAcount.getNumberAccount());
        transferMoney.setToAccountNumber(receivingAccount);
        transferMoney.setIdBankFrom(idFromBank);
        transferMoney.setIdBankTo(idToBank);
        transferMoney.setTerm(term);
        transferMoney.setInterestRate(interestRate);
//        transferMoney.setMoney(savingAccount.getSavingMoney());
        transferMoney.setNameTo(receivingName.toUpperCase());
        transferMoney.setSavingId(Integer.parseInt(savingAccount.getNumberSaving()));
        transferMoneyObserverable(transferMoney).subscribeWith(transferMoneyObserver());
    }

    private Observable<TransferTransactionResponse> transferMoneyObserverable(TransferMoney transferMoney) {
        return ServiceGenerator.resquest()
                .transferMoney(SharePreference.getStringVal(Constant.TOKEN_KEY), transferMoney)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<TransferTransactionResponse> transferMoneyObserver() {
        return new DisposableObserver<TransferTransactionResponse>() {
            @Override
            public void onNext(TransferTransactionResponse transactionResponse) {
                mView.openOTPActivity(transactionResponse.getTransferTransaction().getTransactionId());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
                mView.changeSavineMoneyBankFail(e.getMessage());
                mView.hideProgressBar();
            }

            @Override
            public void onComplete() {
                mView.hideProgressBar();
            }
        };
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

package com.hungtran.bankingassistant.ui.transactionHistory;

import android.util.Log;

import com.hungtran.bankingassistant.model.transactionHistory.TransactionHistoryRequest;
import com.hungtran.bankingassistant.model.transactionHistory.TransactionHistoryResponse;
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

public class TransactionHistoryPresenter implements TransactionHistoryContract.Presenter {

    TransactionHistoryContract.View mView;

    public TransactionHistoryPresenter(TransactionHistoryContract.View view) {
        this.mView = view;
    }

    @Override
    public void getTransactionHistory(int type, int idBank, int idBankReceive) {
        TransactionHistoryRequest transactionHistoryRequest = new TransactionHistoryRequest();
        transactionHistoryRequest.setType(type);
        transactionHistoryRequest.setIdBankSend(idBank);
        transactionHistoryRequest.setIdBankReceive(idBankReceive);
        getTransactionHistoryObservable(transactionHistoryRequest).subscribeWith(getTransactionHistoryObserver());
    }

    private Observable<TransactionHistoryResponse> getTransactionHistoryObservable(TransactionHistoryRequest request) {
        return ServiceGenerator.resquest()
                .getTransactionHistory(SharePreference.getStringVal(Constant.TOKEN_KEY), request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<TransactionHistoryResponse> getTransactionHistoryObserver() {
        return new DisposableObserver<TransactionHistoryResponse>() {
            @Override
            public void onNext(TransactionHistoryResponse transactionResponse) {
                mView.getTransactionHistorySuccess(transactionResponse.getHistories());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
                mView.getTransactionHistoryFail(e.getMessage());
                mView.hideProgressBar();
            }

            @Override
            public void onComplete() {
                mView.hideProgressBar();
            }
        };
    }

}

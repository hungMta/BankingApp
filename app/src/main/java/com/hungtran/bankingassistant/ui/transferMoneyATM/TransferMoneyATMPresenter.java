package com.hungtran.bankingassistant.ui.transferMoneyATM;

import android.content.Context;
import android.util.Log;

import com.hungtran.bankingassistant.model.bank.BankLinkingResponse;
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

public class TransferMoneyATMPresenter implements TransferMoneyATMContract.Presenter{

    private TransferMoneyATMContract.View mView;

    private Context mContext;

    public TransferMoneyATMPresenter(Context context, TransferMoneyATMContract.View view) {
        mContext = context;
        mView = view;
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

    @Override
    public void getAvaibleBankLinking() {
        getAllBankLinkingObservable().subscribeWith(getAllBankLinkingObserver());
    }

    @Override
    public void trasnferMoney(DataAcount dataAcount, int idFromBank, int idToBank, String receivingAccount, String receivingName, long money) {
        TransferMoney transferMoney = new TransferMoney();
        transferMoney.setType(Constant.TRANSFER_ATM_ATM);
        transferMoney.setFromAccountNumber(dataAcount.getNumberAccount());
        transferMoney.setToAccountNumber(receivingAccount);
        transferMoney.setIdBankFrom(idFromBank);
        transferMoney.setIdBankTo(idToBank);
        transferMoney.setMoney(money);
        transferMoney.setNameTo(receivingName);
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
                mView.transferMoneyFail(e.getMessage());
                mView.hideProgressBar();
            }

            @Override
            public void onComplete() {
                mView.hideProgressBar();
            }
        };
    }

}

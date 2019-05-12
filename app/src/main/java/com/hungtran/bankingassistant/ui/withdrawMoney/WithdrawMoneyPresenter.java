package com.hungtran.bankingassistant.ui.withdrawMoney;

import android.util.Log;

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

public class WithdrawMoneyPresenter implements  WithdrawMoneyContract.Presenter {

    private WithdrawMoneyContract.View mView;

    public WithdrawMoneyPresenter(WithdrawMoneyContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void withDrawMoney(DataAcount dataAcount, SavingAccount savingAccount, int idBank, long withDrawMoney) {
        TransferMoney transferMoney = new TransferMoney();
        transferMoney.setType(Constant.TRANSFER_SAVING_ATM);
        transferMoney.setFromAccountNumber(dataAcount.getNumberAccount());
        transferMoney.setToAccountNumber(dataAcount.getNumberAccount());
        transferMoney.setIdBankFrom(idBank);
        transferMoney.setIdBankTo(idBank);
        transferMoney.setMoney(withDrawMoney);
        transferMoney.setNameTo(dataAcount.getName());
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
                mView.withDrawMoneyFail(e.getMessage());
                mView.hideProgressBar();
            }

            @Override
            public void onComplete() {
                mView.hideProgressBar();
            }
        };
    }
}

package com.hungtran.bankingassistant.ui.transactionHistory;

import com.hungtran.bankingassistant.model.transactionHistory.TransactionHistory;

import java.util.List;

public interface TransactionHistoryContract {

    interface View {
        void getTransactionHistorySuccess(List<TransactionHistory> transactionHistories);

        void getTransactionHistoryFail(String message);

        void hideProgressBar();
    }

    interface Presenter {
        void getTransactionHistory(int type, int idBankSend, int idBankReceive);
    }
}

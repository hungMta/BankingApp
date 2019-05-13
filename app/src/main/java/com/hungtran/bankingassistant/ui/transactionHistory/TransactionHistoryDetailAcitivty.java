package com.hungtran.bankingassistant.ui.transactionHistory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.transactionHistory.TransactionHistory;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionHistoryDetailAcitivty extends BaseActivity {

    @BindView(R.id.txtIdTransaction)
    TextView mTxtIdTransaction;

    @BindView(R.id.txtTransactionDate)
    TextView mTxtTransactionDate;

    @BindView(R.id.txtTransactionMoney)
    TextView mTxtTransactionMoney;

    @BindView(R.id.TransactionMessage)
    TextView mTxtMessage;

    TransactionHistory transactionHistory;

    @Override
    public int getLayoutId() {
        return R.layout.activity_transaction_history_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        transactionHistory = (TransactionHistory) getIntent().getSerializableExtra(Constant.TRANSFER_MONEY);

        mTxtIdTransaction.setText(transactionHistory.getId() + "");
        mTxtTransactionDate.setText(DataHelper.getTimeFormatFromInterval(transactionHistory.getTransactionDate()));
        mTxtTransactionMoney.setText(DataHelper.formatMoney(transactionHistory.getMoney()));
        mTxtMessage.setText(transactionHistory.getMessage());
    }
}

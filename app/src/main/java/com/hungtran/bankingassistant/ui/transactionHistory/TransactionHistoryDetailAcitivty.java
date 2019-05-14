package com.hungtran.bankingassistant.ui.transactionHistory;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

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

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    TransactionHistory transactionHistory;

    @Override
    public int getLayoutId() {
        return R.layout.activity_transaction_history_detail;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        transactionHistory = (TransactionHistory) getIntent().getSerializableExtra(Constant.TRANSFER_MONEY);

        mTxtIdTransaction.setText(transactionHistory.getId() + "");
        mTxtTransactionDate.setText(DataHelper.getTimeFormatFromInterval(transactionHistory.getTransactionDate()));
        mTxtTransactionMoney.setText(DataHelper.formatMoney(transactionHistory.getMoney()));
        mTxtMessage.setText(transactionHistory.getMessage());


        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

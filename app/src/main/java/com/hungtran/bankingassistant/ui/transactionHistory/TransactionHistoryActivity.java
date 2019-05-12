package com.hungtran.bankingassistant.ui.transactionHistory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;
import com.hungtran.bankingassistant.model.transactionHistory.TransactionHistory;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.base.BaseActivity;
import com.hungtran.bankingassistant.util.base.SharePreference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionHistoryActivity extends BaseActivity implements TransactionHistoryContract.View, View.OnClickListener {


    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.txtAccountNumber)
    TextView mTxtAccountNumber;

    @BindView(R.id.txtMoneyAvailable)
    TextView mTxtMoney;

    @BindView(R.id.reyclerView)
    RecyclerView recyclerView;

//    @BindView(R.id.tabAll2)
//    TabItem mTabAll;
//
//    @BindView(R.id.tabReceivingMoney)
//    TabItem mTabReceivingMoney;
//
//    @BindView(R.id.tabSendingMoney)
//    TabItem mTabSendingMoney;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;


    private DataAcount dataAcount;
    private int idBank;
    private TransactionHistoryPresenter transactionHistoryPresenter;
    private TransactionHistoryAdapter transactionHistoryAdapter;
    private List<TransactionHistory> transactionHistories = new ArrayList<>();
    private List<TransactionHistory> currentTransactionList = new ArrayList<>();
    private int idUser = 0;


    @Override
    public int getLayoutId() {
        return R.layout.activity_history_transaction;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dataAcount = (DataAcount) getIntent().getSerializableExtra(Constant.DATA_ACCOUNT);
        idBank = getIntent().getIntExtra(Constant.ID_BANK, 0);
        transactionHistoryPresenter = new TransactionHistoryPresenter(this);
        transactionHistoryPresenter.getTransactionHistory(1, idBank, idBank);
        showDialogProgress();
        setData();
        setupRecyclerView();

//        mTabAll.setOnClickListener(this);
//        mTabSendingMoney.setOnClickListener(this);
//        mTabReceivingMoney.setOnClickListener(this);

        idUser = SharePreference.getIntVal(Constant.ID_USER_KEY);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateRecycler(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    void setData() {
        mTxtAccountNumber.setText(dataAcount.getNumberAccount());
        long money = Long.parseLong(dataAcount.getAtmMoney());
        mTxtMoney.setText(DataHelper.formatMoney(money) + " VND");
    }

    @Override
    public void getTransactionHistorySuccess(List<TransactionHistory> transactionHistories) {
        Log.d(TAG, "getTransactionHistorySuccess: " + transactionHistories);
        this.transactionHistories = transactionHistories;
        transactionHistoryAdapter.updateApdater(transactionHistories);
    }

    @Override
    public void getTransactionHistoryFail(String message) {

    }

    @Override
    public void hideProgressBar() {
        hideDialogProgress();
    }

    private void setupRecyclerView(){
        transactionHistoryAdapter = new TransactionHistoryAdapter(this, transactionHistories, dataAcount.getNumberAccount());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(transactionHistoryAdapter);
    }

    private void updateRecycler(int tabIndex) {
        switch (tabIndex) {
            case 0:
                transactionHistoryAdapter.updateApdater(transactionHistories);
                break;
            case 1:
                currentTransactionList.clear();
                for (TransactionHistory transactionHistory : transactionHistories) {
                    if (transactionHistory.getIdBankAccountReceive().equals(dataAcount.getNumberAccount())) {
                        currentTransactionList.add(transactionHistory);
                    }
                }
                transactionHistoryAdapter.updateApdater(currentTransactionList);
                break;
            case 2:
                currentTransactionList.clear();
                for (TransactionHistory transactionHistory : transactionHistories) {
                    if (transactionHistory.getIdBankAccountSend().equals(dataAcount.getNumberAccount())) {
                        currentTransactionList.add(transactionHistory);
                    }
                }
                transactionHistoryAdapter.updateApdater(currentTransactionList);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tabAll2:
                currentTransactionList = transactionHistories;
                transactionHistoryAdapter.updateApdater(currentTransactionList);
                break;
            case R.id.tabReceivingMoney:
                currentTransactionList.clear();
                for (TransactionHistory transactionHistory : transactionHistories) {
                    if (transactionHistory.getIdBankAccountReceive().equals(dataAcount.getNumberAccount())) {
                        currentTransactionList.add(transactionHistory);
                    }
                }
                transactionHistoryAdapter.updateApdater(currentTransactionList);
                break;
            case R.id.tabSendingMoney:
                currentTransactionList.clear();
                for (TransactionHistory transactionHistory : transactionHistories) {
                    if (transactionHistory.getIdBankAccountSend().equals(dataAcount.getNumberAccount())) {
                        currentTransactionList.add(transactionHistory);
                    }
                }
                transactionHistoryAdapter.updateApdater(currentTransactionList);
                break;
        }
    }
}

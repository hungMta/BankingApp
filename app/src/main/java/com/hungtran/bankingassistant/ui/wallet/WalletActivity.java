package com.hungtran.bankingassistant.ui.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.LinkingBankRecyclerViewAdapter;
import com.hungtran.bankingassistant.model.bank.Bank;
import com.hungtran.bankingassistant.ui.AvaibleBankLinkingAcitivity.AvaiableBankLinkingActivity;
import com.hungtran.bankingassistant.ui.myAccountCardList.MyAccountCardListActivity;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletActivity extends BaseActivity implements LinkingBankRecyclerViewAdapter.LinkingBankListener, View.OnClickListener, WalletContract.View, AvaiableBankLinkingActivity.AvaiableBankLinkingActivityListener {

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.layoutAddBank)
    LinearLayout mLayoutAddBank;

    @BindView(R.id.reyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private WalletPresenter mPresenter;

    private LinkingBankRecyclerViewAdapter mAdapter;

    private static OnWalletActivityListener mOnWalletActivityListener;
    private List<Bank> myBanks;

    @Override
    public int getLayoutId() {
        return R.layout.activty_wallet;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter = new WalletPresenter(this, this);
        mPresenter.loadLinkingBankList();
        mProgressBar.setVisibility(View.VISIBLE);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mLayoutAddBank.setOnClickListener(this);
        setupRecyclerView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mOnWalletActivityListener != null) {
            mOnWalletActivityListener.onWalletActivtyDestroy();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutAddBank:
//                Intent intent = new Intent(this, LinkingBankMockupActivity.class);
                Intent intent = new Intent(this, AvaiableBankLinkingActivity.class);
                intent.putExtra(Constant.MY_BANK, (Serializable) myBanks);
                AvaiableBankLinkingActivity.setAvaiableBankLinkingActivityListener(this);
                startActivity(intent);
                break;
        }
    }


    private void setupRecyclerView() {
        mAdapter = new LinkingBankRecyclerViewAdapter(this, new ArrayList<>());
        mAdapter.setLinkingBankListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void loadLinkingBankListSuccess(List<Bank> list) {
        mAdapter.update(list);
        myBanks = list;
    }

    @Override
    public void loadLinkingBankListFail() {

    }

    @Override
    public void needToRefresBankList() {
        mPresenter.loadLinkingBankList();
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLinkingBankItemClick(Bank bank) {
        Intent intent = new Intent(this, MyAccountCardListActivity.class);
        startActivity(intent);
    }

    public interface OnWalletActivityListener {
        void onWalletActivtyDestroy();
    }

    public static void setOnWalletActivityListener(OnWalletActivityListener listener) {
        mOnWalletActivityListener = listener;
    }
}

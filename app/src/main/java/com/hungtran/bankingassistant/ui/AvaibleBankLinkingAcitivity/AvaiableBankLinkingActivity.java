package com.hungtran.bankingassistant.ui.AvaibleBankLinkingAcitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.ChooseLinkingBankReycyclerViewAdapter;
import com.hungtran.bankingassistant.dialog.LinkingBankSuccessDialog;
import com.hungtran.bankingassistant.model.bank.Bank;
import com.hungtran.bankingassistant.ui.linkingBankMockup.LinkingBankMockupActivity;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AvaiableBankLinkingActivity extends BaseActivity implements LinkingBankSuccessDialog.LinkingBankSuccessDialogListener, LinkingBankMockupActivity.LinkingBankMockupListener, AvaiableBankLinkingContract.View, ChooseLinkingBankReycyclerViewAdapter.ChooseLinkingListener {

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.reyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    ChooseLinkingBankReycyclerViewAdapter mApdater;
    AvaiableBankLinkingPresenter mPresenter;
    static AvaiableBankLinkingActivityListener mAvaiableBankLinkingActivityListener;
    private List<Bank> mMyBanks;
    private boolean isNeedShowDialog = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_avaiable_bank_linking;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter = new AvaiableBankLinkingPresenter(this, this);
        mPresenter.getAvaibleBankLinking();
        mProgressBar.setVisibility(View.VISIBLE);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setupRecylerView();

        mMyBanks = (List<Bank>) getIntent().getSerializableExtra(Constant.MY_BANK);
        LinkingBankMockupActivity.setLinkingBankMockupListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNeedShowDialog) {
            LinkingBankSuccessDialog dialog = new LinkingBankSuccessDialog();
            dialog.setLinkingBankSuccessDialogListener(this);
            dialog.show(getSupportFragmentManager(), "dialog");
            isNeedShowDialog = false;
        }
    }

    private void setupRecylerView() {
        mApdater = new ChooseLinkingBankReycyclerViewAdapter(this, new ArrayList<>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mApdater);
        mApdater.setChooseLinkingListener(this);
    }

    @Override
    public void getAvaibleBankLinkingSuccess(List<Bank> banks) {
        mApdater.update(banks);
    }

    @Override
    public void getAvaibleBankLinkingFail() {

    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClicked(Bank bank) {
        boolean isLinked = false;
        for (Bank b : mMyBanks) {
            if (b.getId() == bank.getId_bank()) {
                Toast.makeText(this, "Ngân hàng đã liên kết!", Toast.LENGTH_SHORT).show();
                isLinked = true;
            }
        }
        if (!isLinked) {
            Intent intent = new Intent(this, LinkingBankMockupActivity.class);
            intent.putExtra(Constant.BANK, bank);
            startActivity(intent);
        }
    }

    @Override
    public void onLinkingBankSuccess() {
        isNeedShowDialog = true;
    }

    @Override
    public void onLinkingBankSuccessDialogOkClicked() {
        if (mAvaiableBankLinkingActivityListener != null) {
            mAvaiableBankLinkingActivityListener.needToRefresBankList();
        }
        finish();
    }

    public interface AvaiableBankLinkingActivityListener {
        void needToRefresBankList();
    }

    public static void setAvaiableBankLinkingActivityListener(AvaiableBankLinkingActivityListener listener) {
        mAvaiableBankLinkingActivityListener = listener;
    }
}

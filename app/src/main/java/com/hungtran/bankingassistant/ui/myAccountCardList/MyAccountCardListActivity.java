package com.hungtran.bankingassistant.ui.myAccountCardList;

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
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;
import com.hungtran.bankingassistant.model.respone.DataAccount.SavingAccount;
import com.hungtran.bankingassistant.ui.createSavingAccount.CreateSavingAccountActivity;
import com.hungtran.bankingassistant.ui.detailAccount.DetailATMAccountActivity;
import com.hungtran.bankingassistant.ui.detailAccount.DetailSavingAccountActivity;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAccountCardListActivity extends BaseActivity implements MyAccountCardListContract.View, MyAccountCardListRecyclerViewAdapter.MyAccountCardListListener {

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.reyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private int mIdBank;
    private MyAccountCardListPresenter mPresenter;
    private MyAccountCardListRecyclerViewAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_list_card;
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
        mPresenter = new MyAccountCardListPresenter(this, this);
        mIdBank = getIntent().getIntExtra(Constant.ID_BANK, 0);
        setupRecycler();
    }

    private void setupRecycler() {
        mAdapter = new MyAccountCardListRecyclerViewAdapter(this, null, mIdBank);
        mAdapter.setMyAccountCardListListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
//        setUpBackground();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataAccount();
    }

    private void getDataAccount(){
        mProgressBar.setVisibility(View.VISIBLE);
        mPresenter.getDataAccount(mIdBank);
    }

    @Override
    public void getDataAccountSuccess(DataAcount dataAcount) {
        mAdapter.update(dataAcount);
    }

    @Override
    public void getDataAccountFail() {
        Toast.makeText(this, "erro", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onMyAccountCardListItemClicked(DataAcount acount, int position) {
        if (position == 0) {
            Intent intent = new Intent(this, DetailATMAccountActivity.class);
            intent.putExtra(Constant.DATA_ACCOUNT, acount);
            intent.putExtra(Constant.ID_BANK, mIdBank);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, DetailSavingAccountActivity.class);
            intent.putExtra(Constant.DATA_ACCOUNT, acount);
            intent.putExtra(Constant.ID_BANK, mIdBank);
            startActivity(intent);
        }
    }

    @Override
    public void onMyAccountCardListItemSavingClicked(DataAcount acount, int position, SavingAccount savingAccount) {
        Intent intent = new Intent(this, DetailSavingAccountActivity.class);
        intent.putExtra(Constant.DATA_ACCOUNT, acount);
        intent.putExtra(Constant.SAVING_ACCOUNT, savingAccount);
        intent.putExtra(Constant.ID_BANK, mIdBank);
        startActivity(intent);
    }

    private void setUpBackground(){
        switch (mIdBank) {
            case Constant.ID_VCB:
                mRecyclerView.setBackgroundColor(getResources().getColor(R.color.colorVCB));
                break;
            case Constant.ID_BIDV:
                mRecyclerView.setBackgroundColor(getResources().getColor(R.color.colorBIDV));
                break;
            case Constant.ID_AGRI:
                mRecyclerView.setBackgroundColor(getResources().getColor(R.color.colorViettin));
                break;
            case Constant.ID_VIETTIN:
                mRecyclerView.setBackgroundColor(getResources().getColor(R.color.colorViettin));
                break;
        }
    }
}

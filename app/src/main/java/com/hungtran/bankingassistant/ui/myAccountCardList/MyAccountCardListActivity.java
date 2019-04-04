package com.hungtran.bankingassistant.ui.myAccountCardList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAccountCardListActivity extends BaseActivity implements MyAccountCardListContract.View{

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.reyclerView)
    RecyclerView mRecyclerView;

    private int mIdBank;
    private MyAccountCardListPresenter mPresenter;

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
        mPresenter.getDataAccount(mIdBank);
    }


    @Override
    public void getDataAccountSuccess() {

    }

    @Override
    public void getDataAccountFail() {

    }

    @Override
    public void hideProgressBar() {

    }
}

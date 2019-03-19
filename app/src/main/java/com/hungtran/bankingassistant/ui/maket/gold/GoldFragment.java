package com.hungtran.bankingassistant.ui.maket.gold;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.GoldAreaRecyclerViewAdapter;
import com.hungtran.bankingassistant.model.gold.GoldAreaResponse;
import com.hungtran.bankingassistant.util.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoldFragment extends BaseFragment implements GoldContract.View {

    @BindView(R.id.reyclerView)
    RecyclerView mRecyclerView;

    private static GoldFragment instance;
    private GoldAreaRecyclerViewAdapter mGoldAreaRecyclerViewAdapter;
    private GoldPresenter mPresenter;
    public static GoldFragment getInstance() {
        if (instance == null) {
            instance = new GoldFragment();
        }
        return new GoldFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupRecylerView();
        mPresenter = new GoldPresenter(this);
        mPresenter.getGoldPrice();
    }

    @Override
    public void setPresenter(GoldContract.Presenter presenter) {

    }

    private void setupRecylerView(){
        mGoldAreaRecyclerViewAdapter = new GoldAreaRecyclerViewAdapter(getContext(),null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mGoldAreaRecyclerViewAdapter);
    }

    @Override
    public void onGetGoldPriceSuccess(GoldAreaResponse goldAreaResponse) {
        mGoldAreaRecyclerViewAdapter.upateAdapter(goldAreaResponse.getGoldAreas());
    }
}

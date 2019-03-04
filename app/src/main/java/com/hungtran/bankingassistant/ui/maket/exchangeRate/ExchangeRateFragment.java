package com.hungtran.bankingassistant.ui.maket.exchangeRate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.ExchangeRateRecylerViewAdapter;
import com.hungtran.bankingassistant.model.Currency;
import com.hungtran.bankingassistant.model.ExchangeRate;
import com.hungtran.bankingassistant.util.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExchangeRateFragment extends BaseFragment implements ExchangeRateContract.View{

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.reyclerView)
    RecyclerView mRecyclerView;

    private ExchangeRateRecylerViewAdapter mViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static ExchangeRateFragment instance;
    private ExchangeRatePresenter mPresenter;

    public static ExchangeRateFragment getInstance() {
        if (instance == null) {
            instance = new ExchangeRateFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_exchange_rate;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mPresenter = new ExchangeRatePresenter(this);
        mPresenter.getExchangeRates();
        setupRecyclerView();
    }

    @Override
    public void showExchangeRates(List<ExchangeRate> list) {
        mViewAdapter.updateAdapter(list.get(0).getCurrencies());
    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void setPresenter(ExchangeRateContract.Presenter presenter) {

    }

    private void setupRecyclerView() {
        mViewAdapter = new ExchangeRateRecylerViewAdapter(getContext(), new ArrayList<Currency>());
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mViewAdapter);

    }
}

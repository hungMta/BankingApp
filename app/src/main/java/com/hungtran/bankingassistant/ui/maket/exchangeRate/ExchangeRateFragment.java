package com.hungtran.bankingassistant.ui.maket.exchangeRate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.BankPopupRecylerViewAdapter;
import com.hungtran.bankingassistant.adapters.ExchangeRateRecylerViewAdapter;
import com.hungtran.bankingassistant.model.Bank;
import com.hungtran.bankingassistant.model.Currency;
import com.hungtran.bankingassistant.model.ExchangeRate;
import com.hungtran.bankingassistant.util.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExchangeRateFragment extends BaseFragment implements ExchangeRateContract.View, View.OnClickListener, BankPopupRecylerViewAdapter.OnItemClick {

    private static final String TAG = "hungtd";
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.reyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.layoutChooseBank)
    LinearLayout mLayoutChooseBank;

    @BindView(R.id.txtCurrentBankName)
    TextView mTxtCurrentBankName;

    @BindView(R.id.txtTimeUpdate)
    TextView mTxtTimeUpdate;

    private ExchangeRate currentExchangeRate;
    private ExchangeRateRecylerViewAdapter mViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private PopupWindow mBanksPopup;

    private static ExchangeRateFragment instance;
    private ExchangeRatePresenter mPresenter;
    private List<ExchangeRate> exchangeRates;

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
        mLayoutChooseBank.setOnClickListener(this);
    }

    @Override
    public void showExchangeRates(List<ExchangeRate> list) {
        mViewAdapter.updateAdapter(list.get(0).getCurrencies());
        this.exchangeRates = list;
        this.currentExchangeRate = list.get(0);
        mTxtCurrentBankName.setText(currentExchangeRate.getName());
        try {
            mTxtTimeUpdate.setText(getString(R.string.time_update) + " " +list.get(0).getTimeCrawling());
        }catch (NullPointerException e) {

        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layoutChooseBank:
                PopupWindow popUp = showBankListPopup();
                popUp.showAsDropDown(v, 0, -10);
                break;
            default:
                break;
        }
    }

    private PopupWindow showBankListPopup() {
        mBanksPopup = new PopupWindow(getContext());
        BankPopupRecylerViewAdapter adapter = new BankPopupRecylerViewAdapter(this.exchangeRates);
        adapter.setOnItemClick(this);
        RecyclerView recyclerView = new RecyclerView(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        mBanksPopup.setFocusable(true);
        mBanksPopup.setWidth(600);
        mBanksPopup.setHeight(800);
        mBanksPopup.setContentView(recyclerView);
        mBanksPopup.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_white_corner_five_radius));
        return mBanksPopup;
    }

    @Override
    public void itemBankPopupClick(ExchangeRate exchangeRate) {
        this.currentExchangeRate = exchangeRate;
        mTxtCurrentBankName.setText(currentExchangeRate.getName());
        mViewAdapter.updateAdapter(exchangeRate.getCurrencies());
        Log.d(TAG, "itemBankPopupClick: ");
        mBanksPopup.dismiss();
    }
}

package com.hungtran.bankingassistant.ui.maket.interestRate.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.BankPopupRecylerViewAdapter;
import com.hungtran.bankingassistant.adapters.InterestRateRecyclerViewAdapter;
import com.hungtran.bankingassistant.adapters.TimeDepositRecyclerViewAdapter;
import com.hungtran.bankingassistant.model.Bank;
import com.hungtran.bankingassistant.model.ExchangeRate;
import com.hungtran.bankingassistant.model.InterestRateResponse;
import com.hungtran.bankingassistant.util.base.BaseFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalInterestRateFragment extends BaseFragment implements PersonalInterestRateContract.View, View.OnClickListener, TimeDepositRecyclerViewAdapter.OnItemClick{
    private static final String TAG = "HUNGTD";

    private static PersonalInterestRateFragment instance;

    @BindView(R.id.reyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.layoutFirstColumn)
    LinearLayout mLayoutFirstColumn;

    @BindView(R.id.layoutSecondColumn)
    LinearLayout mLayoutSecondColumn;

    @BindView(R.id.layoutThirdColumn)
    LinearLayout mLayoutThirdColumn;

    @BindView(R.id.layoutFourthColumn)
    LinearLayout mLayoutFourthColumn;

    @BindView(R.id.layoutFifthColumn)
    LinearLayout mLayoutFifthColumn;

    private InterestRateRecyclerViewAdapter mAdapter;
    private PopupWindow mTimeDepositPopup;
    private PersonalInterestRatePresenter mPresenter;

    public static PersonalInterestRateFragment getInstance(){
        if (instance == null) {
            instance = new PersonalInterestRateFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal_interest_rate;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        mPresenter = new PersonalInterestRatePresenter(this);
        mPresenter.getPersonalInterestRate();
        mLayoutFirstColumn.setOnClickListener(this);
        mLayoutSecondColumn.setOnClickListener(this);
        mLayoutThirdColumn.setOnClickListener(this);
        mLayoutFourthColumn.setOnClickListener(this);
        mLayoutFifthColumn.setOnClickListener(this);
    }


    private void setupRecyclerView() {
        mAdapter = new InterestRateRecyclerViewAdapter(null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        mTimeDepositPopup  = getBankListPopup();
        switch (v.getId()) {
            case R.id.layoutFirstColumn:
                break;
            case R.id.layoutSecondColumn:
                mTimeDepositPopup.showAsDropDown(v, 0 , -10);
                break;
            case R.id.layoutThirdColumn:
                mTimeDepositPopup.showAsDropDown(v, 0 , -10);
                break;
            case R.id.layoutFourthColumn:
                mTimeDepositPopup.showAsDropDown(v, 0 , -10);
                break;
            case R.id.layoutFifthColumn:
                mTimeDepositPopup.showAsDropDown(v, 0 , -10);
                break;
        }
    }

    private PopupWindow getBankListPopup() {
        PopupWindow popupWindow = new PopupWindow(getContext());
        String[] array = getResources().getStringArray(R.array.deposit_period);
        List<String> list = Arrays.asList(array);
        TimeDepositRecyclerViewAdapter adapter = new TimeDepositRecyclerViewAdapter(list);
        adapter.setOnItemClick(this);
        RecyclerView recyclerView = new RecyclerView(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        popupWindow.setFocusable(true);
        popupWindow.setWidth(500);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(recyclerView);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_white_corner_five_radius));
        return popupWindow;
    }

    @Override
    public void timeDepositItemClicked() {
        Log.d(TAG, "timeDepositItemClicked: ");
        mTimeDepositPopup.dismiss();
    }

    @Override
    public void setPresenter(PersonalInterestRateContract.Presenter presenter) {

    }

    @Override
    public void onGetPersonalInterestRateSuccess(InterestRateResponse interestRateResponse) {
        mAdapter.updateApdater(interestRateResponse.getInterestRateByBankList());
    }
}

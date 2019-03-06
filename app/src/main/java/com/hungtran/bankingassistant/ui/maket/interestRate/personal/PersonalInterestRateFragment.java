package com.hungtran.bankingassistant.ui.maket.interestRate.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.BankPopupRecylerViewAdapter;
import com.hungtran.bankingassistant.adapters.InterestRateRecyclerViewAdapter;
import com.hungtran.bankingassistant.model.Bank;
import com.hungtran.bankingassistant.util.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalInterestRateFragment extends BaseFragment implements View.OnClickListener {
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
    private PopupWindow mPeriod;

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

        mLayoutFirstColumn.setOnClickListener(this);
        mLayoutSecondColumn.setOnClickListener(this);
        mLayoutThirdColumn.setOnClickListener(this);
        mLayoutFourthColumn.setOnClickListener(this);
        mLayoutFifthColumn.setOnClickListener(this);
    }


    private void setupRecyclerView() {
        mAdapter = new InterestRateRecyclerViewAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutFirstColumn:
                break;
            case R.id.layoutSecondColumn:
                break;
            case R.id.layoutThirdColumn:
                break;
            case R.id.layoutFourthColumn:
                break;
            case R.id.layoutFifthColumn:
                break;
        }
    }


    private PopupWindow showBankListPopup() {
        mPeriod = new PopupWindow(getContext());
        BankPopupRecylerViewAdapter adapter = new BankPopupRecylerViewAdapter(new ArrayList<Bank>());
//        adapter.setOnItemClick(this);
        RecyclerView recyclerView = new RecyclerView(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        mPeriod.setFocusable(true);
        mPeriod.setWidth(600);
        mPeriod.setHeight(800);
        mPeriod.setContentView(recyclerView);
        mPeriod.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_white_corner_five_radius));
        return mPeriod;
    }
}

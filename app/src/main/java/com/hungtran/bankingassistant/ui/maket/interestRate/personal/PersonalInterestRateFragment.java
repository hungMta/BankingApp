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
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.InterestRateRecyclerViewAdapter;
import com.hungtran.bankingassistant.adapters.TimeDepositRecyclerViewAdapter;
import com.hungtran.bankingassistant.model.InterestRateByBank;
import com.hungtran.bankingassistant.model.InterestRateResponse;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.BaseFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalInterestRateFragment extends BaseFragment implements PersonalInterestRateContract.View, View.OnClickListener, TimeDepositRecyclerViewAdapter.OnItemClick {
    private static final String TAG = "HUNGTD";

    private static PersonalInterestRateFragment instance;

    @BindView(R.id.reyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.layoutFirstColumn)
    LinearLayout mLayoutFirstColumn;

    @BindView(R.id.layoutSecondColumn)
    LinearLayout mLayoutSecondColumn;

    @BindView(R.id.txtSecondColumn)
    TextView mTxtSecondColumn;

    @BindView(R.id.layoutThirdColumn)
    LinearLayout mLayoutThirdColumn;

    @BindView(R.id.txtThirdColumn)
    TextView mTxtThirdColumn;

    @BindView(R.id.layoutFourthColumn)
    LinearLayout mLayoutFourthColumn;

    @BindView(R.id.txtFourthColumn)
    TextView mTxtFourthColumn;

    @BindView(R.id.layoutFifthColumn)
    LinearLayout mLayoutFifthColumn;

    @BindView(R.id.txtFifthColumn)
    TextView mTxtFifthColumn;

    @BindView(R.id.txtTimeUpdate)
    TextView mTxtTimeUpdate;

    private InterestRateRecyclerViewAdapter mAdapter;
    private PopupWindow mTimeDepositPopup;
    private PersonalInterestRatePresenter mPresenter;
    private int firstRateType = Constant.TYPE_MONTH_3_RATE;
    private int secondRateType = Constant.TYPE_MONTH_6_RATE;
    private int thirdRateType = Constant.TYPE_MONTH_9_RATE;
    private int fourthRateType = Constant.TYPE_MONTH_12_RATE;
    private List<InterestRateByBank> interestRateByBankList;


    public static PersonalInterestRateFragment getInstance() {
        if (instance == null) {
            instance = new PersonalInterestRateFragment();
        }
        return  new PersonalInterestRateFragment();
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
        mTimeDepositPopup = getBankListPopup(v);
        switch (v.getId()) {
            case R.id.layoutFirstColumn:
                break;
            case R.id.layoutSecondColumn:
                mTimeDepositPopup.showAsDropDown(v, 0, -10);
                break;
            case R.id.layoutThirdColumn:
                mTimeDepositPopup.showAsDropDown(v, 0, -10);
                break;
            case R.id.layoutFourthColumn:
                mTimeDepositPopup.showAsDropDown(v, 0, -10);
                break;
            case R.id.layoutFifthColumn:
                mTimeDepositPopup.showAsDropDown(v, 0, -10);
                break;
        }
    }

    private PopupWindow getBankListPopup(View view) {
        PopupWindow popupWindow = new PopupWindow(getContext());
        String[] array = getResources().getStringArray(R.array.deposit_period);
        List<String> list = Arrays.asList(array);
        TimeDepositRecyclerViewAdapter adapter = new TimeDepositRecyclerViewAdapter(view, list);
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
    public void timeDepositItemClicked(View parrentView, String timeDeposit) {
        Log.d(TAG, "timeDepositItemClicked: ");
        mPresenter.getRateTypeFromString(getContext(), parrentView.getId(), timeDeposit);
        mTimeDepositPopup.dismiss();
    }

    @Override
    public void setPresenter(PersonalInterestRateContract.Presenter presenter) {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onGetPersonalInterestRateSuccess(InterestRateResponse interestRateResponse) {
        this.interestRateByBankList = interestRateResponse.getInterestRateByBankList();
        mAdapter.updateApdater(interestRateResponse.getInterestRateByBankList(), firstRateType, secondRateType, thirdRateType, fourthRateType);
        try {
            if (getActivity() == null && !isAdded()) return;
            mTxtTimeUpdate.setText(getString(R.string.time_update) + " " + interestRateResponse.getInterestRateByBankList().get(0).getTimeCrawling());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGetRateType(int id, int rateType) {
        switch (id) {
            case R.id.layoutFirstColumn:
                break;
            case R.id.layoutSecondColumn:
                firstRateType = rateType;
                mTxtSecondColumn.setText(mPresenter.getStringFromRateType(getContext(), rateType));
                break;
            case R.id.layoutThirdColumn:
                secondRateType = rateType;
                mTxtThirdColumn.setText(mPresenter.getStringFromRateType(getContext(), rateType));
                break;
            case R.id.layoutFourthColumn:
                thirdRateType = rateType;
                mTxtFourthColumn.setText(mPresenter.getStringFromRateType(getContext(), rateType));
                break;
            case R.id.layoutFifthColumn:
                fourthRateType = rateType;
                mTxtFifthColumn.setText(mPresenter.getStringFromRateType(getContext(), rateType));
                break;
        }
        mAdapter.updateApdater(interestRateByBankList, firstRateType, secondRateType, thirdRateType, fourthRateType);
    }
}

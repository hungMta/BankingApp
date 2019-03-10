package com.hungtran.bankingassistant.ui.calculator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.CalculateInterestMoneyRecylerViewAdapter;
import com.hungtran.bankingassistant.adapters.DefaultPopupWindowRecyerViewAdapter;
import com.hungtran.bankingassistant.model.BaseModel;
import com.hungtran.bankingassistant.model.CalculateInterestMoneyModel;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.PopupHelper;
import com.hungtran.bankingassistant.util.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hungtd on 3/7/19.
 */

public class CalculatorFragment extends BaseFragment implements CalculatorContract.View, View.OnClickListener {
    private static CalculatorFragment instance;

    @BindView(R.id.edtInitialSavingMoney)
    EditText mEdtInitialSavingMoney;

    @BindView(R.id.edtInterestRate)
    EditText mEdtInterestRate;

    @BindView(R.id.edtTerm)
    EditText mEdtTerm;

    @BindView(R.id.edtTimeSaving)
    EditText mEdtTimeSaving;

    @BindView(R.id.btnWithdrawType)
    Button mBtnWithdrawType;

    @BindView(R.id.btnRefresh)
    Button mBtnRefresh;

    @BindView(R.id.layoutTimeSavingType)
    LinearLayout mLayoutTimeSavingType;

    @BindView(R.id.txtTimeSavingType)
    TextView mTxtTimeSavingType;

    @BindView(R.id.txtInitialMoney)
    TextView mTxtInitialMoneyFormat;

    @BindView(R.id.txtTotalReceivingInterestMoney)
    TextView mTxtTotalReceivingInterestMoney;

    @BindView(R.id.txtTotalReceivingMoney)
    TextView mTxtTotalReceivingMoney;

    @BindView(R.id.reyclerView)
    RecyclerView mRecylerCalculateMoney;

    private PopupWindow mPopupTimeSavingType;
    private PopupWindow mPopupWithdrawType;
    private List<BaseModel> mListPopupTimeSavingModel;
    private List<BaseModel> mListPopupWithdrawType;
    private int mTimeSavingType = Constant.TYPE_SAVING_MONTH;
    private int mWithdrawType = Constant.TYPE_SAVING_WITHDRAW;
    private long mInitialMoney = 0;
    private double mInterestRate = 0;
    private int mTerm = 0;
    private int mTimeSavingMoney = 0;
    private CalculateInterestMoneyRecylerViewAdapter mCalculateInterestMoneyRecylerViewAdapter;
    private CalculatorPresenter mPresenter;
    public static CalculatorFragment getInstance() {
        if (instance == null) {
            instance = new CalculatorFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_caluclator;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mPresenter = new CalculatorPresenter(this);
        setupPopupModel();
        setupEditTextView();
        setRecyclerViewCalculateMoney();
        mBtnRefresh.setOnClickListener(this);
        mBtnWithdrawType.setOnClickListener(this);
        mLayoutTimeSavingType.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnWithdrawType:
                DefaultPopupWindowRecyerViewAdapter adapterWithdraw = new DefaultPopupWindowRecyerViewAdapter(mListPopupWithdrawType);
                mPopupWithdrawType = PopupHelper.getDefautPopup(getContext(), adapterWithdraw, v.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT);
                mPopupWithdrawType.showAsDropDown(v, 0, -50);
                adapterWithdraw.setOnItemClick(new DefaultPopupWindowRecyerViewAdapter.OnItemClick() {
                    @Override
                    public void onItemDefaultClicked(BaseModel baseModel) {
                        mWithdrawType = baseModel.getId();
                        mBtnWithdrawType.setText(baseModel.getName());
                        mPopupWithdrawType.dismiss();
                        calculateMoney();
                    }
                });
                break;
            case R.id.layoutTimeSavingType:
                DefaultPopupWindowRecyerViewAdapter adapterTimeSaving = new DefaultPopupWindowRecyerViewAdapter(mListPopupTimeSavingModel);
                mPopupTimeSavingType = PopupHelper.getDefautPopup(getContext(), adapterTimeSaving, 500, LinearLayout.LayoutParams.WRAP_CONTENT);
                mPopupTimeSavingType.showAsDropDown(v, 0, -50);
                adapterTimeSaving.setOnItemClick(new DefaultPopupWindowRecyerViewAdapter.OnItemClick() {
                    @Override
                    public void onItemDefaultClicked(BaseModel baseModel) {
                        mTimeSavingType = baseModel.getId();
                        mTxtTimeSavingType.setText(baseModel.getName());
                        mPopupTimeSavingType.dismiss();
                        calculateMoney();
                    }
                });
                break;
            case R.id.btnRefresh:
                mInitialMoney = 0;
                mInterestRate = 0;
                mTerm = 0;
                mTimeSavingMoney = 0;
                mTxtInitialMoneyFormat.setText("0đ");
                mTxtTotalReceivingInterestMoney.setText("0đ");
                mTxtTotalReceivingMoney.setText("0đ");
                mEdtInitialSavingMoney.setText(null);
                mEdtInterestRate.setText(null);
                mEdtTerm.setText(null);
                mEdtTimeSaving.setText(null);
                mCalculateInterestMoneyRecylerViewAdapter.updateAdapter(null);
                break;
            default:
                break;
        }
    }

    private void setupPopupModel() {
        mListPopupTimeSavingModel = new ArrayList<>();
        mListPopupTimeSavingModel.add(new BaseModel(Constant.TYPE_SAVING_MONTH, getResources().getString(R.string.month)));
        mListPopupTimeSavingModel.add(new BaseModel(Constant.TYPE_SAVING_YEAR, getResources().getString(R.string.year)));

        mListPopupWithdrawType = new ArrayList<>();
        mListPopupWithdrawType.add(new BaseModel(Constant.TYPE_SAVING_WITHDRAW, getResources().getString(R.string.type_with_draw)));
        mListPopupWithdrawType.add(new BaseModel(Constant.TYPE_SAVING_WITHDRAW_END, getResources().getString(R.string.type_with_draw_end)));
    }

    private void setupEditTextView() {
        mEdtInitialSavingMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    mInitialMoney = Long.parseLong(s.toString());
                    mTxtInitialMoneyFormat.setText(DataHelper.formatMoney(mInitialMoney ) + "đ");
                    calculateMoney();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdtInterestRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    mInterestRate = Double.parseDouble(s.toString());
                    calculateMoney();
                }catch (NumberFormatException e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdtTerm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    mTerm = Integer.parseInt(s.toString());
                    calculateMoney();
                } catch (NumberFormatException e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdtTimeSaving.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    mTimeSavingMoney = Integer.parseInt(s.toString());
                    calculateMoney();
                } catch (NumberFormatException e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void calculateMoney(){
        mPresenter.calculateMoney(mInitialMoney, mInterestRate, mTerm,
                mTimeSavingMoney, mTimeSavingType, mWithdrawType);
    }

    private void setRecyclerViewCalculateMoney() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecylerCalculateMoney.setLayoutManager(linearLayoutManager);
        mCalculateInterestMoneyRecylerViewAdapter = new CalculateInterestMoneyRecylerViewAdapter(null);
        mRecylerCalculateMoney.setAdapter(mCalculateInterestMoneyRecylerViewAdapter);
    }

    @Override
    public void setPresenter(CalculatorContract.Presenter presenter) {

    }

    @Override
    public void onCalculateResult(List<Long> interestList, List<CalculateInterestMoneyModel> calculateInterestMoneyModelList, long totalInterest, long totalMoney) {
        mTxtTotalReceivingInterestMoney.setText(DataHelper.formatMoney(totalInterest));
        mTxtTotalReceivingMoney.setText(DataHelper.formatMoney(totalMoney));
        mCalculateInterestMoneyRecylerViewAdapter.updateAdapter(calculateInterestMoneyModelList);
    }
}

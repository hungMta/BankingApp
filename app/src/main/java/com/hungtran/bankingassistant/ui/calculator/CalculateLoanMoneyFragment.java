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
import com.hungtran.bankingassistant.model.base.BaseModel;
import com.hungtran.bankingassistant.model.interestRate.CalculateInterestMoneyModel;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.PopupHelper;
import com.hungtran.bankingassistant.util.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalculateLoanMoneyFragment extends BaseFragment implements CalculatorContract.View, View.OnClickListener {

    @BindView(R.id.txtInitialMoney)
    TextView mTxtInitialMoney;

    @BindView(R.id.edtInitialSavingMoney)
    EditText mEdtIntitialMoney;

    @BindView(R.id.edtInterestRate)
    EditText mEdtInterestRate;

    @BindView(R.id.edtTerm)
    EditText mEdtTerm;

    @BindView(R.id.btnWithdrawType)
    Button mBtnLoan;

    @BindView(R.id.btnRefresh)
    Button mBtnRefresh;

    @BindView(R.id.layoutInterestPaymentPerMonth)
    LinearLayout mLayoutInterestPaymentPerMonth;

    @BindView(R.id.txtInterestPaymentPerMonth)
    TextView mTxtInterestPaymentPerMonth;

    @BindView(R.id.layoutPrincipalPaymentPerMonth)
    LinearLayout mLayoutPrincipalPaymentPerMonth;

    @BindView(R.id.txtPrincipalPayment)
    TextView mTxtPrincipalPaymentPerMonth;

    @BindView(R.id.layoutTotalPaymentPerMonth)
    LinearLayout mLayoutTotalPaymentPerMonth;

    @BindView(R.id.txtTotalPaymentPerMonth)
    TextView mTxtTotalPaymentPerMonth;

    @BindView(R.id.txtTotalInterestPayment)
    TextView mTxtTotalInterestPayment;

    @BindView(R.id.txtTotalPayment)
    TextView mTxtTotalPayment;

    @BindView(R.id.layoutRecyclerView)
    LinearLayout mLayoutRecyclerViewLoan;

    @BindView(R.id.viewMarginBottom)
    View mViewMarginBottom;

    @BindView(R.id.reyclerView)
    RecyclerView mRecylerCalculateMoney;

    private List<BaseModel> mListPopupLoanType;
    private int mLoanType = Constant.TYPE_PRINCIPAL_BALANCE;
    private PopupWindow mPopupLoanType;
    private long mInitialMoney = 0;
    private double mInterestRate = 0;
    private int mTerm = 0;
    private CalculatorPresenter mPresenter;
    private CalculateInterestMoneyRecylerViewAdapter mCalculateInterestMoneyRecylerViewAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_calculate_loan_money;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mPresenter = new CalculatorPresenter(this);
        setupListPopupModel();
        mEdtIntitialMoney.setHint(getResources().getString(R.string.loan_money_cap_));
        mBtnLoan.setText(getResources().getString(R.string.principal_balance));
        mLayoutRecyclerViewLoan.setVisibility(View.GONE);
        mBtnLoan.setOnClickListener(this);
        mBtnRefresh.setOnClickListener(this);
        setupEditText();
        setRecyclerViewCalculateMoney();
    }

    private void setupListPopupModel() {
        mListPopupLoanType = new ArrayList<>();
        mListPopupLoanType.add(new BaseModel(Constant.TYPE_PRINCIPAL_BALANCE, getResources().getString(R.string.principal_balance)));
        mListPopupLoanType.add(new BaseModel(Constant.TYPE_PRINCIPAL_BALANCE_DECREASE, getResources().getString(R.string.principal_balance_decreased)));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnWithdrawType:
                DefaultPopupWindowRecyerViewAdapter adapterTimeSaving = new DefaultPopupWindowRecyerViewAdapter(mListPopupLoanType);
                mPopupLoanType = PopupHelper.getDefautPopup(getContext(), adapterTimeSaving, v.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT);
                mPopupLoanType.showAsDropDown(v, 0, -50);
                adapterTimeSaving.setOnItemClick(new DefaultPopupWindowRecyerViewAdapter.OnItemClick() {
                    @Override
                    public void onItemDefaultClicked(BaseModel baseModel) {
                        mLoanType = baseModel.getId();
                        mBtnLoan.setText(baseModel.getName());
                        mPopupLoanType.dismiss();
                        if (mLoanType == Constant.TYPE_PRINCIPAL_BALANCE) {
                            mLayoutInterestPaymentPerMonth.setVisibility(View.VISIBLE);
                            mLayoutPrincipalPaymentPerMonth.setVisibility(View.VISIBLE);
                            mLayoutTotalPaymentPerMonth.setVisibility(View.VISIBLE);
                            mLayoutRecyclerViewLoan.setVisibility(View.GONE);
                            mViewMarginBottom.setVisibility(View.VISIBLE);
                        } else {
                            mLayoutInterestPaymentPerMonth.setVisibility(View.GONE);
                            mLayoutPrincipalPaymentPerMonth.setVisibility(View.GONE);
                            mLayoutTotalPaymentPerMonth.setVisibility(View.GONE);
                            mLayoutRecyclerViewLoan.setVisibility(View.VISIBLE);
                            mViewMarginBottom.setVisibility(View.GONE);
                        }
                        calculateMoney();
                    }
                });
                break;
            case R.id.btnRefresh:
                mInitialMoney = 0;
                mInterestRate = 0;
                mTerm = 0;
                mTxtInitialMoney.setText("0đ");
                mTxtInterestPaymentPerMonth.setText("0đ");
                mTxtPrincipalPaymentPerMonth.setText("0đ");
                mTxtTotalPaymentPerMonth.setText("0đ");
                mTxtTotalInterestPayment.setText("0đ");
                mTxtTotalPayment.setText("0đ");
                mEdtInterestRate.setText(null);
                mEdtIntitialMoney.setText(null);
                mEdtTerm.setText(null);
                mCalculateInterestMoneyRecylerViewAdapter.updateAdapter(null);
                break;
        }
    }

    private void setRecyclerViewCalculateMoney() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecylerCalculateMoney.setLayoutManager(linearLayoutManager);
        mCalculateInterestMoneyRecylerViewAdapter = new CalculateInterestMoneyRecylerViewAdapter(Constant.TYPE_LOAN, null);
        mRecylerCalculateMoney.setAdapter(mCalculateInterestMoneyRecylerViewAdapter);
    }


    private void calculateMoney() {
        mPresenter.calculateLoanMoney(mInitialMoney, mInterestRate, mTerm, mLoanType);
    }

    private void setupEditText() {
        mEdtIntitialMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    mInitialMoney = Long.parseLong(s.toString());
                    mTxtInitialMoney.setText(DataHelper.formatMoney(mInitialMoney) + "đ");
                    calculateMoney();
                } catch (NumberFormatException e) {

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
                } catch (NumberFormatException e) {

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
    }


    @Override
    public void setPresenter(CalculatorContract.Presenter presenter) {

    }

    @Override
    public void onCalculateResult(List<Long> interestList, List<CalculateInterestMoneyModel> calculateInterestMoneyModelList, long totalInterest, long totalMoney) {

    }

    @Override
    public void onCalculateLoanResult(long interestPaymentPerMonth,
                                      long principalPaymentPerMonth,
                                      long totalPaymentPerMonth, long totalInterestPayment,
                                      long totalPayment, List<CalculateInterestMoneyModel> loanList) {
        mTxtTotalInterestPayment.setText(DataHelper.formatMoney(totalInterestPayment));
        mTxtTotalPayment.setText(DataHelper.formatMoney(totalPayment));
        if (mLoanType == Constant.TYPE_PRINCIPAL_BALANCE) {
            mTxtInterestPaymentPerMonth.setText(DataHelper.formatMoney(interestPaymentPerMonth));
            mTxtPrincipalPaymentPerMonth.setText(DataHelper.formatMoney(principalPaymentPerMonth));
            mTxtTotalPaymentPerMonth.setText(DataHelper.formatMoney(totalPaymentPerMonth));
        } else if (mLoanType == Constant.TYPE_PRINCIPAL_BALANCE_DECREASE) {
            mCalculateInterestMoneyRecylerViewAdapter.updateAdapter(loanList);
        }
    }
}

package com.hungtran.bankingassistant.ui.calculator;

import com.hungtran.bankingassistant.model.CalculateInterestMoneyModel;
import com.hungtran.bankingassistant.model.ExchangeRate;
import com.hungtran.bankingassistant.ui.maket.exchangeRate.ExchangeRateContract;
import com.hungtran.bankingassistant.util.BasePresenter;
import com.hungtran.bankingassistant.util.BaseView;

import java.util.List;

public interface CalculatorContract {
    interface View extends BaseView<CalculatorContract.Presenter> {
        void onCalculateResult(List<Long> interestList, List<CalculateInterestMoneyModel> calculateInterestMoneyModelList, long totalInterest, long totalMoney);
        void onCalculateLoanResult(long interestPaymentPerMonth, long principalPaymentPerMonth,
                                  long totalPaymentPerMonth, long totalInterestPayment, long totalPayment,List<CalculateInterestMoneyModel> loanList);
    }

    interface Presenter extends BasePresenter {
        void calculateMoney(long initialMoney, double interestRate, int term, int timeSavingMoney, int timeSavingType, int withdrawType);
        void calculateLoanMoney(long inititalMoney, double interestRate, int term, int loanType);
    }
}

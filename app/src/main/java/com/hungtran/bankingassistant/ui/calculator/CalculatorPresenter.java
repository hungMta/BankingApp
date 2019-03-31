package com.hungtran.bankingassistant.ui.calculator;

import com.hungtran.bankingassistant.model.interestRate.CalculateInterestMoneyModel;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.DataHelper;

import java.util.ArrayList;
import java.util.List;

public class CalculatorPresenter implements CalculatorContract.Presenter {
    private CalculatorContract.View view;

    public CalculatorPresenter(CalculatorContract.View view) {
        this.view = view;
    }

    @Override
    public void calculateMoney(long initialMoney, double interestRate, int term, int timeSavingMoney,
                               int timeSavingType, int withdrawType) {
        if (timeSavingType == Constant.TYPE_SAVING_MONTH) {
        } else {
            timeSavingMoney *= 12;
        }
        List<Long> totalRecevingInterestMoneyList = DataHelper.calculateInterestRate(initialMoney,
                interestRate, term, timeSavingMoney, withdrawType);
        long totalRecevingInterestMoney = 0;
        for (long money: totalRecevingInterestMoneyList) {
            totalRecevingInterestMoney += money;
        }
        long totalRecevingMoney =  initialMoney + totalRecevingInterestMoney;

        List<CalculateInterestMoneyModel> calculateInterestMoneyModelList
                = getCalculateInterestMoneyModelList(totalRecevingInterestMoneyList, initialMoney);
        view.onCalculateResult(totalRecevingInterestMoneyList, calculateInterestMoneyModelList, totalRecevingInterestMoney, totalRecevingMoney);
    }

    private List<CalculateInterestMoneyModel> getCalculateInterestMoneyModelList(List<Long> interestMoneyList, long initalMoney) {
        List<CalculateInterestMoneyModel> calculateInterestMoneyModelList = new ArrayList<>();
        for (int i = 0; i < interestMoneyList.size(); i ++) {
            CalculateInterestMoneyModel calculateInterestMoneyModel = new CalculateInterestMoneyModel();
            calculateInterestMoneyModel.setTimeCount(i + 1);
            calculateInterestMoneyModel.setInitialMoney(initalMoney);
            calculateInterestMoneyModel.setInititalMoneyString(DataHelper.formatMoney(initalMoney));
            calculateInterestMoneyModel.setReceivingInterestMoney(interestMoneyList.get(i));
            calculateInterestMoneyModel.setReceivingInterestMoneyString(DataHelper.formatMoney(interestMoneyList.get(i)));
            initalMoney += interestMoneyList.get(i);
            calculateInterestMoneyModel.setTotalReceivingMoney(initalMoney);
            calculateInterestMoneyModel.setTotalReceivingMoneyString(DataHelper.formatMoney(initalMoney));
            calculateInterestMoneyModelList.add(calculateInterestMoneyModel);
        }
        return calculateInterestMoneyModelList;
    }


    @Override
    public void calculateLoanMoney(long inititalMoney, double interestRate, int term, int loanType) {
        if (term == 0) return;
        if (loanType == Constant.TYPE_PRINCIPAL_BALANCE) {
            long interestPaymentPerMonth = (long) (inititalMoney * interestRate / 100 / term);
            long principalPaymentPerMonth = (long) (inititalMoney / term);
            long totalPaymentPerMonth = interestPaymentPerMonth + principalPaymentPerMonth;
            long totalInterestPayment = interestPaymentPerMonth * 12;
            long totalPayment = inititalMoney + totalInterestPayment;
            view.onCalculateLoanResult(interestPaymentPerMonth, principalPaymentPerMonth,
                    totalPaymentPerMonth, totalInterestPayment, totalPayment, null);
        } else {
            int count = 1;
            long principalPaymentPerMonth = (long) (inititalMoney / term);
            long totalPayment = 0;
            long totalInterestPayment = 0;
            List<CalculateInterestMoneyModel> list = new ArrayList<>();
            while (count <= term) {
                long interestPaymentPerMonth = (long) (inititalMoney * interestRate / 100 / term);
                long totalPaymentPerMonth = principalPaymentPerMonth + interestPaymentPerMonth;
                totalInterestPayment += interestPaymentPerMonth;
                totalPayment += totalPaymentPerMonth;
                inititalMoney -= totalPaymentPerMonth;
                CalculateInterestMoneyModel calculateInterestMoneyModel = new CalculateInterestMoneyModel();
                calculateInterestMoneyModel.setTimeCount(count);
                calculateInterestMoneyModel.setPrincipalPaymentPerMonth(principalPaymentPerMonth);
                calculateInterestMoneyModel.setPrincipalPaymentPerMonthString(DataHelper.formatMoney(principalPaymentPerMonth));
                calculateInterestMoneyModel.setInterestPaymentPerMonth(interestPaymentPerMonth);
                calculateInterestMoneyModel.setInterestPaymentPerMonthString(DataHelper.formatMoney(interestPaymentPerMonth));
                calculateInterestMoneyModel.setTotalPaymentPerMonth(totalPaymentPerMonth);
                calculateInterestMoneyModel.setTotalPaymentPerMonthString(DataHelper.formatMoney(totalPaymentPerMonth));
                list.add(calculateInterestMoneyModel);
                count += 1;
            }
            view.onCalculateLoanResult(0, principalPaymentPerMonth,
                    0, totalInterestPayment, totalPayment, list);
        }
    }

    @Override
    public void start() {

    }
}

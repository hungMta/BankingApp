package com.hungtran.bankingassistant.ui.calculator;

import com.hungtran.bankingassistant.model.CalculateInterestMoneyModel;
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
    public void start() {

    }
}

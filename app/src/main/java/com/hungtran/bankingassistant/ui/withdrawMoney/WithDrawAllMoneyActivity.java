package com.hungtran.bankingassistant.ui.withdrawMoney;

import com.hungtran.bankingassistant.util.base.BaseActivity;

public class WithDrawAllMoneyActivity extends BaseActivity implements WithdrawMoneyContract.View{
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void openOTPActivity(int transactionId) {

    }

    @Override
    public void withDrawMoneyFail(String message) {

    }
}

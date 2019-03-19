package com.hungtran.bankingassistant.ui.maket.exchangeRate;

import com.hungtran.bankingassistant.model.exchangeRate.ExchangeRate;
import com.hungtran.bankingassistant.util.BasePresenter;
import com.hungtran.bankingassistant.util.BaseView;

import java.util.List;

public interface ExchangeRateContract {

    interface View extends BaseView<Presenter> {
        void showExchangeRates(List<ExchangeRate> list);
        void hideProgressBar();
    }

    interface Presenter extends BasePresenter {
        void getExchangeRates();
    }
}

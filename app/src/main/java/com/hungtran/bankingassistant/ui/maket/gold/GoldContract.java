package com.hungtran.bankingassistant.ui.maket.gold;

import com.hungtran.bankingassistant.model.gold.GoldAreaResponse;
import com.hungtran.bankingassistant.util.BasePresenter;
import com.hungtran.bankingassistant.util.BaseView;

public interface GoldContract {

    interface View extends BaseView<GoldContract.Presenter> {
        void onGetGoldPriceSuccess(GoldAreaResponse goldAreaResponse);
    }

    interface Presenter extends BasePresenter {
        void getGoldPrice();
    }
}

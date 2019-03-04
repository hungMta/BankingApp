package com.hungtran.bankingassistant.ui.coin;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.util.base.BaseFragment;

public class CoinFragment extends BaseFragment {

    private static CoinFragment instance;

    public static CoinFragment getInstance() {
        if (instance == null) {
            instance = new CoinFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_coin;
    }
}

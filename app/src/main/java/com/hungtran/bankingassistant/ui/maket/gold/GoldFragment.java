package com.hungtran.bankingassistant.ui.maket.gold;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.util.base.BaseFragment;

public class GoldFragment extends BaseFragment {

    private static GoldFragment instance;

    public static GoldFragment getInstance() {
        if (instance == null) {
            instance = new GoldFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gold;
    }
}

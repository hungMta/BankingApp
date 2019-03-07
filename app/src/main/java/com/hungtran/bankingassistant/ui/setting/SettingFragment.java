package com.hungtran.bankingassistant.ui.setting;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.util.base.BaseFragment;

public class SettingFragment extends BaseFragment {
    private static SettingFragment instance;

    public static SettingFragment getInstance() {
        if (instance == null) {
            instance = new SettingFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_caluclator;
    }
}

package com.hungtran.bankingassistant.ui.news;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.util.base.BaseFragment;

public class NewsFragment extends BaseFragment {

    private static NewsFragment instance;

    public static NewsFragment getInstance() {
        if (instance == null) {
            instance = new NewsFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }
}

package com.hungtran.bankingassistant.ui.map;


import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.util.base.BaseFragment;

/**
 * Created by hungtd on 2/18/19.
 */

public class MapFragment extends BaseFragment {
    private static MapFragment instance;

    public static MapFragment getInstance() {
        if (instance == null) {
            instance = new MapFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_map;
    }
}

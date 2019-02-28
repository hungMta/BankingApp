package com.hungtran.bankingassistant.util.base;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by hungtd on 2/18/19.
 */

public abstract class BaseFragment extends Fragment {
    public abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( getLayoutId(), container, false);
        return  view;
    }
}

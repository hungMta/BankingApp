package com.hungtran.bankingassistant.util;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.PopupWindow;

import com.hungtran.bankingassistant.R;

public class PopupHelper {

    public static PopupWindow getDefautPopup(Context context, RecyclerView.Adapter adapter, int with, int height) {
        PopupWindow popupWindow = new PopupWindow(context);
        RecyclerView recyclerView = new RecyclerView(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        popupWindow.setFocusable(true);
        popupWindow.setWidth(with);
        popupWindow.setHeight(height);
        popupWindow.setContentView(recyclerView);
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_white_corner_five_radius));
        return popupWindow;
    }
}

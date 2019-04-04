package com.hungtran.bankingassistant.ui.myAccountCardList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public class MyAccountCardListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ATMCardItem extends RecyclerView.ViewHolder {

        public ATMCardItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

    public class SavingMoneyItem extends RecyclerView.ViewHolder {

        public SavingMoneyItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}

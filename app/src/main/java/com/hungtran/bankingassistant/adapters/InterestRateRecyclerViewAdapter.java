package com.hungtran.bankingassistant.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hungtran.bankingassistant.R;

import java.security.PublicKey;

import butterknife.ButterKnife;

/**
 * Created by hungtd on 3/6/19.
 */

public class InterestRateRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_interest_rate_reyclerview, viewGroup, false);
        InterestRateItem interestRateItem = new InterestRateItem(view);
        return interestRateItem;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class InterestRateItem extends RecyclerView.ViewHolder {



        public InterestRateItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package com.hungtran.bankingassistant.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.GoldArea;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoldAreaRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<GoldArea> goldAreas;

    public GoldAreaRecyclerViewAdapter(List<GoldArea> list) {
        this.goldAreas = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_gold_price_area_recyclerview,viewGroup,false);
        return new GoldAreaItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((GoldAreaItem) viewHolder).mTxtArea.setText(goldAreas.get(i).getArea());
    }

    @Override
    public int getItemCount() {
        return goldAreas == null ? 0 : goldAreas.size();
    }

    public void upateAdapter(List<GoldArea> list) {
        this.goldAreas = list;
        notifyDataSetChanged();
    }

    public class GoldAreaItem extends RecyclerView.ViewHolder {

        @BindView(R.id.txtArea)
        TextView mTxtArea;

        @BindView(R.id.recyclerViewGoldInArea)
        RecyclerView mRecyclerViewGoldInArea;


        public GoldAreaItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

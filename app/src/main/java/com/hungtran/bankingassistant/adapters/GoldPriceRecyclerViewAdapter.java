package com.hungtran.bankingassistant.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.Gold;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hungtd on 3/11/19.
 */

public class GoldPriceRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<Gold> golds;

    public GoldPriceRecyclerViewAdapter(List<Gold> golds) {
        this.golds = golds;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_gold_price_recyclerview, viewGroup, false);
        return new GoldPriceItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((GoldPriceItem)viewHolder).mTxtGoldName.setText(golds.get(i).getName());
        ((GoldPriceItem)viewHolder).mTxtGoldBuy.setText(golds.get(i).getBuy());
        ((GoldPriceItem)viewHolder).mTxtGoldSell.setText(golds.get(i).getSell());
    }

    @Override
    public int getItemCount() {
        return golds == null ? 0 : golds.size();
    }

    public class GoldPriceItem extends RecyclerView.ViewHolder {

        @BindView(R.id.txtGoldName)
        TextView mTxtGoldName;

        @BindView(R.id.txtGoldBuy)
        TextView mTxtGoldBuy;

        @BindView(R.id.txtGoldSell)
        TextView mTxtGoldSell;

        public GoldPriceItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

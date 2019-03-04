package com.hungtran.bankingassistant.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.Currency;
import com.hungtran.bankingassistant.model.ExchangeRate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExchangeRateRecylerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Currency> currencies = new ArrayList<>();

    public ExchangeRateRecylerViewAdapter(Context context, List<Currency> list) {
        this.mContext = context;
        this.currencies = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_exchange_rate_recyler_view, viewGroup, false);
        CurrencyItem currencyItem = new CurrencyItem(view);
        return currencyItem;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (i % 2 == 0) {
            ((CurrencyItem) viewHolder).mLayout
                    .setBackgroundColor(mContext.getColor(R.color.colorLight2Gray));
        } else {
            ((CurrencyItem) viewHolder).mLayout.setBackgroundColor(Color.WHITE);
        }
        ((CurrencyItem) viewHolder).mCurrencyName.setText("number " + i);
    }

    @Override
    public int getItemCount() {
        return currencies.size() ;
    }

    public void updateAdapter(List<Currency> list) {
        this.currencies = list;
        notifyDataSetChanged();
    }


    public static class CurrencyItem extends RecyclerView.ViewHolder {

        @BindView(R.id.layoutItem)
        LinearLayout mLayout;

        @BindView(R.id.txtCurrencyName)
        TextView mCurrencyName;

        public CurrencyItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package com.hungtran.bankingassistant.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.exchangeRate.Currency;
import com.hungtran.bankingassistant.model.exchangeRate.ExchangeRate;
import com.hungtran.bankingassistant.model.gold.Gold;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExchangeRateRecylerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Currency> currencies = new ArrayList<>();
    private ExchangeRate mExchangeRate;
    private PopupWindow mPopup;


    public ExchangeRateRecylerViewAdapter(Context context, ExchangeRate exchangeRate) {
        this.mContext = context;
        this.mExchangeRate = exchangeRate;
        this.currencies = exchangeRate.getCurrentExchangerate();
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

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (i % 2 == 0) {
                ((CurrencyItem) viewHolder).mLayout
                        .setBackgroundColor(mContext.getColor(R.color.colorLight2Gray));
            } else {
                ((CurrencyItem) viewHolder).mLayout.setBackgroundColor(Color.WHITE);
            }
        } else {
            if (i % 2 == 0) {
                ((CurrencyItem) viewHolder).mLayout
                        .setBackgroundColor(mContext.getResources().getColor(R.color.colorLight2Gray));
            } else {
                ((CurrencyItem) viewHolder).mLayout.setBackgroundColor(Color.WHITE);
            }
        }


        ((CurrencyItem) viewHolder).mCurrencyName.setText(currencies.get(i).getCodeName());
        ((CurrencyItem) viewHolder).mTxtBuyCash.setText(currencies.get(i).getBuyCashString());
        ((CurrencyItem) viewHolder).mTxtBuyCard.setText(currencies.get(i).getBuyCardString());
        ((CurrencyItem) viewHolder).mTxtSellCash.setText(currencies.get(i).getSellCashString());
        String imgUrl = currencies.get(i).getImageURL();
        Picasso.get().load(imgUrl).into(((CurrencyItem) viewHolder).mImgCurrency);


        double priceChange = currencies.get(i).getBuyCashChange();
        if (priceChange == 0) {
            ((CurrencyItem) viewHolder).mImgBuyCashStatus.setImageDrawable(null);
        } else if (priceChange > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((CurrencyItem) viewHolder).mImgBuyCashStatus.setImageDrawable(mContext.getDrawable(R.drawable.ic_icon_up));
            } else {
                ((CurrencyItem) viewHolder).mImgBuyCashStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_icon_up));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((CurrencyItem) viewHolder).mImgBuyCashStatus.setImageDrawable(mContext.getDrawable(R.drawable.ic_icon_down));
            } else {
                ((CurrencyItem) viewHolder).mImgBuyCashStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_icon_down));
            }
        }


        double buyCardChange = currencies.get(i).getBuyCardChange();
        if (buyCardChange == 0) {
            ((CurrencyItem) viewHolder).mImgBuyCardStatus.setImageDrawable(null);
        } else if (buyCardChange > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((CurrencyItem) viewHolder).mImgBuyCardStatus.setImageDrawable(mContext.getDrawable(R.drawable.ic_icon_up));
            } else {
                ((CurrencyItem) viewHolder).mImgBuyCardStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_icon_up));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((CurrencyItem) viewHolder).mImgBuyCardStatus.setImageDrawable(mContext.getDrawable(R.drawable.ic_icon_down));
            } else {
                ((CurrencyItem) viewHolder).mImgBuyCardStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_icon_down));
            }
        }

        double sellCashChange = currencies.get(i).getSellCashChange();
        if (sellCashChange == 0) {
            ((CurrencyItem) viewHolder).mImgSellCashStatus.setImageDrawable(null);
        } else if (sellCashChange > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((CurrencyItem) viewHolder).mImgSellCashStatus.setImageDrawable(mContext.getDrawable(R.drawable.ic_icon_up));
            } else {
                ((CurrencyItem) viewHolder).mImgSellCashStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_icon_up));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((CurrencyItem) viewHolder).mImgSellCashStatus.setImageDrawable(mContext.getDrawable(R.drawable.ic_icon_down));
            } else {
                ((CurrencyItem) viewHolder).mImgSellCashStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_icon_down));
            }
        }

        ((CurrencyItem) viewHolder).mLayoutBuyCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopup = getPopupWindow(currencies.get(i), 0);
                mPopup.showAsDropDown(v);
            }
        });

        ((CurrencyItem) viewHolder).mLayoutBuyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopup = getPopupWindow(currencies.get(i), 1);
                mPopup.showAsDropDown(v);
            }
        });

        ((CurrencyItem) viewHolder).mLayoutSellCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopup = getPopupWindow(currencies.get(i), 2);
                mPopup.showAsDropDown(v);
            }
        });
    }


    @Override
    public int getItemCount() {
        return currencies == null ? 0 : currencies.size();
    }


    private PopupWindow getPopupWindow(Currency currency, int type) {
        PopupWindow mPopup = new PopupWindow(mContext);
        ExchangeRatePopupDetailRecyclerViewAdapter adapter = new ExchangeRatePopupDetailRecyclerViewAdapter(mContext, mExchangeRate, currency, type);
        RecyclerView recyclerView = new RecyclerView(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        mPopup.setFocusable(true);
        mPopup.setWidth(600);
        mPopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopup.setContentView(recyclerView);
        mPopup.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_white_corner_five_radius));
        return mPopup;
    }


    public void updateAdapter(ExchangeRate exchangeRate) {
        this.mExchangeRate = exchangeRate;
        this.currencies = exchangeRate.getCurrentExchangerate();
        notifyDataSetChanged();
    }


    public static class CurrencyItem extends RecyclerView.ViewHolder {

        @BindView(R.id.layoutItem)
        LinearLayout mLayout;

        @BindView(R.id.txtCurrencyName)
        TextView mCurrencyName;

        @BindView(R.id.txtBuyCash)
        TextView mTxtBuyCash;

        @BindView(R.id.txtBuyCard)
        TextView mTxtBuyCard;

        @BindView(R.id.txtSellCash)
        TextView mTxtSellCash;

        @BindView(R.id.imgCurrency)
        ImageView mImgCurrency;

        @BindView(R.id.layoutSellCash)
        RelativeLayout mLayoutSellCash;

        @BindView(R.id.layoutBuyCard)
        RelativeLayout mLayoutBuyCard;

        @BindView(R.id.layoutBuyCash)
        RelativeLayout mLayoutBuyCash;

        @BindView(R.id.imgBuyCashStatus)
        ImageView mImgBuyCashStatus;

        @BindView(R.id.imgBuyCardStatus)
        ImageView mImgBuyCardStatus;

        @BindView(R.id.imgSellCashStatus)
        ImageView mImgSellCashStatus;

        public CurrencyItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

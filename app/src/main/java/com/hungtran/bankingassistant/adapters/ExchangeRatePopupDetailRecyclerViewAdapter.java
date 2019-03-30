package com.hungtran.bankingassistant.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.exchangeRate.Currency;
import com.hungtran.bankingassistant.model.exchangeRate.ExchangeRate;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.DataHelper;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRatePopupDetailRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private Currency mCurrentCurrency;
    private ExchangeRate exchangeRate;
    private int mType;

    public ExchangeRatePopupDetailRecyclerViewAdapter(Context context, ExchangeRate exchangeRate, Currency currency, int type) {
        this.mContext = context;
        this.mCurrentCurrency = currency;
        this.exchangeRate = exchangeRate;
        this.mType = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_price_detail_popup_recycler_view, viewGroup, false);
        return new PriceDetailItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Currency oldPrice1 = getOldCurrency(1);
        Currency oldPrice2 = getOldCurrency(2);
        double dif = 0;
        String time = "";
        double price = 0;
        double difference = 0;
        int typeTime = 0;

        if (mType == 0) { // buy cash
            if (i == 0) {
                dif = mCurrentCurrency.getBuyCash();
                time = mCurrentCurrency.getTimeCrawling();
                price = mCurrentCurrency.getBuyCash();
                difference = mCurrentCurrency.getBuyCashChange();
            } else if (i == 1 && oldPrice1 != null) {
                dif = oldPrice1.getBuyCash();
                time = oldPrice1.getTimeCrawling();
                price = oldPrice1.getBuyCash();
                difference = oldPrice1.getBuyCashChange();
                typeTime = 1;
            } else if (i == 2 && oldPrice2 != null) {
                dif = oldPrice2.getBuyCash();
                time = oldPrice2.getTimeCrawling();
                price = oldPrice2.getBuyCash();
                difference = oldPrice2.getBuyCashChange();
                typeTime = 1;
            } else {
                dif = mCurrentCurrency.getBuyCash();
                time = mCurrentCurrency.getTimeCrawling();
                price = mCurrentCurrency.getBuyCash();
                difference = mCurrentCurrency.getBuyCashChange();
            }
        } else if (mType == 1) { // buy card
            if (i == 0) {
                dif = mCurrentCurrency.getBuyCard();
                time = mCurrentCurrency.getTimeCrawling();
                price = mCurrentCurrency.getBuyCard();
                difference = mCurrentCurrency.getBuyCardChange();
            } else if (i == 1 && oldPrice1 != null) {
                dif = oldPrice1.getBuyCard();
                time = oldPrice1.getTimeCrawling();
                price = oldPrice1.getBuyCard();
                difference = oldPrice1.getBuyCardChange();
                typeTime = 1;
            } else if (i == 2 && oldPrice2 != null) {
                dif = oldPrice2.getBuyCard();
                time = oldPrice2.getTimeCrawling();
                price = oldPrice2.getBuyCard();
                difference = oldPrice2.getBuyCardChange();
                typeTime = 1;
            } else {
                dif = mCurrentCurrency.getBuyCard();
                time = mCurrentCurrency.getTimeCrawling();
                price = mCurrentCurrency.getBuyCard();
                difference = mCurrentCurrency.getBuyCardChange();
            }
        } else if (mType == 2) { // sell cash
            if (i == 0) {
                dif = mCurrentCurrency.getSellCash();
                time = mCurrentCurrency.getTimeCrawling();
                price = mCurrentCurrency.getSellCash();
                difference = mCurrentCurrency.getSellCashChange();
            } else if (i == 1 && oldPrice1 != null) {
                dif = oldPrice1.getSellCash();
                time = oldPrice1.getTimeCrawling();
                price = oldPrice1.getSellCash();
                difference = oldPrice1.getSellCashChange();
                typeTime = 1;
            } else if (i == 2 && oldPrice2 != null) {
                dif = oldPrice2.getSellCash();
                time = oldPrice2.getTimeCrawling();
                price = oldPrice2.getSellCash();
                difference = oldPrice2.getSellCashChange();
                typeTime = 1;
            } else {
                dif = mCurrentCurrency.getSellCash();
                time = mCurrentCurrency.getTimeCrawling();
                price = mCurrentCurrency.getSellCash();
                difference = mCurrentCurrency.getSellCashChange();
            }
        }


        ((PriceDetailItem) viewHolder).mTxtTime.setText(DataHelper.getTime(time, typeTime));
        ((PriceDetailItem) viewHolder).mTxtPrice.setText(DataHelper.formatMoney(price));
        ((PriceDetailItem) viewHolder).mTxtDifference.setText(DataHelper.formatMoney(difference));


        String color = "#FFFFFF";
        if (difference == 0) {
            ((PriceDetailItem) viewHolder).mImgStatus.setImageDrawable(null);
            ((PriceDetailItem) viewHolder).mTxtDifference.setText("");
            color = Constant.COLOR_UNCHANGE;
        } else if (difference > 0) {
            color = Constant.COLOR_UP;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((PriceDetailItem) viewHolder).mImgStatus.setImageDrawable(mContext.getDrawable(R.drawable.ic_icon_up));
            } else {
                ((PriceDetailItem) viewHolder).mImgStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_icon_up));
            }
        } else {
            color = Constant.COLOR_DOWN;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((PriceDetailItem) viewHolder).mImgStatus.setImageDrawable(mContext.getDrawable(R.drawable.ic_icon_down));
            } else {
                ((PriceDetailItem) viewHolder).mImgStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_icon_down));
            }
        }

        ((PriceDetailItem) viewHolder).mTxtTime.setTextColor(Color.parseColor(color));
        ((PriceDetailItem) viewHolder).mTxtPrice.setTextColor(Color.parseColor(color));
        ((PriceDetailItem) viewHolder).mTxtDifference.setTextColor(Color.parseColor(color));

    }

    private Currency getOldCurrency(int type) {
        List<Currency> list = new ArrayList<>();
        if (type == 1) {
            list = exchangeRate.getOldExchangerate1();
        } else if (type == 2) {
            list = exchangeRate.getOldExchangerate2();
        }
        for (Currency currency : list
                ) {
            if (currency.getId() == mCurrentCurrency.getId()) {
                return currency;
            }
        }
        return new Currency();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

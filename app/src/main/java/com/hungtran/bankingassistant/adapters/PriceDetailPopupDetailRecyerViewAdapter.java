package com.hungtran.bankingassistant.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.PluralsRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.gold.Gold;
import com.hungtran.bankingassistant.model.gold.GoldPrice;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.DataHelper;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PriceDetailPopupDetailRecyerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private Gold mGold;
    private int mType; // buy or sell

    public PriceDetailPopupDetailRecyerViewAdapter(Context context, Gold gold, int type) {
        this.mContext = context;
        this.mGold = gold;
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
        GoldPrice currentPrice = mGold.getCurrentPrice();
        GoldPrice oldPrice1 = mGold.getOldPrice1();
        GoldPrice oldPrice2 = mGold.getOldPrice2();
        double dif = 0;
        String time = "";
        String price = "";
        String difference = "";
        int type = 0;

        if (mType == 0) { // buy
            if (i == 0) {
                dif = Double.parseDouble(currentPrice.getBuyChange());
                time = currentPrice.getTimeCrawling();
                price = currentPrice.getBuy();
                difference = currentPrice.getBuyChange();
            } else if (i == 1 && oldPrice1 != null) {
                dif = Double.parseDouble(oldPrice1.getBuyChange());
                time = oldPrice1.getTimeCrawling();
                price = oldPrice1.getBuy();
                difference = oldPrice1.getBuyChange();
                type = 1;
            } else if (i == 2 && oldPrice2 != null) {
                dif = Double.parseDouble(oldPrice2.getBuyChange());
                time = oldPrice2.getTimeCrawling();
                price = oldPrice2.getBuy();
                difference = oldPrice2.getBuyChange();
                type = 1;
            } else {
                dif = Double.parseDouble(currentPrice.getBuyChange());
                time = currentPrice.getTimeCrawling();
                price = currentPrice.getBuy();
                difference = currentPrice.getBuyChange();
            }
        } else { // sel
            if (i == 0) {
                dif = Double.parseDouble(currentPrice.getSellChange());
                time = currentPrice.getTimeCrawling();
                price = currentPrice.getSell();
                difference = currentPrice.getSellChange();
            } else if (i == 1 && oldPrice1 != null) {
                dif = Double.parseDouble(oldPrice1.getSellChange());
                time = oldPrice1.getTimeCrawling();
                price = oldPrice1.getSell();
                difference = oldPrice1.getSellChange();
                type = 1;
            } else if (i == 2 && oldPrice2 != null) {
                dif = Double.parseDouble(oldPrice2.getSellChange());
                time = oldPrice2.getTimeCrawling();
                price = oldPrice2.getSell();
                difference = oldPrice2.getSellChange();
                type = 1;
            } else {
                dif = Double.parseDouble(currentPrice.getBuyChange());
                time = currentPrice.getTimeCrawling();
                price = currentPrice.getBuy();
                difference = currentPrice.getBuyChange();
            }
        }


        ((PriceDetailItem) viewHolder).mTxtTime.setText(getTime(time,type));
        ((PriceDetailItem) viewHolder).mTxtPrice.setText(DataHelper.formatMoney(Double.parseDouble(price)));
        ((PriceDetailItem) viewHolder).mTxtDifference.setText(difference);

        String color = "#FFFFFF";
        if (dif == 0) {
            ((PriceDetailItem) viewHolder).mImgStatus.setImageDrawable(null);
            ((PriceDetailItem) viewHolder).mTxtDifference.setText("");
            color = Constant.COLOR_UNCHANGE;
        } else if (dif > 0) {
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

    @Override
    public int getItemCount() {
        return 3;
    }

    private String getTime(String time, int type) {
        Date date = DataHelper.getDateFromString(time);
        if (date == null) return  "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (type == 0) {
            int hour = calendar.get(Calendar.HOUR);
            int min = calendar.get(Calendar.MINUTE);
            return "" + hour + ":" + min;
        } else {
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DATE);
            return "" + day + "/" + month;
        }
    }

    public class PriceDetailItem extends RecyclerView.ViewHolder {

        @BindView(R.id.txtTime)
        TextView mTxtTime;

        @BindView(R.id.txtPrice)
        TextView mTxtPrice;

        @BindView(R.id.txtDifference)
        TextView mTxtDifference;

        @BindView(R.id.imgStatus)
        ImageView mImgStatus;

        public PriceDetailItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

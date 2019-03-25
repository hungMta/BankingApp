package com.hungtran.bankingassistant.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.gold.Gold;
import com.hungtran.bankingassistant.util.DataHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hungtd on 3/11/19.
 */

public class GoldPriceRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<Gold> golds;
    private Context mContext;
    private PopupWindow mPopup;

    public GoldPriceRecyclerViewAdapter(Context context, List<Gold> golds) {
        this.mContext = context;
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
        ((GoldPriceItem) viewHolder).mTxtGoldName.setText(golds.get(i).getName());
        String buy = golds.get(i).getCurrentPrice().getBuy();
        double _buy = Double.parseDouble(buy);
        String sell = golds.get(i).getCurrentPrice().getSell();
        double _sell = Double.parseDouble(sell);
        ((GoldPriceItem) viewHolder).mTxtGoldBuy.setText(DataHelper.formatMoney(_buy));
        ((GoldPriceItem) viewHolder).mTxtGoldSell.setText(DataHelper.formatMoney(_sell));

        double buyChangePrice = Double.parseDouble(golds.get(i).getCurrentPrice().getBuyChange());
        if (buyChangePrice == 0) {
            ((GoldPriceItem) viewHolder).mImgBuyStatus.setImageDrawable(null);
            ((GoldPriceItem) viewHolder).mTxtBuyChange.setText("");
        } else if (buyChangePrice > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((GoldPriceItem) viewHolder).mImgBuyStatus.setImageDrawable(mContext.getDrawable(R.drawable.ic_icon_up));
            } else {
                ((GoldPriceItem) viewHolder).mImgBuyStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_icon_up));
            }
            ((GoldPriceItem) viewHolder).mTxtBuyChange.setText(golds.get(i).getCurrentPrice().getBuyChange());
            ((GoldPriceItem) viewHolder).mTxtBuyChange.setTextColor(Color.parseColor("#187139"));
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((GoldPriceItem) viewHolder).mImgBuyStatus.setImageDrawable(mContext.getDrawable(R.drawable.ic_icon_down));
            } else {
                ((GoldPriceItem) viewHolder).mImgBuyStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_icon_down));
            }
            ((GoldPriceItem) viewHolder).mTxtBuyChange.setText(golds.get(i).getCurrentPrice().getBuyChange());
            ((GoldPriceItem) viewHolder).mTxtBuyChange.setTextColor(Color.parseColor("#bf1414"));
        }


        double sellChangePrice = Double.parseDouble(golds.get(i).getCurrentPrice().getSellChange());
        if (sellChangePrice == 0) {
            ((GoldPriceItem) viewHolder).mImgSellStatus.setImageDrawable(null);
            ((GoldPriceItem) viewHolder).mTxtSellChange.setText("");
        } else if (buyChangePrice > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((GoldPriceItem) viewHolder).mImgSellStatus.setImageDrawable(mContext.getDrawable(R.drawable.ic_icon_up));
            } else {
                ((GoldPriceItem) viewHolder).mImgSellStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_icon_up));
            }
            ((GoldPriceItem) viewHolder).mTxtSellChange.setText(golds.get(i).getCurrentPrice().getSellChange());
            ((GoldPriceItem) viewHolder).mTxtSellChange.setTextColor(Color.parseColor("#187139"));
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((GoldPriceItem) viewHolder).mImgSellStatus.setImageDrawable(mContext.getDrawable(R.drawable.ic_icon_down));
            } else {
                ((GoldPriceItem) viewHolder).mImgSellStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_icon_down));
            }
            ((GoldPriceItem) viewHolder).mTxtSellChange.setText(golds.get(i).getCurrentPrice().getSellChange());
            ((GoldPriceItem) viewHolder).mTxtSellChange.setTextColor(Color.parseColor("#bf1414"));
        }


        ((GoldPriceItem) viewHolder).mLayoutGoldBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopup = getPopupWindow(golds.get(i), 0);
                mPopup.showAsDropDown(v);
            }
        });


        ((GoldPriceItem) viewHolder).mLayoutGoldSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopup = getPopupWindow(golds.get(i), 1);
                mPopup.showAsDropDown(v);
            }
        });

    }

    private PopupWindow getPopupWindow(Gold gold, int type) {
        PopupWindow mPopup = new PopupWindow(mContext);
        PriceDetailPopupDetailRecyerViewAdapter adapter = new PriceDetailPopupDetailRecyerViewAdapter(mContext, gold, type);
        RecyclerView recyclerView = new RecyclerView(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        mPopup.setFocusable(true);
        mPopup.setWidth(800);
        mPopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopup.setContentView(recyclerView);
        mPopup.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_white_corner_five_radius));
        return mPopup;
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

        @BindView(R.id.imgBuyStatus)
        ImageView mImgBuyStatus;

        @BindView(R.id.imgSellStatus)
        ImageView mImgSellStatus;

        @BindView(R.id.txtBuyChange)
        TextView mTxtBuyChange;

        @BindView(R.id.txtSellChange)
        TextView mTxtSellChange;

        @BindView(R.id.layoutGoldBuy)
        RelativeLayout mLayoutGoldBuy;

        @BindView(R.id.layoutGoldSell)
        RelativeLayout mLayoutGoldSell;

        public GoldPriceItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

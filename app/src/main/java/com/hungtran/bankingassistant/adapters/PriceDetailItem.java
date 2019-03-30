package com.hungtran.bankingassistant.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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

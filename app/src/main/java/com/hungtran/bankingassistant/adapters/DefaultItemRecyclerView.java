package com.hungtran.bankingassistant.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DefaultItemRecyclerView extends RecyclerView.ViewHolder {

    @BindView(R.id.layoutItem)
    public LinearLayout layoutItem;

    @BindView(R.id.text)
    public TextView text;

    public DefaultItemRecyclerView(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}

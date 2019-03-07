package com.hungtran.bankingassistant.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hungtd on 3/7/19.
 */

public class TimeDepositRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemClick mItemClick;

    List<String> list = new ArrayList<>();

    public TimeDepositRecyclerViewAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_time_deposit_popup, viewGroup, false);
        return new TimeDepositItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((TimeDepositItem) viewHolder).timeDeposit.setText(list.get(i));
        ((TimeDepositItem) viewHolder).layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClick != null) {
                    mItemClick.timeDepositItemClicked();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class TimeDepositItem extends RecyclerView.ViewHolder {

        @BindView(R.id.layoutItem)
        RelativeLayout layout;

        @BindView(R.id.txtTimeDeposit)
        TextView timeDeposit;

        TimeDepositItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClick {
        void timeDepositItemClicked();
    }

    public void setOnItemClick(OnItemClick itemClick){
        this.mItemClick = itemClick;
    }
}

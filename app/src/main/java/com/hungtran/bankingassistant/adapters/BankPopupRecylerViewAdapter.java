package com.hungtran.bankingassistant.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.Bank;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hungtd on 3/5/19.
 */

public class BankPopupRecylerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Bank> mBanks = new ArrayList<>();
    private  OnItemClick mOnItemClick;

    public BankPopupRecylerViewAdapter(List<Bank> list) {
        this.mBanks = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bank_recylerview, viewGroup, false);
        BankListItem bankListItem = new BankListItem(view);
        return bankListItem;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((BankListItem) viewHolder).mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClick != null) {
                    mOnItemClick.itemBankPopupClick(new Bank());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class BankListItem extends RecyclerView.ViewHolder {

        @BindView(R.id.layoutItem)
        LinearLayout mLayout;

        public BankListItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClick {
        void itemBankPopupClick(Bank bank);
    }

    public  void setOnItemClick(OnItemClick itemClick) {
        mOnItemClick = itemClick;
    }
}

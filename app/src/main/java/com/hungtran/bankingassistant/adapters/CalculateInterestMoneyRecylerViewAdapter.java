package com.hungtran.bankingassistant.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.interestRate.CalculateInterestMoneyModel;
import com.hungtran.bankingassistant.util.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalculateInterestMoneyRecylerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<CalculateInterestMoneyModel> list;
    private int type;

    public CalculateInterestMoneyRecylerViewAdapter(int type, List<CalculateInterestMoneyModel> list) {
        this.list = list;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_calculate_money_recyclerview, viewGroup, false);
        return new CalculateInterestMoneyItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((CalculateInterestMoneyItem) viewHolder).mTxtTimeCount.setText(String.valueOf(i + 1));
        if (type == Constant.TYPE_SAVING) {
            ((CalculateInterestMoneyItem) viewHolder).mTxtSendingInitialMoney.setText(list.get(i).getInititalMoneyString());
            ((CalculateInterestMoneyItem) viewHolder).mTxtReceivingInterestMoney.setText(list.get(i).getReceivingInterestMoneyString());
            ((CalculateInterestMoneyItem) viewHolder).mTxtTotalReceivingMoney.setText(list.get(i).getTotalReceivingMoneyString());
        } else {
            ((CalculateInterestMoneyItem) viewHolder).mTxtSendingInitialMoney.setText(list.get(i).getPrincipalPaymentPerMonthString());
            ((CalculateInterestMoneyItem) viewHolder).mTxtReceivingInterestMoney.setText(list.get(i).getInterestPaymentPerMonthString());
            ((CalculateInterestMoneyItem) viewHolder).mTxtTotalReceivingMoney.setText(list.get(i).getTotalPaymentPerMonthString());
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }



    public void updateAdapter(List<CalculateInterestMoneyModel> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public class CalculateInterestMoneyItem extends RecyclerView.ViewHolder {

        @BindView(R.id.txtTimeCount)
        TextView mTxtTimeCount;

        @BindView(R.id.txtReceivingInterestMoney)
        TextView mTxtReceivingInterestMoney;

        @BindView(R.id.txtSendingInitialMoney)
        TextView mTxtSendingInitialMoney;

        @BindView(R.id.txtTotalReceivingMoney)
        TextView mTxtTotalReceivingMoney;

        public CalculateInterestMoneyItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

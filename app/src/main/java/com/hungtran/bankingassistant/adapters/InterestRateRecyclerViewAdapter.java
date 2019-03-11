package com.hungtran.bankingassistant.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.InterestRateByBank;
import com.hungtran.bankingassistant.util.Constant;

import java.security.PublicKey;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hungtd on 3/6/19.
 */

public class InterestRateRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<InterestRateByBank> interesRateByBanks;
    private int firstRateType = Constant.TYPE_MONTH_3_RATE;
    private int secondRateType = Constant.TYPE_MONTH_6_RATE;
    private int thirdRateType = Constant.TYPE_MONTH_9_RATE;
    private int fourthRateType = Constant.TYPE_MONTH_12_RATE;

    public InterestRateRecyclerViewAdapter (List<InterestRateByBank> list) {
        this.interesRateByBanks = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_interest_rate_reyclerview, viewGroup, false);
        return  new InterestRateItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((InterestRateItem) viewHolder).mTxtBankName.setText(interesRateByBanks.get(i).getName());
        ((InterestRateItem) viewHolder).mTxtFirstRate.setText(getValueInterestRate(firstRateType, interesRateByBanks.get(i)));
        ((InterestRateItem) viewHolder).mTxtSecondRate.setText(getValueInterestRate(secondRateType, interesRateByBanks.get(i)));
        ((InterestRateItem) viewHolder).mTxtThirdRate.setText(getValueInterestRate(thirdRateType, interesRateByBanks.get(i)));
        ((InterestRateItem) viewHolder).mTxtFourthRate.setText(getValueInterestRate(fourthRateType, interesRateByBanks.get(i)));
//        try {
//            ((InterestRateItem) viewHolder).mTxtFirstRate.setText(getValueInterestRate(firstRateType, interesRateByBanks.get(i)));
//        }catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            ((InterestRateItem) viewHolder).mTxtSecondRate.setText(interesRateByBanks.get(i).getSendingOffline().getInterestRateVnd().getMonth6());
//        }catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            ((InterestRateItem) viewHolder).mTxtThirdRate.setText(interesRateByBanks.get(i).getSendingOffline().getInterestRateVnd().getMonth9());
//        }catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            ((InterestRateItem) viewHolder).mTxtFourthRate.setText(interesRateByBanks.get(i).getSendingOffline().getInterestRateVnd().getMonth12());
//        }catch (NullPointerException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public int getItemCount() {
        return interesRateByBanks  == null ? 0 : interesRateByBanks.size();
    }

    public void updateApdater(List<InterestRateByBank> list, int typeFirst, int typeSecond, int typeThird, int typeFourth) {
        this.interesRateByBanks = list;
        this.firstRateType = typeFirst;
        this.secondRateType = typeSecond;
        this.thirdRateType = typeThird;
        this.fourthRateType = typeFourth;
        notifyDataSetChanged();
    }

    private String getValueInterestRate(int rateType, InterestRateByBank interestRateByBank) {
        try {
            switch (rateType) {
                case 0:
                    return interestRateByBank.getSendingOffline().getInterestRateVnd().getUnlimited();
                case 1:
                    return interestRateByBank.getSendingOffline().getInterestRateVnd().getMonth1();
                case 2:
                    return interestRateByBank.getSendingOffline().getInterestRateVnd().getMonth2();
                case 3:
                    return interestRateByBank.getSendingOffline().getInterestRateVnd().getMonth3();
                case 6:
                    return interestRateByBank.getSendingOffline().getInterestRateVnd().getMonth6();
                case 9:
                    return interestRateByBank.getSendingOffline().getInterestRateVnd().getMonth9();
                case 12:
                    return interestRateByBank.getSendingOffline().getInterestRateVnd().getMonth12();
                case 24:
                    return interestRateByBank.getSendingOffline().getInterestRateVnd().getMonth24();
                case 36:
                    return interestRateByBank.getSendingOffline().getInterestRateVnd().getMonth36();
                default:
                    return null;
            }
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public class InterestRateItem extends RecyclerView.ViewHolder {

        @BindView(R.id.layoutItem)
        LinearLayout layout;

        @BindView(R.id.txtBankName)
        TextView mTxtBankName;

        @BindView(R.id.txtFirstRate)
        TextView mTxtFirstRate;

        @BindView(R.id.txtSecondRate)
        TextView mTxtSecondRate;

        @BindView(R.id.txtThirdRate)
        TextView mTxtThirdRate;

        @BindView(R.id.txtFourthRate)
        TextView mTxtFourthRate;

        public InterestRateItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

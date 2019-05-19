package com.hungtran.bankingassistant.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.interestRate.InterestRateByBank;
import com.hungtran.bankingassistant.util.Constant;

import java.util.ArrayList;
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
    private double maxFirstRate = 0;
    private double maxSecondRate = 0;
    private double maxThirdRate = 0;
    private double maxFourthRate = 0;
    private Context mContext;

    public InterestRateRecyclerViewAdapter(Context context, List<InterestRateByBank> list) {
        this.interesRateByBanks = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_interest_rate_reyclerview, viewGroup, false);
        return new InterestRateItem(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((InterestRateItem) viewHolder).mTxtBankName.setText(interesRateByBanks.get(i).getName());

        String value1 = getValueInterestRate(firstRateType, interesRateByBanks.get(i));
        if (value1 == null){
            value1 = "0";
        }
        ((InterestRateItem) viewHolder).mTxtFirstRate.setText(value1);
        double _value1 = Double.parseDouble(value1);
        if (maxFirstRate <= _value1 && maxFirstRate != 0) {
            ((InterestRateItem) viewHolder).mTxtFirstRate.setTextColor(Color.WHITE);
            ((InterestRateItem) viewHolder).mTxtFirstRate.setBackground(mContext.getDrawable(R.drawable.bg_primary_corner_radius_circle));
//            ((InterestRateItem) viewHolder).mTxtFirstRate.setBackground(mContext.getDrawable(R.drawable.bg_primary_corner_radius_circle));
        } else {
            ((InterestRateItem) viewHolder).mTxtFirstRate.setTextColor(Color.BLACK);
            ((InterestRateItem) viewHolder).mTxtFirstRate.setBackground(null);
        }


        String value2 = getValueInterestRate(secondRateType, interesRateByBanks.get(i));
        if (value2 == null){
            value2 = "0.0";
        }
        ((InterestRateItem) viewHolder).mTxtSecondRate.setText(value2);
        double _value2 = Double.parseDouble(value2);
        if (maxSecondRate <= _value2 && maxSecondRate != 0) {
            ((InterestRateItem) viewHolder).mTxtSecondRate.setTextColor(Color.WHITE);
            ((InterestRateItem) viewHolder).mTxtSecondRate.setBackground(mContext.getDrawable(R.drawable.bg_primary_corner_radius_circle));
        } else {
            ((InterestRateItem) viewHolder).mTxtSecondRate.setTextColor(Color.BLACK);
            ((InterestRateItem) viewHolder).mTxtSecondRate.setBackground(null);
        }


        String value3 = getValueInterestRate(thirdRateType, interesRateByBanks.get(i));
        if (value3 == null){
            value3 = "0.0";
        }
        ((InterestRateItem) viewHolder).mTxtThirdRate.setText(value3);
        double _value3 = Double.parseDouble(value3);
        if (maxThirdRate <= _value3 && maxThirdRate != 0) {
            ((InterestRateItem) viewHolder).mTxtThirdRate.setTextColor(Color.WHITE);
            ((InterestRateItem) viewHolder).mTxtThirdRate.setBackground(mContext.getDrawable(R.drawable.bg_primary_corner_radius_circle));
        } else {
            ((InterestRateItem) viewHolder).mTxtThirdRate.setTextColor(Color.BLACK);
            ((InterestRateItem) viewHolder).mTxtThirdRate.setBackground(null);
        }


        String value4 = getValueInterestRate(fourthRateType, interesRateByBanks.get(i));
        if (value4 == null) {
            value4 = "0.0";
        }
        ((InterestRateItem) viewHolder).mTxtFourthRate.setText(value4);
        double _value4 = Double.parseDouble(value4);
        if (maxFourthRate <= _value4 && maxFourthRate != 0) {
            ((InterestRateItem) viewHolder).mTxtFourthRate.setTextColor(Color.WHITE);
            ((InterestRateItem) viewHolder).mTxtFourthRate.setBackground(mContext.getDrawable(R.drawable.bg_primary_corner_radius_circle));
        } else {
            ((InterestRateItem) viewHolder).mTxtFourthRate.setTextColor(Color.BLACK);
            ((InterestRateItem) viewHolder).mTxtFourthRate.setBackground(null);
        }
    }

    @Override
    public int getItemCount() {
        return interesRateByBanks == null ? 0 : interesRateByBanks.size();
    }

    public void updateApdater(List<InterestRateByBank> list, int typeFirst, int typeSecond, int typeThird, int typeFourth) {
        this.interesRateByBanks = list;
        this.firstRateType = typeFirst;
        this.secondRateType = typeSecond;
        this.thirdRateType = typeThird;
        this.fourthRateType = typeFourth;
        maxFirstRate = getMaxValue(firstRateType, list);
        maxSecondRate = getMaxValue(secondRateType, list);
        maxThirdRate = getMaxValue(thirdRateType, list);
        maxFourthRate = getMaxValue(fourthRateType, list);
        notifyDataSetChanged();
    }

    private String getValueInterestRate(int rateType, InterestRateByBank interestRateByBank) {
        try {
            switch (rateType) {
                case 0:
                    try {
                        return interestRateByBank.getSendingOffline().getInterestRateVnd().getUnlimited();
                    } catch (NumberFormatException | NullPointerException e) {
                        e.printStackTrace();
                        return null;
                    }
                case 1:
                    try {
                        return interestRateByBank.getSendingOffline().getInterestRateVnd().getMonth1();
                    } catch (NumberFormatException | NullPointerException e) {
                        e.printStackTrace();
                        return null;
                    }
                case 2:
                    try {
                        return interestRateByBank.getSendingOffline().getInterestRateVnd().getMonth2();
                    } catch (NumberFormatException | NullPointerException e) {
                        e.printStackTrace();
                        return null;
                    }
                case 3:
                    try {
                        return interestRateByBank.getSendingOffline().getInterestRateVnd().getMonth3();
                    } catch (NumberFormatException | NullPointerException e) {
                        e.printStackTrace();
                        return null;
                    }
                case 6:
                    try {
                        return interestRateByBank.getSendingOffline().getInterestRateVnd().getMonth6();
                    } catch (NumberFormatException | NullPointerException e) {
                        e.printStackTrace();
                        return null;
                    }
                case 9:
                    try {
                        return interestRateByBank.getSendingOffline().getInterestRateVnd().getMonth9();
                    } catch (NumberFormatException | NullPointerException e) {
                        e.printStackTrace();
                        return null;
                    }
                case 12:
                    try {
                        return interestRateByBank.getSendingOffline().getInterestRateVnd().getMonth12();
                    } catch (NumberFormatException | NullPointerException e) {
                        e.printStackTrace();
                        return null;
                    }
                case 24:
                    try {
                        return interestRateByBank.getSendingOffline().getInterestRateVnd().getMonth24();
                    } catch (NumberFormatException | NullPointerException e) {
                        e.printStackTrace();
                        return null;
                    }
                case 36:
                    try {
                        return interestRateByBank.getSendingOffline().getInterestRateVnd().getMonth36();
                    } catch (NumberFormatException | NullPointerException e) {
                        e.printStackTrace();
                        return null;
                    }
                default:
                    return null;
            }
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double getMaxValue(int rateType, List<InterestRateByBank> interestRateByBanks){
        List<Double> listValue = new ArrayList<>();
        for (InterestRateByBank interestRateByBank: interestRateByBanks) {
            String value = getValueInterestRate(rateType, interestRateByBank);
            if (value == null) {
                value = "0";
            }
            double dbValue = Double.parseDouble(value);
            listValue.add(dbValue);
        }

        double max = 0;
        for (int i = 0; i < listValue.size(); i++) {
            if (max < listValue.get(i)) {
                max = listValue.get(i);
            }
        }

        return max;
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

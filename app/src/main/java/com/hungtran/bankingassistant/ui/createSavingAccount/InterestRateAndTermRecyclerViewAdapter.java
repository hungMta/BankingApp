package com.hungtran.bankingassistant.ui.createSavingAccount;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.DefaultItemRecyclerView;
import com.hungtran.bankingassistant.model.interestRate.InterestRate;

public class InterestRateAndTermRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private InterestRate mInterestRate;
    private InterestRateAndTermListener interestRateAndTermListener;

    public InterestRateAndTermRecyclerViewAdapter(InterestRate interestRate) {
        this.mInterestRate = interestRate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_default_recyclerview, viewGroup, false);
        return new DefaultItemRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (i) {
            case 0:
                ((DefaultItemRecyclerView) viewHolder).text.setText("Không kì hạn - Lãi suất " + mInterestRate.getUnlimited() + "/năm");
                break;
            case 1:
                ((DefaultItemRecyclerView) viewHolder).text.setText("1 Tháng - Lãi suất " + mInterestRate.getMonth1() + "%/năm");
                break;
            case 2:
                ((DefaultItemRecyclerView) viewHolder).text.setText("2 Tháng - Lãi suất " + mInterestRate.getMonth2() + "%/năm");
                break;
            case 3:
                ((DefaultItemRecyclerView) viewHolder).text.setText("3 Tháng - Lãi suất " + mInterestRate.getMonth3() + "%/năm");
                break;
            case 4:
                ((DefaultItemRecyclerView) viewHolder).text.setText("6 Tháng - Lãi suất " + mInterestRate.getMonth6() + "%/năm");
                break;
            case 5:
                ((DefaultItemRecyclerView) viewHolder).text.setText("9 Tháng - Lãi suất " + mInterestRate.getMonth9() + "%/năm");
                break;
            case 6:
                ((DefaultItemRecyclerView) viewHolder).text.setText("12 Tháng - Lãi suất " + mInterestRate.getMonth12() + "%/năm");
                break;
            case 7:
                ((DefaultItemRecyclerView) viewHolder).text.setText("18 Tháng - Lãi suất " + mInterestRate.getMonth18() + "%/năm");
                break;
            case 8:
                ((DefaultItemRecyclerView) viewHolder).text.setText("24 Tháng - Lãi suất " + mInterestRate.getMonth24() + "/năm");
                break;
            case 9:
                ((DefaultItemRecyclerView) viewHolder).text.setText("36 Tháng - Lãi suất " + mInterestRate.getMonth36() + "/năm");
                break;
        }

        ((DefaultItemRecyclerView) viewHolder).layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interestRateAndTermListener.onItemInterestRateAndTermLicked(mInterestRate, i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mInterestRate == null ? 0 : 10;
    }

    public interface InterestRateAndTermListener {
        void onItemInterestRateAndTermLicked(InterestRate interestRate, int Position);
    }

    public void setInterestRateAndTermListener(InterestRateAndTermListener listener) {
        interestRateAndTermListener = listener;
    }

}

package com.hungtran.bankingassistant.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.bankLocation.BankLocation;
import com.hungtran.bankingassistant.model.bankLocation.BranchLocation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hungtd on 3/13/19.
 */

public class BranchSearchRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BranchLocation> branchLocations;
    private List<BankLocation> bankLocations;
    public OnBranchRecyclerViewApdapterListener mOnBranchRecyclerViewApdapterListener;

    public BranchSearchRecyclerViewAdapter(List<BranchLocation> branchLocations, List<BankLocation> bankLocations) {
        this.branchLocations = branchLocations;
        this.bankLocations = bankLocations;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_branch_reyclerview, viewGroup, false);
        return new BranchSearchItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((BranchSearchItem) viewHolder).mTxtName.setText(getBankName(branchLocations.get(i).getIdBank()) + " - " + branchLocations.get(i).getName());
        ((BranchSearchItem) viewHolder).mTxtAddressDetail.setText(branchLocations.get(i).getAddress());
        ((BranchSearchItem) viewHolder).mTxtDistant.setText(String.valueOf(branchLocations.get(i).getDistance()));
        ((BranchSearchItem) viewHolder).mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBranchRecyclerViewApdapterListener != null) {
                    mOnBranchRecyclerViewApdapterListener.onItemBranchReyclerViewAdapterClicked(branchLocations.get(i));
                }
            }
        });
        ((BranchSearchItem) viewHolder).mTxtDistant.setText("");
    }

    @Override
    public int getItemCount() {
        return branchLocations == null ? 0 : branchLocations.size();
    }

    public void updateAdapter(List<BranchLocation> list, List<BankLocation> bankLocations) {
        this.branchLocations = list;
        this.bankLocations = bankLocations;
        notifyDataSetChanged();
    }

    private String getBankName(int id) {
        for (BankLocation bankLocation : bankLocations) {
            if (bankLocation.getId() == id) {
                return bankLocation.getName();
            }
        }
        return "";
    }

    public class BranchSearchItem extends RecyclerView.ViewHolder {

        @BindView(R.id.card_view)
        CardView mCardView;

        @BindView(R.id.txtName)
        TextView mTxtName;

        @BindView(R.id.txtDistant)
        TextView mTxtDistant;

        @BindView(R.id.addressDetail)
        TextView mTxtAddressDetail;

        public BranchSearchItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnBranchRecyclerViewApdapterListener {
        void onItemBranchReyclerViewAdapterClicked(BranchLocation branchLocation);
    }

    public void setOnBranchRecyclerViewApdapterListener(OnBranchRecyclerViewApdapterListener listener) {
        mOnBranchRecyclerViewApdapterListener = listener;
    }
}

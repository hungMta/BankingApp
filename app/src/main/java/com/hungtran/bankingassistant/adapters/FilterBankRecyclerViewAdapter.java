package com.hungtran.bankingassistant.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.bankLocation.Bank;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterBankRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Bank> bankList;
    private static FilterBankListener mFilterBankListener;

    public FilterBankRecyclerViewAdapter(Context mContext, List<Bank> list) {
        this.mContext = mContext;
        this.bankList = list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_chose_bank_recycler_view, viewGroup, false);
        return new FilterBankItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (i == 0) {
            ((FilterBankItem) viewHolder).mImgBank.setImageBitmap(null);
        } else {
            Picasso.get().load(bankList.get(i).getImage()).into(((FilterBankItem) viewHolder).mImgBank);
        }
        ((FilterBankItem) viewHolder).mTxtBankName.setText(bankList.get(i).getName());
        if (bankList.get(i).isChecked()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((FilterBankItem) viewHolder).mImgBankCheck.setImageDrawable(mContext.getDrawable(R.drawable.ic_check_circle));
            } else {
                ((FilterBankItem) viewHolder).mImgBankCheck.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_check_circle));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((FilterBankItem) viewHolder).mImgBankCheck.setImageDrawable(mContext.getDrawable(R.drawable.ic_check_circle_gray));
            } else {
                ((FilterBankItem) viewHolder).mImgBankCheck.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_check_circle_gray));
            }
        }
        ((FilterBankItem) viewHolder).mLayoutChooseBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFilterBankListener != null) {
                    mFilterBankListener.onChooseBank(bankList.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bankList == null ? 0 : bankList.size();
    }

    public void updateAdapter(List<Bank> list) {
        this.bankList = list;
        notifyDataSetChanged();
    }

    public class FilterBankItem extends RecyclerView.ViewHolder {

        @BindView(R.id.imgBank)
        ImageView mImgBank;

        @BindView(R.id.txtBankName)
        TextView mTxtBankName;

        @BindView(R.id.imgBankCheck)
        ImageView mImgBankCheck;

        @BindView(R.id.layoutChooseBank)
        LinearLayout mLayoutChooseBank;

        public FilterBankItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface FilterBankListener {
        void onChooseBank(Bank bank);
    }

    public static void setFilterBankListener(FilterBankListener listener){
        mFilterBankListener = listener;
    }
}

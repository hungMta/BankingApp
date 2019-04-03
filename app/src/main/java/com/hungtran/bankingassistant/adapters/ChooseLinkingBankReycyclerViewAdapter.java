package com.hungtran.bankingassistant.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.bank.Bank;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseLinkingBankReycyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Bank> mList;
    private ChooseLinkingListener mChooseLinkingListener;

    public ChooseLinkingBankReycyclerViewAdapter(Context mContext, List<Bank> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_choose_linking_bank_recycler_view, viewGroup, false);
        return new ChooseLinkingBankItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ChooseLinkingBankItem) viewHolder).mTxtNameBank.setText(mList.get(i).getName());
        int idBank = mList.get(i).getId_bank();
        switch (idBank) {
            case 1:
                ((ChooseLinkingBankItem) viewHolder).mLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.vcb));
//                ((ChooseLinkingBankItem) viewHolder).mLayout.setBackgroundColor(mContext.getResources().getColor(R.color.colorVCB));
                break;
            case 2:
                ((ChooseLinkingBankItem) viewHolder).mLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.bidv));
//                ((ChooseLinkingBankItem) viewHolder).mLayout.setBackgroundColor(mContext.getResources().getColor(R.color.colorBIDV));
                break;
            case 4:
                ((ChooseLinkingBankItem) viewHolder).mLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.argi));
//                ((ChooseLinkingBankItem) viewHolder).mLayout.setBackgroundColor(mContext.getResources().getColor(R.color.colorAGRI));
                break;
            case 15:
                ((ChooseLinkingBankItem) viewHolder).mLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.viettin));
                break;
            default:
        }

        if (i == mList.size() - 1) {
            ((ChooseLinkingBankItem) viewHolder).mLineFull.setVisibility(View.VISIBLE);
            ((ChooseLinkingBankItem) viewHolder).mLineMargin.setVisibility(View.GONE);
        } else {
            ((ChooseLinkingBankItem) viewHolder).mLineFull.setVisibility(View.GONE);
            ((ChooseLinkingBankItem) viewHolder).mLineMargin.setVisibility(View.VISIBLE);
        }

        ((ChooseLinkingBankItem) viewHolder).mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mChooseLinkingListener != null) {
                    mChooseLinkingListener.onItemClicked(mList.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void update(List<Bank> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public class ChooseLinkingBankItem extends RecyclerView.ViewHolder {

        @BindView(R.id.layout)
        RelativeLayout mLayout;

        @BindView(R.id.imgBankLogo)
        ImageView mLogo;

        @BindView(R.id.txtNameBank)
        TextView mTxtNameBank;

        @BindView(R.id.viewLineFull)
        View mLineFull;

        @BindView(R.id.viewLineMargin)
        View mLineMargin;

        public ChooseLinkingBankItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ChooseLinkingListener {
        void onItemClicked(Bank bank);
    }

    public void setChooseLinkingListener(ChooseLinkingListener listener) {
        mChooseLinkingListener = listener;
    }
}

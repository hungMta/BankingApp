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
import com.hungtran.bankingassistant.model.linkingBank.LinkingBank;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinkingBankRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Bank> mList;
    private LinkingBankListener mLinkingBankListener;


    public LinkingBankRecyclerViewAdapter(Context context, List<Bank> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bank_linking, viewGroup, false);
        return new LinkingBankItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((LinkingBankItem) viewHolder).mTxtNameBank.setText(mList.get(i).getName());
        int idBank = mList.get(i).getId();
        switch (idBank) {
            case 1:
                ((LinkingBankItem) viewHolder).mLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.vcb));
                ((LinkingBankItem) viewHolder).mLayout.setBackgroundColor(mContext.getResources().getColor(R.color.colorVCB));
                break;
            case 2:
                ((LinkingBankItem) viewHolder).mLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.bidv));
                ((LinkingBankItem) viewHolder).mLayout.setBackgroundColor(mContext.getResources().getColor(R.color.colorBIDV));
                break;
            case 4:
                ((LinkingBankItem) viewHolder).mLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.argi));
                ((LinkingBankItem) viewHolder).mLayout.setBackgroundColor(mContext.getResources().getColor(R.color.colorAGRI));
                break;
            case 15:
                ((LinkingBankItem) viewHolder).mLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.viettin));
                ((LinkingBankItem) viewHolder).mLayout.setBackgroundColor(mContext.getResources().getColor(R.color.colorViettin));
                break;
            default:
                break;
        }
        ((LinkingBankItem) viewHolder).mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLinkingBankListener != null){
                    mLinkingBankListener.onLinkingBankItemClick(mList.get(i));
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

    public class LinkingBankItem extends RecyclerView.ViewHolder {

        @BindView(R.id.txtNameBank)
        TextView mTxtNameBank;

        @BindView(R.id.imgBankLogo)
        ImageView mLogo;

        @BindView(R.id.layoutBank)
        RelativeLayout mLayout;

        public LinkingBankItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface LinkingBankListener{
        void onLinkingBankItemClick(Bank bank);
    }

    public void setLinkingBankListener(LinkingBankListener listener){
        mLinkingBankListener = listener;
    }
}

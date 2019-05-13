package com.hungtran.bankingassistant.ui.transactionHistory;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.transactionHistory.TransactionHistory;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.base.SharePreference;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<TransactionHistory> transactionHistories;
    private Context mContext;
    private int idUser = 0;
    private String nummberAccount;
    private  TransactionHistoryAdapterListener transactionHistoryAdapterListener;


    public TransactionHistoryAdapter(Context context, List<TransactionHistory> transactionHistories, String numberAccount) {
        this.transactionHistories = transactionHistories;
        this.nummberAccount = numberAccount;
        idUser = SharePreference.getIntVal(Constant.ID_USER_KEY);
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_transaction_history,
                viewGroup, false);
        return new ItemView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TransactionHistory transactionHistory = transactionHistories.get(i);
        String time = DataHelper.getTimeFormatFromInterval(transactionHistory.getTransactionDate());
        ((ItemView) viewHolder).txtTime.setText(time);
        ((ItemView) viewHolder).txtMessage.setText(transactionHistory.getMessage());

        String money = DataHelper.formatMoney(transactionHistory.getMoney());
        if (transactionHistory.getIdBankAccountSend().equals(nummberAccount)) {
            ((ItemView) viewHolder).txtStatusMoney.setTextColor(Color.RED);
            ((ItemView) viewHolder).txtStatusMoney.setText("-" + money + "");
        } else {
            ((ItemView) viewHolder).txtStatusMoney.setTextColor(mContext.getResources()
                    .getColor(R.color.colorLightPrimary));
            ((ItemView) viewHolder).txtStatusMoney.setText("+" + money + "");
        }

        ((ItemView) viewHolder).layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (transactionHistoryAdapterListener != null) {
                    transactionHistoryAdapterListener.onItemCick(transactionHistories.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionHistories == null ? 0 : transactionHistories.size();
    }

    public void updateApdater(List<TransactionHistory> list) {
        this.transactionHistories = list;
        notifyDataSetChanged();
    }


    public class ItemView extends RecyclerView.ViewHolder {

        @BindView(R.id.txtTime)
        TextView txtTime;

        @BindView(R.id.txtMessage)
        TextView txtMessage;

        @BindView(R.id.txtStatusMoney)
        TextView txtStatusMoney;

        @BindView(R.id.layout)
        RelativeLayout layout;


        public ItemView(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface TransactionHistoryAdapterListener {
        void onItemCick(TransactionHistory transactionHistory);
    }

    public  void setTransactionHistoryAdapterListener(TransactionHistoryAdapterListener listener){
        transactionHistoryAdapterListener = listener;
    }
}

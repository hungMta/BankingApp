package com.hungtran.bankingassistant.ui.myAccountCardList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;
import com.hungtran.bankingassistant.model.respone.DataAccount.SavingAccount;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.DataHelper;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAccountCardListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ATM = 1;
    private static final int TYPE_SAVING = 2;

    private Context mContext;
    private DataAcount dataAcount;
    private MyAccountCardListListener mListener;
    private int mIdBank;

    public MyAccountCardListRecyclerViewAdapter(Context mContext, DataAcount dataAcount, int idBank) {
        this.mContext = mContext;
        this.dataAcount = dataAcount;
        this.mIdBank = idBank;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_ATM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_atm_card_recycler_view, viewGroup, false);
            return new ATMCardItem(view);
        } else if (viewType == TYPE_SAVING) {
            View view2 = LayoutInflater.from(mContext).inflate(R.layout.item_saving_money_note_recyceler_view, viewGroup, false);
            return new SavingMoneyItem(view2);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == TYPE_ATM) {
            ((ATMCardItem) viewHolder).txtNumberAccount.setText(dataAcount.getNumberAccount());
            ((ATMCardItem) viewHolder).txtMoney.setText(DataHelper.formatMoney(Long.parseLong(dataAcount.getAtmMoney())) + " VND");
            ((ATMCardItem) viewHolder).mLayoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onMyAccountCardListItemClicked(dataAcount, i);
                    }
                }
            });

            switch (mIdBank) {
                case Constant
                        .ID_VCB:
                    ((ATMCardItem) viewHolder).mLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.vcb));
                    break;
                case Constant
                        .ID_BIDV:
                    ((ATMCardItem) viewHolder).mLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.bidv));

                    break;
                case Constant
                        .ID_AGRI:
                    ((ATMCardItem) viewHolder).mLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.argi));

                    break;
                case Constant
                        .ID_VIETTIN:
                    ((ATMCardItem) viewHolder).mLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.viettin));

                    break;
                default:
                    break;
            }

        } else {
            ((SavingMoneyItem) viewHolder).txtNumberAccount.setText(dataAcount.getSavingAccountList().get(i - 1).getNumberSaving());
            ((SavingMoneyItem) viewHolder).txtMoney.setText(DataHelper.formatMoney(Long.parseLong(dataAcount.getSavingAccountList().get(i - 1).getSavingMoney())) + " VND");
            ((SavingMoneyItem) viewHolder).txtInterestRate.setText(dataAcount.getSavingAccountList().get(i - 1)
                    .getInterestRate() + mContext.getResources().getString(R.string.interest_rate_per_year)
            );
            ((SavingMoneyItem) viewHolder).txtTerm.setText(dataAcount.getSavingAccountList().get(i - 1)
                    .getTerm() + " " + mContext.getResources().getString(R.string.month)
            );
            ((SavingMoneyItem) viewHolder).txtNumberAccountTitle.setText(mContext.getResources().getString(R.string.saving_money_book) + " (" + (i) + ")");
            ((SavingMoneyItem) viewHolder).mLayoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onMyAccountCardListItemSavingClicked(dataAcount, i, dataAcount.getSavingAccountList().get(i - 1));
                    }
                }
            });

            switch (mIdBank) {
                case Constant
                        .ID_VCB:
                    ((SavingMoneyItem) viewHolder).mLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.vcb));
                    break;
                case Constant
                        .ID_BIDV:
                    ((SavingMoneyItem) viewHolder).mLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.bidv));

                    break;
                case Constant
                        .ID_AGRI:
                    ((SavingMoneyItem) viewHolder).mLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.argi));

                    break;
                case Constant
                        .ID_VIETTIN:
                    ((SavingMoneyItem) viewHolder).mLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.viettin));

                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (dataAcount == null) {
            return 0;
        }
        if (dataAcount.getSavingAccountList() == null) {
            return 1;
        }
        return dataAcount.getSavingAccountList().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ATM;
        } else {
            return TYPE_SAVING;
        }
    }

    public void update(DataAcount dataAcount) {
        this.dataAcount = dataAcount;
        notifyDataSetChanged();
    }

    public class ATMCardItem extends RecyclerView.ViewHolder {

        @BindView(R.id.layoutBank)
        LinearLayout mLayoutItem;

        @BindView(R.id.txtNumberAcount)
        TextView txtNumberAccount;

        @BindView(R.id.txtMoney)
        TextView txtMoney;

        @BindView(R.id.imgBankLogo)
        ImageView mLogo;

        public ATMCardItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class SavingMoneyItem extends RecyclerView.ViewHolder {

        @BindView(R.id.layoutBank)
        LinearLayout mLayoutItem;

        @BindView(R.id.txtNumberAcount)
        TextView txtNumberAccount;

        @BindView(R.id.txtMoney)
        TextView txtMoney;

        @BindView(R.id.txtInterestrate)
        TextView txtInterestRate;

        @BindView(R.id.txtTerm)
        TextView txtTerm;

        @BindView(R.id.txtPaymentAcountTitle)
        TextView txtNumberAccountTitle;

        @BindView(R.id.imgBankLogo)
        ImageView mLogo;

        public SavingMoneyItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface MyAccountCardListListener {
        void onMyAccountCardListItemClicked(DataAcount acount, int position);

        void onMyAccountCardListItemSavingClicked(DataAcount acount, int position, SavingAccount savingAccount);
    }

    public void setMyAccountCardListListener(MyAccountCardListListener listener) {
        mListener = listener;
    }
}

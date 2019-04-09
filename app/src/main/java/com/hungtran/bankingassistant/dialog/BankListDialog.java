package com.hungtran.bankingassistant.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.ChooseLinkingBankReycyclerViewAdapter;
import com.hungtran.bankingassistant.model.bank.Bank;
import com.hungtran.bankingassistant.model.bankLocation.BankLc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BankListDialog extends DialogFragment implements ChooseLinkingBankReycyclerViewAdapter.ChooseLinkingListener {

    private static final String BANKS = "BANKS";
    @BindView(R.id.recylerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.imgClose)
    ImageView mImgClose;

    private List<Bank> banks;
    ChooseLinkingBankReycyclerViewAdapter mApdater;
    BankListDialogListener bankListDialogListener;

    public static BankListDialog newInstance(List<Bank> banks) {
        BankListDialog areaDialog = new BankListDialog();
        Bundle args = new Bundle();
        args.putSerializable(BANKS, (Serializable) banks);
        areaDialog.setArguments(args);
        return areaDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_dialog_bank_list, container, false);
        ButterKnife.bind(this, view);
        banks = (List<Bank>) getArguments().getSerializable(BANKS);
        setupRecyclerView();
        mImgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

    private void setupRecyclerView() {
        mApdater = new ChooseLinkingBankReycyclerViewAdapter(getContext(), banks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mApdater);
        mApdater.setChooseLinkingListener(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onItemClicked(com.hungtran.bankingassistant.model.bank.Bank bank) {
        if (bankListDialogListener != null) {
            bankListDialogListener.onItemBankListDialogClick(bank);
        }
        dismiss();
    }

    public interface BankListDialogListener {
        void onItemBankListDialogClick(Bank bank);
    }

    public void setBankListDialogListener(BankListDialogListener listener) {
        bankListDialogListener = listener;
    }
}

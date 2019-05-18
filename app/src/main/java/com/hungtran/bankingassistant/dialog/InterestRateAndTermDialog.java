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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.interestRate.InterestRate;
import com.hungtran.bankingassistant.ui.createSavingAccount.InterestRateAndTermRecyclerViewAdapter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InterestRateAndTermDialog extends DialogFragment implements InterestRateAndTermRecyclerViewAdapter.InterestRateAndTermListener {
    public static final String INTEREST_RATE = "INTEREST_RATE";
    public static final String INTEREST_RATE_MAP = "INTEREST_RATE_MAP";
    private InterestRate interestRate;
    private InterestRateAndTermRecyclerViewAdapter adapter;
    private InterestRateAndTermDialogListener interestRateAndTermDialogListener;

    @BindView(R.id.recylerArea)
    RecyclerView mRecyclerView;

    @BindView(R.id.imgClose)
    ImageView mImgClose;

    @BindView(R.id.title)
    TextView mTitle;

    private HashMap<String, Double> interestRateMap;

    public static InterestRateAndTermDialog newInstance(InterestRate interestRate) {
        InterestRateAndTermDialog dialog = new InterestRateAndTermDialog();
        Bundle args = new Bundle();
        args.putSerializable(INTEREST_RATE, interestRate);
        dialog.setArguments(args);
        return dialog;
    }

    public static InterestRateAndTermDialog newInstance(HashMap<String, Double> map){
        InterestRateAndTermDialog dialog = new InterestRateAndTermDialog();
        Bundle args = new Bundle();
        args.putSerializable(INTEREST_RATE_MAP, map);
        dialog.setArguments(args);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_dialog_area, container, false);
        ButterKnife.bind(this, view);
        interestRate = (InterestRate) getArguments().getSerializable(INTEREST_RATE);
        interestRateMap = (HashMap<String, Double>) getArguments().getSerializable(INTEREST_RATE_MAP);
        setupRecyclerView();
        mImgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTitle.setText("Chọn kì hạn - lãi suất");
        return view;
    }

    private void setupRecyclerView() {
//        adapter = new InterestRateAndTermRecyclerViewAdapter(interestRate);
        adapter = new InterestRateAndTermRecyclerViewAdapter(interestRateMap);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);
        adapter.setInterestRateAndTermListener(this);
    }

    @Override
    public void onItemInterestRateAndTermLicked(InterestRate interestRate, int Position) {
        if (interestRateAndTermDialogListener != null) {
            interestRateAndTermDialogListener.onInterestRateAndTermDialogDestroy(interestRate, Position);
            dismiss();
        }
        dismiss();
    }

    @Override
    public void onItemInterestRateAndTermLicked(String term, double interest) {
        if (interestRateAndTermDialogListener != null) {
            interestRateAndTermDialogListener.onInterestRateAndTermDialogDestroy(term, interest);
            dismiss();
        }
        dismiss();
    }

    public interface InterestRateAndTermDialogListener {
        void onInterestRateAndTermDialogDestroy(InterestRate interestRate, int position);
        void onInterestRateAndTermDialogDestroy(String term, double interestRate);
    }

    public  void setAreaDialogListener(InterestRateAndTermDialogListener listener) {
        interestRateAndTermDialogListener = listener;
    }
}

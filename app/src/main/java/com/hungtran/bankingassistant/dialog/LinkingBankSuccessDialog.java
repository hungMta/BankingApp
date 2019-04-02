package com.hungtran.bankingassistant.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.area.AreaResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinkingBankSuccessDialog extends DialogFragment {

    @BindView(R.id.txtOk)
    TextView mTxtOk;

    public static LinkingBankSuccessDialog newInstance(){
        LinkingBankSuccessDialog dialog = new LinkingBankSuccessDialog();
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        View view = inflater.inflate(R.layout.dialog_fragment_linking_bank_success, container, false);
        ButterKnife.bind(this, view);
        mTxtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}

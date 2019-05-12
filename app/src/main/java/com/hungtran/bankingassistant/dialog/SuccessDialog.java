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
import com.hungtran.bankingassistant.util.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuccessDialog extends DialogFragment {

    @BindView(R.id.txtOk)
    TextView mTxtOk;

    @BindView(R.id.txtMessage)
    TextView txtMessage;


    SuccessDialogListener mLinkingBankSuccessDialogListener;
    String message;

    public static SuccessDialog newInstance(String message) {
        SuccessDialog dialog = new SuccessDialog();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.MESSAGE, message);
        dialog.setArguments(bundle);
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
                if (mLinkingBankSuccessDialogListener != null) {
                    mLinkingBankSuccessDialogListener.onSuccessDialogOkClicked();
                }
                dismiss();
            }
        });
        message = getArguments().getString(Constant.MESSAGE, "");
        txtMessage.setText(message);
        return view;
    }

    public interface SuccessDialogListener {
        void onSuccessDialogOkClicked();
    }

    public void seSuccessDialogListener(SuccessDialogListener listener) {
        mLinkingBankSuccessDialogListener = listener;
    }
}

package com.hungtran.bankingassistant.ui.pressOTP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.dialog.DialogCommon;
import com.hungtran.bankingassistant.dialog.SuccessDialog;
import com.hungtran.bankingassistant.ui.myAccountCardList.MyAccountCardListPresenter;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OTPAcvitiy extends BaseActivity implements OTPContract.View, SuccessDialog.SuccessDialogListener {

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.layoutProgressBarGray)
    LinearLayout mLayoutProgressBar;

    @BindView(R.id.btnOK)
    Button btnOk;

    @BindView(R.id.edtOTP)
    EditText edtOTP;

    private OTPPresenter mPresenter;
    private int transactionId;
    private static OTPActivityListener otpActivityListener;
    private int typeTranserMoney;

    @Override
    public int getLayoutId() {
        return R.layout.activity_otp;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        transactionId = getIntent().getIntExtra(Constant.TRANSACTION_ID, 0);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPresenter = new OTPPresenter(this, this);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtOTP.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập mã OTP", Toast.LENGTH_SHORT).show();
                }
                int otp = Integer.parseInt(edtOTP.getText().toString());
//                mLayoutProgressBar.setVisibility(View.VISIBLE);
                showDialogProgress();
                mPresenter.submitOTP(transactionId, otp);
            }
        });
        typeTranserMoney = getIntent().getIntExtra(Constant.TYPE_TRANSFER_MONEY, 0);
    }

    @Override
    public void submitOTPSuccess() {
//        showSuccessDialog();
        if (otpActivityListener != null) {
            otpActivityListener.OPTActivitySucess();
        }
        finish();
    }

    @Override
    public void submitOTPFail(String message) {
        DialogCommon dialogCommon = DialogCommon.newInstance(message);
        dialogCommon.show(getSupportFragmentManager(), Constant.DIALOG);
    }

    @Override
    public void hideProgressBar() {
//        mLayoutProgressBar.setVisibility(View.GONE);
        hideDialogProgress();
    }


    private void showSuccessDialog() {
        String message = "";
        switch (typeTranserMoney) {
            case Constant.TRANSFER_ATM_SAVING:
                message = getResources().getString(R.string.create_saving_money_success);
                break;
            case Constant.TRANSFER_ATM_ATM:
                message = getResources().getString(R.string.transfer_money_atm_success);
                break;
            default:
                break;
        }
        SuccessDialog dialog = SuccessDialog.newInstance(message);
        dialog.seSuccessDialogListener(this);
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onSuccessDialogOkClicked() {
        if (otpActivityListener != null) {
            otpActivityListener.OPTActivitySucess();
        }
        finish();
    }

    public interface OTPActivityListener {
        void OPTActivitySucess();
    }

    public static void setOTPActivityListener(OTPActivityListener listener) {
        otpActivityListener = listener;
    }
}

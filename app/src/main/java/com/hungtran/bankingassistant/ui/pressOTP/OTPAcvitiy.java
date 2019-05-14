package com.hungtran.bankingassistant.ui.pressOTP;

import android.content.Intent;
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
import com.hungtran.bankingassistant.model.register.RegisterRequest;
import com.hungtran.bankingassistant.ui.login.LoginActivty;
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
    private boolean isRegister;
    private RegisterRequest registerRequest;

    @Override
    public int getLayoutId() {
        return R.layout.activity_otp;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        transactionId = getIntent().getIntExtra(Constant.TRANSACTION_ID, 0);
        isRegister = getIntent().getBooleanExtra(Constant.REGISTER_ACCOUNT, false);
        registerRequest = (RegisterRequest) getIntent().getSerializableExtra(Constant.REGISTER_REQUEST);
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
                if (isRegister) {
                    registerRequest.setOtp(otp);
                    mPresenter.registerAccount(registerRequest);
                } else {
                    mPresenter.submitOTP(transactionId, otp);
                }
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
        hideDialogProgress();
    }

    @Override
    public void registerSuccess() {
        DialogCommon dialogCommon = DialogCommon.newInstance(getResources().getString(R.string.register_success));
        dialogCommon.show(getSupportFragmentManager(), Constant.DIALOG);
        dialogCommon.setDialogListener(new DialogCommon.DialogCommonListener() {
            @Override
            public void onDialogOkClicked() {
                Intent intent = new Intent(getApplicationContext(), LoginActivty.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void registerFail(String message) {
        DialogCommon dialogCommon = DialogCommon.newInstance(message);
        dialogCommon.show(getSupportFragmentManager(), Constant.DIALOG);
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

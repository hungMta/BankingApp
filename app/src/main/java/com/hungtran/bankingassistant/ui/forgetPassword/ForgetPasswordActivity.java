package com.hungtran.bankingassistant.ui.forgetPassword;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.dialog.DialogCommon;
import com.hungtran.bankingassistant.model.register.RegisterRequest;
import com.hungtran.bankingassistant.ui.changePassword.ChangePasswordAcitvity;
import com.hungtran.bankingassistant.ui.changePassword.ChangePasswordPresenter;
import com.hungtran.bankingassistant.ui.login.LoginActivty;
import com.hungtran.bankingassistant.ui.pressOTP.OTPAcvitiy;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hungtd on 5/15/19.
 */

public class ForgetPasswordActivity extends BaseActivity implements ForgetPasswordContract.View, OTPAcvitiy.OTPActivityListener {
    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    private EditText inputEmail, inputPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;

    @BindView(R.id.btn_ok)
    Button btnOk;

    ForgetPasswordPresenter mPresenter;


    @Override
    public int getLayoutId() {
        return R.layout.activty_forgot_password;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));

        mPresenter = new ForgetPasswordPresenter(this);

    }


    private void submit() {
        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        showDialogProgress();
        mPresenter.getOTP(inputEmail.getText().toString());
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void getOTPSuccess() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword(inputPassword.getText().toString());
        Intent intent = new Intent(this, OTPAcvitiy.class);
        intent.putExtra(Constant.REGISTER_REQUEST, registerRequest);
        intent.putExtra(Constant.OTP_TYPE, Constant.OTP_FORGOT_PASSWORD);
        startActivity(intent);
        OTPAcvitiy.setOTPActivityListener(this);
    }

    @Override
    public void getOTPFail(String message) {
        DialogCommon.newInstance(message).show(getSupportFragmentManager(), Constant.DIALOG);
    }

    @Override
    public void hideProgress() {
        hideDialogProgress();
    }

    @Override
    public void OPTActivitySucess() {
        Intent intent = new Intent(getApplicationContext(), LoginActivty.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }
        }
    }
}

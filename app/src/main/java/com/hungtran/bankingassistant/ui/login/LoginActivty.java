package com.hungtran.bankingassistant.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.user.Account;
import com.hungtran.bankingassistant.ui.main.MainActivity;
import com.hungtran.bankingassistant.ui.transferMoneyATM.TransferMoneySuccessAcitvity;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivty extends BaseActivity implements LoginContract.View {

    @BindView(R.id.edtEmail)
    EditText mEdtEmail;

    @BindView(R.id.edtPassword)
    EditText mEdtPassword;

    @BindView(R.id.btnLogin)
    Button mBtnLogin;

    @BindView(R.id.txtError)
    TextView mTxtError;

    @BindView(R.id.layoutProgressBar)
    LinearLayout mLayoutProgressBar;

    @BindView(R.id.imgPhone)
    ImageView mImgPhone;

    @BindView(R.id.imgKey)
    ImageView mImgKey;

    private LoginPresenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this, this);
        mEdtEmail.setText("0365023120");
        mEdtPassword.setText("123456");

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mLayoutProgressBar.setVisibility(View.VISIBLE);

//                startActivity(new Intent(getApplicationContext(), TransferMoneySuccessAcitvity.class));

                showDialogProgress();
                mTxtError.setVisibility(View.INVISIBLE);
                Account account = new Account();
                account.setUserName(mEdtEmail.getText().toString());
                account.setPassword(mEdtPassword.getText().toString());
                presenter.login(account);
            }
        });

        mImgPhone.setImageDrawable(getResources().getDrawable(R.drawable.ic_phone_accent));
        mImgKey.setImageDrawable(getResources().getDrawable(R.drawable.ic_key));


        mEdtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    mImgPhone.setImageDrawable(getResources().getDrawable(R.drawable.ic_phone));
                    mImgKey.setImageDrawable(getResources().getDrawable(R.drawable.ic_key_accent));
                }
            }
        });

        mEdtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mImgPhone.setImageDrawable(getResources().getDrawable(R.drawable.ic_phone_accent));
                mImgKey.setImageDrawable(getResources().getDrawable(R.drawable.ic_key));
            }
        });
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginError(String message) {
        mTxtError.setText(message);
        mTxtError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
//        mLayoutProgressBar.setVisibility(View.GONE);
        hideDialogProgress();
    }
}

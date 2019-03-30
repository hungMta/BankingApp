package com.hungtran.bankingassistant.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.user.Account;
import com.hungtran.bankingassistant.ui.main.MainActivity;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivty extends BaseActivity implements LoginContract.View {

    @BindView(R.id.edtEmail)
    EditText mEdtEmail;

    @BindView(R.id.edtPassword)
    EditText mEdtPassword;

    @BindView(R.id.btnLogin)
    Button mBtnLogin;

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

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account();
                account.setUserName(mEdtEmail.getText().toString());
                account.setPassword(mEdtPassword.getText().toString());
                presenter.login(account);
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
    public void hideProgressBar() {

    }
}

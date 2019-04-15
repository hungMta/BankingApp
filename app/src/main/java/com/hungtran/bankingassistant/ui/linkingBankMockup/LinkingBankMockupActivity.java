package com.hungtran.bankingassistant.ui.linkingBankMockup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.dialog.LinkingBankSuccessDialog;
import com.hungtran.bankingassistant.model.bank.Bank;
import com.hungtran.bankingassistant.model.linkingBank.LinkingBank;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinkingBankMockupActivity extends BaseActivity implements View.OnClickListener, LinkingBankMockupContract.View {

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.edtATMNumber)
    EditText mEdtAtmNumber;

    @BindView(R.id.edtPassword)
    EditText mEdtPassword;

    @BindView(R.id.txtErrorATM)
    TextView mTxtErrorATM;

    @BindView(R.id.txtErrorPassword)
    TextView mTxtErrorPassword;

    @BindView(R.id.txtErrorPolicy)
    TextView mTxtErrorPolicy;

    @BindView(R.id.checkbox)
    CheckBox mCheckPolicy;

    @BindView(R.id.layoutCheckPolicy)
    LinearLayout mLayoutCheckPolicy;

    @BindView(R.id.btnLinking)
    Button mBtnLinking;

//    @BindView(R.id.layoutProgressBar)
//    LinearLayout mLayoutProgressBar;

    @BindView(R.id.txtTitle)
    TextView mTxtTitle;

    @BindView(R.id.imgBankBg)
    ImageView mImgBankBg;


    private LinkingBankMockupPresenter mPresenter;
    private static LinkingBankMockupListener mLinkingBankMockupListener;

    private Bank mBank;

    @Override
    public int getLayoutId() {
        return R.layout.activity_linking_bank_mockup;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter = new LinkingBankMockupPresenter(this, this);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mLayoutCheckPolicy.setOnClickListener(this);
        mBtnLinking.setOnClickListener(this);
        mBank = (Bank) getIntent().getSerializableExtra(Constant.BANK);
        checkBankTheme();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutCheckPolicy:
                mCheckPolicy.setChecked(!mCheckPolicy.isChecked());
                break;
            case R.id.btnLinking:
                mTxtErrorPolicy.setText("");
                mTxtErrorPassword.setText("");
                mTxtErrorATM.setText("");
                if (validField()) {
//                    mLayoutProgressBar.setVisibility(View.VISIBLE);
                    showDialogProgress();
                    mPresenter.linkingBank(mBank, new LinkingBank(mEdtAtmNumber.getText().toString(),
                            mEdtPassword.getText().toString()));
                }
        }
    }


    private boolean validField() {
        boolean isValid = true;
        if (mEdtAtmNumber.getText().toString().equals("")) {
            isValid = false;
            mTxtErrorATM.setText(getString(R.string.error_atm));
        }

        if (mEdtPassword.getText().toString().equals("")) {
            isValid = false;
            mTxtErrorPassword.setText(getString(R.string.error_password));
        }

        if (!mCheckPolicy.isChecked()) {
            isValid = false;
            mTxtErrorPolicy.setText(getString(R.string.error_policy));
        }

        return isValid;
    }

    @Override
    public void linkingBankSuccess() {
        Toast.makeText(this, "Liên kết thành công", Toast.LENGTH_SHORT).show();
        if (mLinkingBankMockupListener != null) {
            mLinkingBankMockupListener.onLinkingBankSuccess();
        }
        finish();
    }

    @Override
    public void linkingBankError() {
        Toast.makeText(this, "ERRRORR", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgressBar() {
//        mLayoutProgressBar.setVisibility(View.GONE);
        hideDialogProgress();
    }

    private void checkBankTheme() {
        switch (mBank.getId_bank()) {
            case 1:
                mTxtTitle.setBackgroundColor(getResources().getColor(R.color.colorVCB));
                mBtnLinking.setBackgroundColor(getResources().getColor(R.color.colorVCB));
                mImgBankBg.setImageDrawable(getResources().getDrawable(R.drawable.banner_vcb_two));
                break;
            case 2:
                mTxtTitle.setBackgroundColor(getResources().getColor(R.color.colorBIDV));
                mBtnLinking.setBackgroundColor(getResources().getColor(R.color.colorBIDV));
                mImgBankBg.setImageDrawable(getResources().getDrawable(R.drawable.bidv_banner_two));
                break;
            case 4:
                mTxtTitle.setBackgroundColor(getResources().getColor(R.color.colorAGRI));
                mBtnLinking.setBackgroundColor(getResources().getColor(R.color.colorAGRI));
                mImgBankBg.setImageDrawable(getResources().getDrawable(R.drawable.agribank_two));
                break;
            case 15:
                mTxtTitle.setBackgroundColor(getResources().getColor(R.color.colorViettin));
                mBtnLinking.setBackgroundColor(getResources().getColor(R.color.colorViettin));
                mImgBankBg.setImageDrawable(getResources().getDrawable(R.drawable.banner_viettin));
                break;
        }
    }

    public interface LinkingBankMockupListener {
        void onLinkingBankSuccess();
    }

    public static void setLinkingBankMockupListener(LinkingBankMockupListener listener) {
        mLinkingBankMockupListener = listener;
    }
}

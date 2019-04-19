package com.hungtran.bankingassistant.ui.transferMoneyATM;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.dialog.BankListDialog;
import com.hungtran.bankingassistant.model.bank.Bank;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;
import com.hungtran.bankingassistant.ui.pressOTP.OTPAcvitiy;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.CurrencyEditText;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransferMoneyATMActivity extends BaseActivity implements TransferMoneyATMContract.View, View.OnClickListener, BankListDialog.BankListDialogListener, OTPAcvitiy.OTPActivityListener, TransferMoneySuccessAcitvity.TransferMoneySuccessListener {


    private static TransferMoneyActivityListener transferMoneyActivityListener;


    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.edtReceivingAccount)
    EditText mEdtRecevingAccount;

    @BindView(R.id.edtReceivingAccountName)
    EditText mEdtReceivingAcountName;

    @BindView(R.id.edtMoney)
    CurrencyEditText mEdtSendingMoney;

    @BindView(R.id.txtMoney)
    TextView mTxtMoney;

    @BindView(R.id.edtNumberAccount)
    EditText mEdtMyAccount;

    @BindView(R.id.edtContent)
    EditText mEdtContent;

    @BindView(R.id.imgLogo)
    ImageView mLogo;

//    @BindView(R.id.layoutProgressBar)
//    LinearLayout mLayoutProgressBar;

    @BindView(R.id.imgReceivingBank)
    ImageView mImgReceivingBank;

    @BindView(R.id.btnOK)
    Button btnOk;

    @BindView(R.id.edtReceivingBank)
    EditText mEdtReceivingBank;

    private int idReceivingBank;
    private Bank receivingBank;
    private long sendingMoney;
    private String recevingName;
    private String sendingAccount;
    private int mIdBank;
    private DataAcount mDataAcount;
    private TransferMoneyATMPresenter mPresenter;
    private List<Bank> bankList;
    private double myMoney;

    @Override
    public int getLayoutId() {
        return R.layout.activity_transfer_money_atm;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mDataAcount = (DataAcount) getIntent().getSerializableExtra(Constant.DATA_ACCOUNT);
        mIdBank = getIntent().getIntExtra(Constant.ID_BANK, 0);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setupLogo();
        bindData();


        mPresenter = new TransferMoneyATMPresenter(this, this);
//        mLayoutProgressBar.setVisibility(View.VISIBLE);
        showDialogProgress();

        mPresenter.getAvaibleBankLinking();
        mImgReceivingBank.setOnClickListener(this);
        btnOk.setOnClickListener(this);

        myMoney = Double.parseDouble(mDataAcount.getAtmMoney());

        mEdtRecevingAccount.setText("960988807808");
        mEdtReceivingAcountName.setText("NGUYEN VAN THUAN");
    }

    private void bindData() {
        mTxtMoney.setText(DataHelper.formatMoney(Long.parseLong(mDataAcount.getAtmMoney())) + " VND");
        mEdtMyAccount.setText(mDataAcount.getNumberAccount());
    }


    private void setupLogo() {
        switch (mIdBank) {
            case Constant
                    .ID_VCB:
                mLogo.setImageDrawable(getResources().getDrawable(R.drawable.banner_vcb_two));
                break;
            case Constant.ID_BIDV:
                mLogo.setImageDrawable(getResources().getDrawable(R.drawable.bidv_banner_two));
                break;
            case Constant.ID_AGRI:
                mLogo.setImageDrawable(getResources().getDrawable(R.drawable.agribank_two));
                break;
            case Constant.ID_VIETTIN:
                mLogo.setImageDrawable(getResources().getDrawable(R.drawable.banner_viettin));
                break;
        }
    }

    @Override
    public void getAvaibleBankLinkingSuccess(List<Bank> banks) {
        bankList = banks;
    }

    @Override
    public void getAvaibleBankLinkingFail() {

    }

    @Override
    public void hideProgressBar() {
//        mLayoutProgressBar.setVisibility(View.GONE);
        hideDialogProgress();
    }

    @Override
    public void openOTPActivity(int transactionId) {
        Intent intent = new Intent(this, OTPAcvitiy.class);
        intent.putExtra(Constant.TRANSACTION_ID, transactionId);
        intent.putExtra(Constant.TYPE_TRANSFER_MONEY, Constant.TRANSFER_ATM_ATM);
        OTPAcvitiy.setOTPActivityListener(this);
        startActivity(intent);
    }

    @Override
    public void transferMoneyFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgReceivingBank:
                BankListDialog dialog = BankListDialog.newInstance(bankList);
                dialog.show(getSupportFragmentManager(), Constant.DIALOG);
                dialog.setBankListDialogListener(this);
            case R.id.btnOK:
                if (mEdtRecevingAccount.getText().toString() == "") {
                    Toast.makeText(this, "Vui lòng điền tên người hưởng!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mEdtSendingMoney.getText().toString() == "") {
                    Toast.makeText(this, "Vui lòng điền số tiền chuyển!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (receivingBank == null) {
                    Toast.makeText(this, "Vui lòng chọn ngân hàng hưởng", Toast.LENGTH_SHORT).show();
                    return;
                }

                String format = DataHelper.deletAllNonDigit(mEdtSendingMoney.getText().toString());
                sendingMoney = Long.parseLong(format);
                if (sendingMoney > myMoney) {
                    Toast.makeText(this, "Số tiền chuyển phải nhỏ hơn số dư khả dụng!", Toast.LENGTH_SHORT).show();
                    return;
                }

                recevingName = mEdtReceivingAcountName.getText().toString();
//                mLayoutProgressBar.setVisibility(View.VISIBLE);
                showDialogProgress();
                mPresenter.trasnferMoney(mDataAcount, mIdBank, receivingBank.getId_bank(), mEdtRecevingAccount.getText().toString(), mEdtReceivingAcountName.getText().toString(), sendingMoney);
        }
    }

    @Override
    public void onItemBankListDialogClick(Bank bank) {
        receivingBank = bank;
        mEdtReceivingBank.setText(bank.getFull_name());
    }


    /**
     * TRANSFER SUCCEED
     */

    @Override
    public void OPTActivitySucess() {
//        if (transferMoneyActivityListener != null) {
////            transferMoneyActivityListener.transferMoneySuccess();
////        }


        Intent intent = new Intent(this, TransferMoneySuccessAcitvity.class);
        intent.putExtra(Constant.RECEIVER_BANK, receivingBank);
        intent.putExtra(Constant.RECEIVER_NAME, mEdtReceivingAcountName.getText().toString());
        intent.putExtra(Constant.TYPE_TRANSFER_MONEY, Constant.TRANSFER_ATM_ATM);
        intent.putExtra(Constant.TRANSFER_MONEY, sendingMoney);
        intent.putExtra(Constant.RECEIVER_ACCOUNT, mEdtRecevingAccount.getText().toString());
        TransferMoneySuccessAcitvity.setTransferMoneySuccessListener(this);
        startActivity(intent);

//        finish();
    }

    @Override
    public void doOtherTransaction() {
        finish();
    }

    @Override
    protected void onDestroy() {
        TransferMoneySuccessAcitvity.setTransferMoneySuccessListener(null);
        super.onDestroy();
    }


    public interface TransferMoneyActivityListener {
        void transferMoneySuccess();
    }

    public static void setTransferMoneyActivityListener(TransferMoneyActivityListener listener) {
        transferMoneyActivityListener = listener;
    }

}

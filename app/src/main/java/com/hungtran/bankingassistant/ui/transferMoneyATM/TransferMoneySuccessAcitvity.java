package com.hungtran.bankingassistant.ui.transferMoneyATM;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.bank.Bank;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransferMoneySuccessAcitvity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.txtMessage)
    TextView mTxtMessage;

    @BindView(R.id.btnDoOtherTransaction)
    Button mBtnDotherTransaction;

    private long transferMoney;
    private String receiver;
    private String receivingAccount;
    private Bank receivingBank;
    private int typeTransfer;
    private static TransferMoneySuccessListener transferMoneySuccessListener;


    @Override
    public int getLayoutId() {
        return R.layout.activity_transfer_success;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getData();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setTitle(null);
        setupText();
        mBtnDotherTransaction.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.transfer_success_menu_toolbar, menu);
        return true;
    }

    private void setupText() {
        ForegroundColorSpan foregroundPrimary = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        ForegroundColorSpan foregroundBlack = new ForegroundColorSpan(getResources().getColor(R.color.colorBlack));

        SpannableStringBuilder builder = new SpannableStringBuilder();

        SpannableString str1 = new SpannableString(getStringTypeTransfer());
        str1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, str1.length(), 0);
        builder.append(str1);

        String money = DataHelper.formatMoney(transferMoney) + " VND";
        SpannableString strMoney = new SpannableString(money);
        strMoney.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, strMoney.length(), 0);
        builder.append(strMoney);


        SpannableString strFor = new SpannableString(" cho ");
        strFor.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, strFor.length(), 0);
        builder.append(strFor);


        SpannableString strReceiver = new SpannableString(" " + receiver + " ");
        strReceiver.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, strReceiver.length(), 0);
        builder.append(strReceiver);

        SpannableString strReceiverAccount = new SpannableString(" số tài khoản ");
        strReceiverAccount.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, strReceiverAccount.length(), 0);
        builder.append(strReceiverAccount);

        SpannableString receiverAccount = new SpannableString(" " + receivingAccount + " ");
        receiverAccount.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, receiverAccount.length(), 0);
        builder.append(receiverAccount);

        SpannableString strReceiverBank = new SpannableString(" ngân hàng thụ hưởng ");
        strReceiverBank.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, strReceiverBank.length(), 0);
        builder.append(strReceiverBank);

        SpannableString receiverBank = new SpannableString(" " + receivingBank.getFull_name() + " ");
        receiverBank.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, receiverBank.length(), 0);
        builder.append(receiverBank);

        mTxtMessage.setText(builder, TextView.BufferType.SPANNABLE);
    }

    private void getData() {
        typeTransfer = getIntent().getIntExtra(Constant.TYPE_TRANSFER_MONEY, 0);
        transferMoney = getIntent().getLongExtra(Constant.TRANSFER_MONEY, 0);
        receiver = getIntent().getStringExtra(Constant.RECEIVER_NAME);
        receivingBank = (Bank) getIntent().getSerializableExtra(Constant.RECEIVER_BANK);
        receivingAccount = getIntent().getStringExtra(Constant.RECEIVER_ACCOUNT);

//        typeTransfer = Constant.TRANSFER_ATM_ATM;
//        transferMoney = 3000000;
//        receiver = "tran duy hung";
//        receivingAccount = "12321312312";
//        receivingBank = new Bank();
//        receivingBank.setFullname("CONG THUONG VN (VIETTINBANK)");
    }


    private String getStringTypeTransfer() {
        switch (typeTransfer) {
            case Constant.TRANSFER_ATM_ATM:
                return "Quý khách đã thực hiện chuyển thành công ";
        }
        return "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDoOtherTransaction:
                if (transferMoneySuccessListener != null) {
                    transferMoneySuccessListener.doOtherTransaction();
                }
                finish();
        }
    }

    public interface TransferMoneySuccessListener {
        void doOtherTransaction();
    }

    public static void setTransferMoneySuccessListener(TransferMoneySuccessListener listener) {
        transferMoneySuccessListener = listener;
    }

}

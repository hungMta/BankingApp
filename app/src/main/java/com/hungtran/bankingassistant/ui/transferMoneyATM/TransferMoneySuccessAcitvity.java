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
import com.hungtran.bankingassistant.model.respone.DataAccount.SavingAccount;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    private int term;
    private double interestRate;
    private SavingAccount savingAccount;


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
        switch (typeTransfer) {
            case Constant.TRANSFER_ATM_ATM:
                setupTextATM2ATM();
                break;
            case Constant.TRANSFER_ATM_SAVING:
                setupTextCreateSaving();
                break;
            case Constant.TRANSFER_SAVING_ATM:
                setupTextWithdrawMoney();
                break;
            case Constant.TRANSFER_SAVING_SAVING:
                setupTextSaving2Saving();
                break;
        }
        mBtnDotherTransaction.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.transfer_success_menu_toolbar, menu);
        return true;
    }

    private void setupTextATM2ATM() {
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

    private void setupTextCreateSaving() {
        ForegroundColorSpan foregroundPrimary = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        ForegroundColorSpan foregroundBlack = new ForegroundColorSpan(getResources().getColor(R.color.colorBlack));

        SpannableStringBuilder builder = new SpannableStringBuilder();

        SpannableString str1 = new SpannableString("Qúy khách đã tạo sổ tiết kiệm thành công có số tiền ");
        str1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, str1.length(), 0);
        builder.append(str1);

        String money = DataHelper.formatMoney(transferMoney) + " VND";
        SpannableString strMoney = new SpannableString(money);
        strMoney.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, strMoney.length(), 0);
        builder.append(strMoney);


        SpannableString strFor = new SpannableString(" với kỳ hạn ");
        strFor.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, strFor.length(), 0);
        builder.append(strFor);


        SpannableString strReceiver = new SpannableString(" " + term + " tháng, ");
        strReceiver.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, strReceiver.length(), 0);
        builder.append(strReceiver);

        SpannableString strReceiverAccount = new SpannableString("lãi suất");
        strReceiverAccount.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, strReceiverAccount.length(), 0);
        builder.append(strReceiverAccount);

        SpannableString receiverAccount = new SpannableString(" " + interestRate + "%/năm");
        receiverAccount.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, receiverAccount.length(), 0);
        builder.append(receiverAccount);


        mTxtMessage.setText(builder, TextView.BufferType.SPANNABLE);
    }

    private void setupTextWithdrawMoney() {
        ForegroundColorSpan foregroundPrimary = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        ForegroundColorSpan foregroundBlack = new ForegroundColorSpan(getResources().getColor(R.color.colorBlack));

        SpannableStringBuilder builder = new SpannableStringBuilder();

        SpannableString str1 = new SpannableString("Qúy khách vừa rút tiền từ sổ tiết kiệm sang tài khoản thẻ thành công với số tiền ");
        str1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, str1.length(), 0);
        builder.append(str1);

        String money = DataHelper.formatMoney(transferMoney) + " VND";
        SpannableString strMoney = new SpannableString(money);
        strMoney.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, strMoney.length(), 0);
        builder.append(strMoney);

        mTxtMessage.setText(builder, TextView.BufferType.SPANNABLE);
    }

    private void setupTextSaving2Saving() {
        ForegroundColorSpan foregroundPrimary = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        ForegroundColorSpan foregroundBlack = new ForegroundColorSpan(getResources().getColor(R.color.colorBlack));

        SpannableStringBuilder builder = new SpannableStringBuilder();

        SpannableString str1 = new SpannableString("Quý khách vừa chuyển đổi sổ tiết kiệm thành công! Với số tiền ");
        str1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, str1.length(), 0);
        builder.append(str1);


        String totalMoney = getTotalMoney();
        String money = totalMoney + " VND";
        SpannableString strMoney = new SpannableString(money);
        strMoney.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, strMoney.length(), 0);
        builder.append(strMoney);

        SpannableString strTermStr = new SpannableString(" với kỳ hạn ");
        strTermStr.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, strTermStr.length(), 0);
        builder.append(strTermStr);


        SpannableString strTerm = new SpannableString(" " + term + " tháng, ");
        strTerm.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, strTerm.length(), 0);
        builder.append(strTerm);

        SpannableString strInterestRateStr = new SpannableString("lãi suất");
        strInterestRateStr.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, strInterestRateStr.length(), 0);
        builder.append(strInterestRateStr);

        SpannableString strInterestRate = new SpannableString(" " + interestRate + "%/năm");
        strInterestRate.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, strInterestRate.length(), 0);
        builder.append(strInterestRate);


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
        term = getIntent().getIntExtra(Constant.SAVING_TERM, 0);
        interestRate = getIntent().getDoubleExtra(Constant.SAVING_INTEREST_RATE, 0);
        savingAccount = (SavingAccount) getIntent().getSerializableExtra(Constant.SAVING_ACCOUNT);
    }


    private int getTimeSaving(Date createDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);

        Calendar createCal = Calendar.getInstance();
        calendar.setTime(createDate);

        int createMonth = createCal.get(Calendar.MONTH);
        int createYear = createCal.get(Calendar.YEAR);


        int year = currentYear - createYear;
        int month = currentMonth - createMonth + year * 12;

        return month;
    }

    private String getTotalMoney() {
        Date dateSaving = DataHelper.getDateFromString(savingAccount.getCreateDate(), Constant.SAVING_FORMAT_DATE);
        int timeSaving = getTimeSaving(dateSaving);
        List<Long> totalMoneyList = DataHelper.calculateInterestRate(transferMoney, interestRate, term, timeSaving, Constant.TYPE_SAVING_WITHDRAW_AT_END_TERM);
        long totalMoney = 0;
        if (totalMoneyList.size() > 0) {
            totalMoney = totalMoneyList.get(totalMoneyList.size() - 1);
        } else {
            totalMoney = Long.parseLong(savingAccount.getSavingMoney());
        }

        return DataHelper.formatMoney(totalMoney);
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

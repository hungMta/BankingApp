package com.hungtran.bankingassistant.ui.changeSavingMoneyBank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.interestRate.InterestRate;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;
import com.hungtran.bankingassistant.model.respone.DataAccount.SavingAccount;
import com.hungtran.bankingassistant.ui.pressOTP.OTPAcvitiy;
import com.hungtran.bankingassistant.ui.transferMoneyATM.TransferMoneySuccessAcitvity;
import com.hungtran.bankingassistant.ui.withdrawMoney.WithdrawMoneyContract;
import com.hungtran.bankingassistant.ui.withdrawMoney.WithdrawMoneyPresenter;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeforeChangeSavingMoneyBankAcitivity extends BaseActivity implements WithdrawMoneyContract.View, View.OnClickListener, TransferMoneySuccessAcitvity.TransferMoneySuccessListener, OTPAcvitiy.OTPActivityListener {

    private static BeforeChangeSavingListener beforeChangeSavingListener;
    @BindView(R.id.txtMessageWithdraw)
    TextView mTxtMessageWithdraw;

    @BindView(R.id.txtMessage)
    TextView mTxtMessage;

    @BindView(R.id.btnOK)
    Button mBtnOk;

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;


    private DataAcount dataAcount;
    private SavingAccount savingAccount;
    private int idBank;
    private double interest0month;
    private double interesetRate;
    private InterestRate interestRateObj;
    WithdrawMoneyPresenter presenter;
    private static com.hungtran.bankingassistant.ui.withdrawMoney.WithDrawAllMoneyActivity.WithDrawAllMoneyListner withDrawAllMoneyListner;
    private int type = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_with_draw_all_money;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        presenter = new WithdrawMoneyPresenter(this);
        dataAcount = (DataAcount) getIntent().getSerializableExtra(Constant.DATA_ACCOUNT);
        savingAccount = (SavingAccount) getIntent().getSerializableExtra(Constant.SAVING_ACCOUNT);
        idBank = getIntent().getIntExtra(Constant.ID_BANK, 0);
        interestRateObj = (InterestRate) getIntent().getSerializableExtra(Constant.SAVING_INTEREST_RATE);
        type = getIntent().getIntExtra(Constant.TYPE_CHANGE_SAVING, 0);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtnOk.setOnClickListener(this);
        // Quý khách sẽ nhận được tổng số tiền là 13242 vnf. Trong đó 123221 tiền gốc và 12321 tiền lãi
        if (!isDueDate()) {
            mTxtMessageWithdraw.setVisibility(View.VISIBLE);
            interesetRate = Double.parseDouble(interestRateObj.getUnlimited());
            mTxtMessageWithdraw.setText(getResources().getString(R.string.message_withdraw_money) + " " + interesetRate + "%/năm");
        } else {
            mTxtMessageWithdraw.setVisibility(View.GONE);
            interesetRate = Double.parseDouble(savingAccount.getInterestRate());
        }
        long totalMoney = getTotalMoney();
        long interestMoney = getInterestMoney();
        long intialMoney = Long.parseLong(savingAccount.getSavingMoney());

        ForegroundColorSpan foregroundPrimary = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        ForegroundColorSpan foregroundBlack = new ForegroundColorSpan(getResources().getColor(R.color.colorBlack));

        SpannableStringBuilder builder = new SpannableStringBuilder();

        SpannableString str1 = new SpannableString("Sổ tiết kiệm mới sẽ có tổng số tiền là ");
        str1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, str1.length(), 0);
        builder.append(str1);

        String totalMoneystr = DataHelper.formatMoney(totalMoney) + " VND";
        SpannableString strMoney1 = new SpannableString(totalMoneystr);
        strMoney1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, totalMoneystr.length(), 0);
        builder.append(strMoney1);


        SpannableString strFor = new SpannableString(" trong đó ");
        strFor.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, strFor.length(), 0);
        builder.append(strFor);

        String intialMoneystr = DataHelper.formatMoney(intialMoney) + " VND";
        SpannableString strMoney2 = new SpannableString(intialMoneystr);
        strMoney2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, intialMoneystr.length(), 0);
        builder.append(strMoney2);

        SpannableString str2 = new SpannableString(" tiền gốc và ");
        str2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, str2.length(), 0);
        builder.append(str2);

        String interestMoneystr = DataHelper.formatMoney(interestMoney) + " VND";
        SpannableString strMoney3 = new SpannableString(interestMoneystr);
        strMoney3.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, interestMoneystr.length(), 0);
        builder.append(strMoney3);

        SpannableString str3 = new SpannableString(" tiền lãi.");
        str3.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, str3.length(), 0);
        builder.append(str3);


        mTxtMessage.setText(builder, TextView.BufferType.SPANNABLE);
    }

    private long getTotalMoney() {
        long initialMoney = Long.parseLong(savingAccount.getSavingMoney());
        int term = Integer.parseInt(savingAccount.getTerm());
        String createDate = savingAccount.getCreateDate();
        double interestRate = interesetRate;

        long totalMoney = DataHelper.calculateSavingInterestRate(initialMoney, interestRate, term, createDate);
        return totalMoney;

//        List<Long> list = DataHelper.calculateInterestRate(Long.parseLong(savingAccount.getSavingMoney()),
//                interesetRate,
//                Integer.parseInt(savingAccount.getTerm()), 1, Constant.TYPE_SAVING_WITHDRAW_AT_END_TERM);
//        long money = 0;
//        if (list.size() == 0) {
//            money = Long.parseLong(savingAccount.getSavingMoney());
//            long interestMoney = (long) (money * (interesetRate / 100));
//            money += interestMoney;
//            return money;
//        } else {
//            money = list.get(list.size() - 1);
//            money += Long.parseLong(savingAccount.getSavingMoney());
//            return money;
//        }
    }

    private boolean isDueDate() {
        return DataHelper.isDueDateSaving(savingAccount.getCreateDate(), Integer.parseInt(savingAccount.getTerm()));
//        Date createDate = DataHelper.getDateFromString(savingAccount.getCreateDate(), Constant.SAVING_FORMAT_DATE);
//        Calendar calCreateDate = Calendar.getInstance();
//        calCreateDate.setTime(createDate);
//
//        int term = 0;
//        if (savingAccount.getTerm().contains(" ")) {
//            String[] arr = savingAccount.getTerm().split(" ");
//            term = Integer.parseInt(arr[0]);
//        } else {
//            term = Integer.parseInt(savingAccount.getTerm());
//        }
//
//        calCreateDate.add(Calendar.MONTH, term);
//
//        Date currentDate = Calendar.getInstance().getTime();
//        Date dueDate = calCreateDate.getTime();
//        if (dueDate.before(currentDate) || dueDate.equals(currentDate)) {
//            return true;
//        } else {
//            return false;
//        }
    }

    private long getInterestMoney() {
        long totalMoney = getTotalMoney();
        long money = Long.parseLong(savingAccount.getSavingMoney());
        return totalMoney - money;
    }


    @Override
    public void hideProgressBar() {
        hideDialogProgress();
    }

    @Override
    public void openOTPActivity(int transactionId) {
        Intent intent = new Intent(this, OTPAcvitiy.class);
        intent.putExtra(Constant.TRANSACTION_ID, transactionId);
        intent.putExtra(Constant.TYPE_TRANSFER_MONEY, Constant.TRANSFER_SAVING_ATM);
        OTPAcvitiy.setOTPActivityListener(this);
        startActivity(intent);
    }

    @Override
    public void withDrawMoneyFail(String message) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOK:
                if (beforeChangeSavingListener != null) {
                    beforeChangeSavingListener.doChangeSavingMoney();
                }
                finish();
//                showDialogProgress();
//                presenter.withDrawMoney(dataAcount, savingAccount, idBank, Long.parseLong(savingAccount.getSavingMoney()));
                break;
        }
    }

    @Override
    public void doOtherTransaction() {
        if (withDrawAllMoneyListner != null) {
            withDrawAllMoneyListner.WithDrawAllAcvitiyDestroyed();
        }
        finish();
    }

    @Override
    public void OPTActivitySucess() {
        Intent intent = new Intent(this, TransferMoneySuccessAcitvity.class);
        intent.putExtra(Constant.RECEIVER_NAME, dataAcount.getName());
        intent.putExtra(Constant.TYPE_TRANSFER_MONEY, Constant.TRANSFER_SAVING_ATM);
        intent.putExtra(Constant.TRANSFER_MONEY, Long.parseLong(savingAccount.getSavingMoney()));
        intent.putExtra(Constant.SAVING_ACCOUNT, savingAccount);
        intent.putExtra(Constant.WITH_DRAW_ALL, true);
        TransferMoneySuccessAcitvity.setTransferMoneySuccessListener(this);
        startActivity(intent);
    }

    public interface BeforeChangeSavingListener {
        void doChangeSavingMoney();
    }

    public static void setBeforeChangeSavingListener(BeforeChangeSavingListener listner) {
        beforeChangeSavingListener = listner;
    }


}

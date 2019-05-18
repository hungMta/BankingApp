package com.hungtran.bankingassistant.ui.withdrawMoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.interestRate.InterestRate;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;
import com.hungtran.bankingassistant.model.respone.DataAccount.SavingAccount;
import com.hungtran.bankingassistant.ui.pressOTP.OTPAcvitiy;
import com.hungtran.bankingassistant.ui.transferMoneyATM.TransferMoneySuccessAcitvity;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.CurrencyEditText;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WithdrawMoneyActivity extends BaseActivity implements WithdrawMoneyContract.View, OTPAcvitiy.OTPActivityListener, TransferMoneySuccessAcitvity.TransferMoneySuccessListener, View.OnClickListener {


    @BindView(R.id.txtNumberAcount)
    TextView mTxtNumberAccount;

    @BindView(R.id.txtAccountHolder)
    TextView mTxtAccountHoler;

    @BindView(R.id.txtMoney)
    TextView mTxtMoney;

    @BindView(R.id.imgLogo)
    ImageView mLogo;

    @BindView(R.id.txtTerm)
    TextView mTxtTerm;

    @BindView(R.id.txtInterestRate)
    TextView mTxtInterestRate;

    @BindView(R.id.txtCreateDate)
    TextView mTxtCreateDate;

    @BindView(R.id.txtDueDate)
    TextView mTxtDueDate;


    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.txtMessageWithdraw)
    TextView mTxtMessageWithDraw;

    @BindView(R.id.edtMoney)
    CurrencyEditText mEdtMoney;

    @BindView(R.id.btnOK)
    Button mBtnOk;


    private DataAcount dataAcount;
    private SavingAccount savingAccount;
    private InterestRate interestRate;
    private int idBank;
    Date createDate;
    Date dueDate;
    private long withDrawMoney;
    private long totalMoney;

    private WithdrawMoneyPresenter mPresenter;

    private static WithdrawMoneyActivityListener withdrawMoneyActivityListener;



    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw_money;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter = new WithdrawMoneyPresenter(this);
        dataAcount = (DataAcount) getIntent().getSerializableExtra(Constant.DATA_ACCOUNT);
        savingAccount = (SavingAccount) getIntent().getSerializableExtra(Constant.SAVING_ACCOUNT);
        idBank = getIntent().getIntExtra(Constant.ID_BANK, 0);
        interestRate = (InterestRate) getIntent().getSerializableExtra(Constant.SAVING_INTEREST_RATE);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setupData();
        setupLogo();
        mBtnOk.setOnClickListener(this);
    }

    private void setupData() {
        totalMoney = Long.parseLong(savingAccount.getSavingMoney());
        mTxtNumberAccount.setText(savingAccount.getNumberSaving());
        mTxtAccountHoler.setText(dataAcount.getName());
        mTxtMoney.setText(DataHelper.formatMoney(Long.parseLong(savingAccount.getSavingMoney())) + " VND");
        mTxtInterestRate.setText(savingAccount
                .getInterestRate() + getResources().getString(R.string.interest_rate_per_year)
        );
        mTxtTerm.setText(savingAccount
                .getTerm() + " " + getResources().getString(R.string.month)
        );

        createDate = DataHelper.getDateFromString(savingAccount.getCreateDate(), Constant.SAVING_FORMAT_DATE);
        Calendar calCreateDate = Calendar.getInstance();
        calCreateDate.setTime(createDate);
        mTxtCreateDate.setText(calCreateDate.get(Calendar.DAY_OF_MONTH) + "/"
                + (calCreateDate.get(Calendar.MONTH) + 1) + "/"
                + calCreateDate.get(Calendar.YEAR)
        );

        int term = 0;
        if (savingAccount.getTerm().contains(" ")) {
            String[] arr = savingAccount.getTerm().split(" ");
            term = Integer.parseInt(arr[0]);
        } else {
            term = Integer.parseInt(savingAccount.getTerm());
        }

        calCreateDate.add(Calendar.MONTH, term);
        mTxtDueDate.setText(calCreateDate.get(Calendar.DAY_OF_MONTH) + "/"
                + (calCreateDate.get(Calendar.MONTH) + 1) + "/"
                + calCreateDate.get(Calendar.YEAR)
        );

        mTxtMessageWithDraw.setText(getResources().getString(R.string.message_withdraw_money) + " " + interestRate.getUnlimited() + "%/năm");
    }

    private void setupLogo() {
        switch (idBank) {
            case Constant.ID_VCB:
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

    private void checkDueDate(){

    }

    @Override
    public void hideProgressBar() {
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
    public void withDrawMoneyFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OPTActivitySucess() {
        Intent intent = new Intent(this, TransferMoneySuccessAcitvity.class);
//        intent.putExtra(Constant.RECEIVER_BANK, idBank);
        intent.putExtra(Constant.RECEIVER_NAME, dataAcount.getName());
        intent.putExtra(Constant.TYPE_TRANSFER_MONEY, Constant.TRANSFER_SAVING_SAVING);
        intent.putExtra(Constant.TRANSFER_MONEY, withDrawMoney);
        intent.putExtra(Constant.SAVING_ACCOUNT, savingAccount);
        TransferMoneySuccessAcitvity.setTransferMoneySuccessListener(this);
        startActivity(intent);
    }

    @Override
    public void doOtherTransaction() {
        if (withdrawMoneyActivityListener != null) {
            withdrawMoneyActivityListener.withDrawMoneySuccess();
        }
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOK:
                String format = DataHelper.deletAllNonDigit(mEdtMoney.getText().toString());
                withDrawMoney = Long.parseLong(format);
                if (!validateMoney()) {
                    return;
                }
                showDialogProgress();
                mPresenter.withDrawMoney(dataAcount, savingAccount, idBank, withDrawMoney);
                break;
        }
    }

    private boolean validateMoney(){
        if (withDrawMoney > totalMoney) {
            Toast.makeText(this, "Số tiền rút phải nhỏ hơn số tiền trong tài khoản tiết kiệm", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public interface WithdrawMoneyActivityListener {
        void withDrawMoneySuccess();
    }

    public static void setWithdrawMoneyActivityListener(WithdrawMoneyActivityListener listener){
        withdrawMoneyActivityListener = listener;
    }
}

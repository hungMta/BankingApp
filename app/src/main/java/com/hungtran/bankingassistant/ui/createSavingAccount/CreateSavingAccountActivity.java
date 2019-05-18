package com.hungtran.bankingassistant.ui.createSavingAccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.dialog.DialogCommon;
import com.hungtran.bankingassistant.dialog.InterestRateAndTermDialog;
import com.hungtran.bankingassistant.dialog.SuccessDialog;
import com.hungtran.bankingassistant.model.bank.Bank;
import com.hungtran.bankingassistant.model.interestRate.InterestRate;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;
import com.hungtran.bankingassistant.ui.pressOTP.OTPAcvitiy;
import com.hungtran.bankingassistant.ui.transferMoneyATM.TransferMoneySuccessAcitvity;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.CurrencyEditText;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateSavingAccountActivity extends BaseActivity implements CreateSavingAccountContract.View, View.OnClickListener, InterestRateAndTermDialog.InterestRateAndTermDialogListener, SuccessDialog.SuccessDialogListener, OTPAcvitiy.OTPActivityListener, TransferMoneySuccessAcitvity.TransferMoneySuccessListener {

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.imgChooseInterestRate)
    ImageView imgChooseInterestRate;

    @BindView(R.id.edtTerm)
    EditText edtTerm;

    @BindView(R.id.txtMoney)
    TextView mTxtMoney;

    @BindView(R.id.edtSavingMoney)
    CurrencyEditText mEdtSavingMoney;

    @BindView(R.id.btnOK)
    Button btnOk;

    @BindView(R.id.imgLogo)
    ImageView mLogo;

    @BindView(R.id.edtNumberAccount)
    EditText mEdtNumberAccount;

    private DataAcount mDataAcount;
    private int mIdBank;
    private Bank mBank;
    private CreateSavingAccountPresenter mPresenter;
    private InterestRate mInterestRate;
    private InterestRateAndTermDialog interestRateAndTermDialog;
    private double atmMoney;
    private long savingMoney;
    private static CreateSavingAccountActivityListener createSavingAccountActivityListener;
    private double interestRateNumber;
    private int term;

    private HashMap<String, Double> interestRateMap = new HashMap<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_saving_account;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter = new CreateSavingAccountPresenter(this, this);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDataAcount = (DataAcount) getIntent().getSerializableExtra(Constant.DATA_ACCOUNT);
        mIdBank = getIntent().getIntExtra(Constant.ID_BANK, 0);
        mBank = (Bank) getIntent().getSerializableExtra(Constant.BANK);
        mPresenter.getInterestRate(mIdBank);

        showDialogProgress();

        imgChooseInterestRate.setOnClickListener(this);
        edtTerm.setEnabled(false);
        atmMoney = Long.parseLong(mDataAcount.getAtmMoney());
        mTxtMoney.setText(DataHelper.formatMoney(Long.parseLong(mDataAcount.getAtmMoney())) + " VND");
        mEdtNumberAccount.setText(mDataAcount.getNumberAccount());
        btnOk.setOnClickListener(this);
        setupLogo();
    }

    @Override
    public void getInterestRateSuccess(InterestRate interestRate) {
        mInterestRate = interestRate;
        interestRateMap = getMapInterest();
    }

    @Override
    public void getInterestRateFail() {

    }

    @Override
    public void hideProgressBar() {
        hideDialogProgress();
    }

    @Override
    public void openOTPActivity(int transactionId) {
        Intent intent = new Intent(this, OTPAcvitiy.class);
        intent.putExtra(Constant.TRANSACTION_ID, transactionId);
        intent.putExtra(Constant.TYPE_TRANSFER_MONEY, Constant.TRANSFER_ATM_SAVING);
        OTPAcvitiy.setOTPActivityListener(this);
        startActivity(intent);
    }

    @Override
    public void createSavingAccountFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgChooseInterestRate:
                showInterestRateAndTerm();
                break;
            case R.id.btnOK:
                if (isValidMoney()) {
                    showDialogProgress();
                    mPresenter.createSavingAccount(mDataAcount, mIdBank, term, savingMoney, interestRateNumber);
                }
                break;
        }
    }

    private void showInterestRateAndTerm() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            fragmentTransaction.remove(prev);
        }
        fragmentTransaction.addToBackStack(null);

        // Create and show the dialog.
//        InterestRateAndTermDialog dialog = InterestRateAndTermDialog.newInstance(mInterestRate);
        InterestRateAndTermDialog dialog = InterestRateAndTermDialog.newInstance(interestRateMap);
        dialog.show(fragmentTransaction, "dialog");
        dialog.setAreaDialogListener(this);
    }

    @Override
    public void onInterestRateAndTermDialogDestroy(InterestRate interestRate, int position) {
        switch (position) {
            case 0:
                edtTerm.setText("Không kì hạn - Lãi suất " + mInterestRate.getUnlimited() + "%/năm");
                term = 0;
                interestRateNumber = Double.parseDouble(interestRate.getUnlimited());
                break;
            case 1:
                edtTerm.setText("1 Tháng - Lãi suất " + mInterestRate.getMonth1() + "%/năm");
                term = 1;
                interestRateNumber = Double.parseDouble(interestRate.getMonth1());
                break;
            case 2:
                edtTerm.setText("2 Tháng - Lãi suất " + mInterestRate.getMonth2() + "%/năm");
                term = 2;
                interestRateNumber = Double.parseDouble(interestRate.getMonth2());
                break;
            case 3:
                edtTerm.setText("3 Tháng - Lãi suất " + mInterestRate.getMonth3() + "%/năm");
                term = 3;
                interestRateNumber = Double.parseDouble(interestRate.getMonth3());
                break;
            case 4:
                edtTerm.setText("6 Tháng - Lãi suất " + mInterestRate.getMonth6() + "%/năm");
                term = 6;
                interestRateNumber = Double.parseDouble(interestRate.getMonth6());
                break;
            case 5:
                edtTerm.setText("9 Tháng - Lãi suất " + mInterestRate.getMonth9() + "%/năm");
                term = 9;
                interestRateNumber = Double.parseDouble(interestRate.getMonth9());
                break;
            case 6:
                edtTerm.setText("12 Tháng - Lãi suất " + mInterestRate.getMonth12() + "%/năm");
                term = 12;
                interestRateNumber = Double.parseDouble(interestRate.getMonth12());
                break;
            case 7:
                edtTerm.setText("18 Tháng - Lãi suất " + mInterestRate.getMonth18() + "%/năm");
                term = 18;
                interestRateNumber = Double.parseDouble(interestRate.getMonth18());
                break;
            case 8:
                edtTerm.setText("24 Tháng - Lãi suất " + mInterestRate.getMonth24() + "%/năm");
                term = 24;
                interestRateNumber = Double.parseDouble(interestRate.getMonth24());
                break;
            case 9:
                edtTerm.setText("36 Tháng - Lãi suất " + mInterestRate.getMonth36() + "%/năm");
                term = 36;
                interestRateNumber = Double.parseDouble(interestRate.getMonth6());
                break;
        }
    }

    @Override
    public void onInterestRateAndTermDialogDestroy(String term, double interestRate) {
        int _term = Integer.parseInt(term);
        if (_term == 0){
            edtTerm.setText("Không kì hạn - Lãi suất " + mInterestRate.getUnlimited() + "%/năm");
        } else {
            edtTerm.setText(term + " tháng - Lãi suất " + interestRate + "%/năm");
        }
        this.term = _term;
        interestRateNumber = interestRate;
    }

    private boolean isValidMoney() {
        if (mEdtSavingMoney.getText().toString().equals("")) return false;
        String format = DataHelper.deletAllNonDigit(mEdtSavingMoney.getText().toString());
        savingMoney = Long.parseLong(format);

        if (savingMoney > atmMoney) {
            Toast.makeText(this, "Số tiền phải nhỏ hơn số dư khả dụng!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (savingMoney < 3000000) {
            DialogCommon dialogCommon = DialogCommon.newInstance(getResources().getString(R.string.error_saving_money));
            dialogCommon.show(getSupportFragmentManager(), Constant.DIALOG);
            return false;
        }

        if (term == 0 && interestRateNumber == 0) {
            Toast.makeText(this, "Vui lòng chọn kì hạn và lãi suất!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private void showSuccessDialog() {
        SuccessDialog dialog = SuccessDialog.newInstance(getResources().getString(R.string.create_saving_money_success));
        dialog.seSuccessDialogListener(this);
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onSuccessDialogOkClicked() {
        if (createSavingAccountActivityListener != null) {
            createSavingAccountActivityListener.createSavingAccountSuccess();
        }
        finish();
    }

    @Override
    public void OPTActivitySucess() {
        Intent intent = new Intent(this, TransferMoneySuccessAcitvity.class);
        intent.putExtra(Constant.SAVING_TERM, term);
        intent.putExtra(Constant.SAVING_INTEREST_RATE, interestRateNumber);
        intent.putExtra(Constant.TYPE_TRANSFER_MONEY, Constant.TRANSFER_ATM_SAVING);
        long savingLong = (long) savingMoney;
        intent.putExtra(Constant.TRANSFER_MONEY, savingLong);
        TransferMoneySuccessAcitvity.setTransferMoneySuccessListener(this);
        startActivity(intent);
        finish();
    }

    @Override
    public void doOtherTransaction() {
        if (createSavingAccountActivityListener != null) {
            createSavingAccountActivityListener.createSavingAccountSuccess();
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        TransferMoneySuccessAcitvity.setTransferMoneySuccessListener(null);
        super.onDestroy();
    }

    public interface CreateSavingAccountActivityListener {
        void createSavingAccountSuccess();
    }

    public static void setCreateSavingAccountActivityListener(CreateSavingAccountActivityListener listener) {
        createSavingAccountActivityListener = listener;
    }

    private void setupLogo() {
        switch (mIdBank) {
            case 1:
                mLogo.setImageDrawable(getResources().getDrawable(R.drawable.banner_vcb_two));
                break;
            case 2:
                mLogo.setImageDrawable(getResources().getDrawable(R.drawable.bidv_banner_two));
                break;
            case 4:
                mLogo.setImageDrawable(getResources().getDrawable(R.drawable.agribank_two));
                break;
            case 15:
                mLogo.setImageDrawable(getResources().getDrawable(R.drawable.banner_viettin));
                break;
        }
    }


    private HashMap<String, Double> getMapInterest() {
        HashMap<String, Double> data = new HashMap<>();
        if (mInterestRate.getUnlimited() != null) {
            data.put("0", Double.parseDouble(mInterestRate.getUnlimited()));
        }

        if (mInterestRate.getMonth1() != null) {
            data.put("1", Double.parseDouble(mInterestRate.getMonth1()));
        }

        if (mInterestRate.getMonth2() != null) {
            data.put("2", Double.parseDouble(mInterestRate.getMonth2()));
        }

        if (mInterestRate.getMonth3() != null) {
            data.put("3", Double.parseDouble(mInterestRate.getMonth3()));
        }

        if (mInterestRate.getMonth6() != null) {
            data.put("6", Double.parseDouble(mInterestRate.getMonth6()));
        }

        if (mInterestRate.getMonth9() != null) {
            data.put("9", Double.parseDouble(mInterestRate.getMonth9()));
        }

        if (mInterestRate.getMonth12() != null) {
            data.put("12", Double.parseDouble(mInterestRate.getMonth12()));
        }

        if (mInterestRate.getMonth18() != null) {
            data.put("18", Double.parseDouble(mInterestRate.getMonth18()));
        }

        if (mInterestRate.getMonth24() != null) {
            data.put("24", Double.parseDouble(mInterestRate.getMonth24()));
        }

        if (mInterestRate.getMonth36() != null) {
            data.put("36", Double.parseDouble(mInterestRate.getMonth36()));
        }

        return data;
    }

    public interface CreateSavingAccountListener {

    }

    public static void setCreateSavingAccountListener(CreateSavingAccountActivityListener listener) {
        createSavingAccountActivityListener = listener;
    }
}

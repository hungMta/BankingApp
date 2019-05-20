package com.hungtran.bankingassistant.ui.changeSavingMoneyBank;

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
import android.widget.TextView;
import android.widget.Toast;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.dialog.BankListDialog;
import com.hungtran.bankingassistant.dialog.InterestRateAndTermDialog;
import com.hungtran.bankingassistant.model.bank.Bank;
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
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeSavingMoneyBankActivity extends BaseActivity implements View.OnClickListener, BankListDialog.BankListDialogListener, ChangeSavingMoneyBankContract.View, InterestRateAndTermDialog.InterestRateAndTermDialogListener, OTPAcvitiy.OTPActivityListener, TransferMoneySuccessAcitvity.TransferMoneySuccessListener {

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.edtReceivingAccount)
    EditText mEdtRecevingAccount;

    @BindView(R.id.edtReceivingAccountName)
    EditText mEdtReceivingAcountName;


    @BindView(R.id.txtMoney)
    TextView mTxtMoney;

    @BindView(R.id.imgLogo)
    ImageView mLogo;

    @BindView(R.id.imgReceivingBank)
    ImageView mImgReceivingBank;

    @BindView(R.id.btnOK)
    Button btnOk;

    @BindView(R.id.edtReceivingBank)
    EditText mEdtReceivingBank;


    @BindView(R.id.txtNumberAcount)
    TextView mTxtNumberAccount;

    @BindView(R.id.txtAccountHolder)
    TextView mTxtAccountHoler;

    @BindView(R.id.txtTerm)
    TextView mTxtTerm;

    @BindView(R.id.txtInterestRate)
    TextView mTxtInterestRate;

    @BindView(R.id.txtCreateDate)
    TextView mTxtCreateDate;

    @BindView(R.id.txtDueDate)
    TextView mTxtDueDate;

    @BindView(R.id.imgChooseInterestRate)
    ImageView mImgChooseInterestRate;

    @BindView(R.id.edtTerm)
    EditText edtTerm;

    @BindView(R.id.txtMessageWithdraw)
    TextView mTxtMessageWithdraw;


    private int mIdBank;
    private Bank receivingBank;
    private DataAcount dataAcount;
    private SavingAccount savingAccount;
    private InterestRate interestRate;
    Date createDate;
    Date dueDate;
    private double interestRateNumber;
    private int term;
    private List<Bank> bankList;
    private ChangeSavingMoneyBankPresenter mPresenter;
    private static ChangeSavingMoneyBankListener changeSavingMoneyBankListener;
    private HashMap<String, Double> interestRateMap = new HashMap<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_change_saving_mone_bank;
    }


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
        setupData();
        setupLogo();

        btnOk.setOnClickListener(this);
        mImgReceivingBank.setOnClickListener(this);
        mImgChooseInterestRate.setOnClickListener(this);

        mPresenter = new ChangeSavingMoneyBankPresenter(this);
        showDialogProgress();
        mPresenter.getAvaibleBankLinking();
    }

    private void setupData() {
        dataAcount = (DataAcount) getIntent().getSerializableExtra(Constant.DATA_ACCOUNT);
        savingAccount = (SavingAccount) getIntent().getSerializableExtra(Constant.SAVING_ACCOUNT);
        mIdBank = getIntent().getIntExtra(Constant.ID_BANK, 0);
        interestRate = (InterestRate) getIntent().getSerializableExtra(Constant.SAVING_INTEREST_RATE);

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

        mTxtMessageWithdraw.setText(getResources().getString(R.string.message_withdraw_money) + " " + interestRate.getUnlimited() + "%/năm");
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


    private boolean isValidData() {
        if (mEdtRecevingAccount.getText().toString().equals("")) {
            Toast.makeText(this, "Vui lòng điền tên người hưởng!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (mEdtReceivingAcountName.getText().toString().equals("")) {
            Toast.makeText(this, "Vui lòng điền số tiền chuyển!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (receivingBank == null) {
            Toast.makeText(this, "Vui lòng chọn ngân hàng hưởng", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (term == 0 && interestRateNumber == 0) {
            Toast.makeText(this, "Vui lòng chọn kì hạn và lãi suất!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOK:
                if (isValidData()) {
                    showDialogProgress();
                    mPresenter.trasnferMoney(dataAcount, savingAccount, mIdBank,
                            receivingBank.getId_bank(), mEdtRecevingAccount.getText().toString(),
                            mEdtReceivingAcountName.getText().toString(), term, interestRateNumber);
                }
                break;
            case R.id.imgReceivingBank:
                BankListDialog dialog = BankListDialog.newInstance(bankList);
                dialog.show(getSupportFragmentManager(), Constant.DIALOG);
                dialog.setBankListDialogListener(this);
                break;
            case R.id.imgChooseInterestRate:
                if (receivingBank == null) {
                    Toast.makeText(this, "Vui lòng chọn ngân hàng hưởng", Toast.LENGTH_SHORT).show();
                } else {
                    showDialogProgress();
                    mPresenter.getInterestRate(receivingBank.getId_bank());
                }
                break;
        }
    }

    @Override
    public void onItemBankListDialogClick(Bank bank) {
        receivingBank = bank;
        mEdtReceivingBank.setText(bank.getFull_name());
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
    public void changeSavineMoneyBankFail(String message) {

    }

    @Override
    public void getInterestRateSuccess(InterestRate interestRate) {
        this.interestRate = interestRate;
        showInterestRateAndTerm();
    }

    @Override
    public void getInterestRateFail() {

    }

    private void showInterestRateAndTerm() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            fragmentTransaction.remove(prev);
        }
        fragmentTransaction.addToBackStack(null);

        // Create and show the dialog.
        interestRateMap = getMapInterest();
        InterestRateAndTermDialog dialog = InterestRateAndTermDialog.newInstance(interestRateMap);
        dialog.show(fragmentTransaction, "dialog");
        dialog.setAreaDialogListener(this);
    }



    private HashMap<String, Double> getMapInterest() {
        HashMap<String, Double> data = new HashMap<>();
        if (interestRate.getUnlimited() != null) {
            data.put("0", Double.parseDouble(interestRate.getUnlimited()));
        }

        if (interestRate.getMonth1() != null) {
            data.put("1", Double.parseDouble(interestRate.getMonth1()));
        }

        if (interestRate.getMonth2() != null) {
            data.put("2", Double.parseDouble(interestRate.getMonth2()));
        }

        if (interestRate.getMonth3() != null) {
            data.put("3", Double.parseDouble(interestRate.getMonth3()));
        }

        if (interestRate.getMonth6() != null) {
            data.put("6", Double.parseDouble(interestRate.getMonth6()));
        }

        if (interestRate.getMonth9() != null) {
            data.put("9", Double.parseDouble(interestRate.getMonth9()));
        }

        if (interestRate.getMonth12() != null) {
            data.put("12", Double.parseDouble(interestRate.getMonth12()));
        }

        if (interestRate.getMonth18() != null) {
            data.put("18", Double.parseDouble(interestRate.getMonth18()));
        }

        if (interestRate.getMonth24() != null) {
            data.put("24", Double.parseDouble(interestRate.getMonth24()));
        }

        if (interestRate.getMonth36() != null) {
            data.put("36", Double.parseDouble(interestRate.getMonth36()));
        }

        return data;
    }

    @Override
    public void onInterestRateAndTermDialogDestroy(InterestRate interestRate, int position) {
        switch (position) {
            case 0:
                edtTerm.setText("Không kì hạn - Lãi suất " + interestRate.getUnlimited() + "%/năm");
                term = 0;
                interestRateNumber = Double.parseDouble(interestRate.getUnlimited());
                break;
            case 1:
                edtTerm.setText("1 Tháng - Lãi suất " + interestRate.getMonth1() + "%/năm");
                term = 1;
                interestRateNumber = Double.parseDouble(interestRate.getMonth1());
                break;
            case 2:
                edtTerm.setText("2 Tháng - Lãi suất " + interestRate.getMonth2() + "%/năm");
                term = 2;
                interestRateNumber = Double.parseDouble(interestRate.getMonth2());
                break;
            case 3:
                edtTerm.setText("3 Tháng - Lãi suất " + interestRate.getMonth3() + "%/năm");
                term = 3;
                interestRateNumber = Double.parseDouble(interestRate.getMonth3());
                break;
            case 4:
                edtTerm.setText("6 Tháng - Lãi suất " + interestRate.getMonth6() + "%/năm");
                term = 6;
                interestRateNumber = Double.parseDouble(interestRate.getMonth6());
                break;
            case 5:
                edtTerm.setText("9 Tháng - Lãi suất " + interestRate.getMonth9() + "%/năm");
                term = 9;
                interestRateNumber = Double.parseDouble(interestRate.getMonth9());
                break;
            case 6:
                edtTerm.setText("12 Tháng - Lãi suất " + interestRate.getMonth12() + "%/năm");
                term = 12;
                interestRateNumber = Double.parseDouble(interestRate.getMonth12());
                break;
            case 7:
                edtTerm.setText("18 Tháng - Lãi suất " + interestRate.getMonth18() + "%/năm");
                term = 18;
                interestRateNumber = Double.parseDouble(interestRate.getMonth18());
                break;
            case 8:
                edtTerm.setText("24 Tháng - Lãi suất " + interestRate.getMonth24() + "%/năm");
                term = 24;
                interestRateNumber = Double.parseDouble(interestRate.getMonth24());
                break;
            case 9:
                edtTerm.setText("36 Tháng - Lãi suất " + interestRate.getMonth36() + "%/năm");
                term = 36;
                interestRateNumber = Double.parseDouble(interestRate.getMonth6());
                break;
        }
    }

    @Override
    public void onInterestRateAndTermDialogDestroy(String term, double interestRate) {

    }

    @Override
    public void OPTActivitySucess() {
        Intent intent = new Intent(this, TransferMoneySuccessAcitvity.class);
        intent.putExtra(Constant.RECEIVER_BANK, receivingBank);
        intent.putExtra(Constant.RECEIVER_NAME, mEdtReceivingAcountName.getText().toString());
        intent.putExtra(Constant.TYPE_TRANSFER_MONEY, Constant.TRANSFER_SAVING_SAVING);
        intent.putExtra(Constant.SAVING_ACCOUNT, savingAccount);
        intent.putExtra(Constant.RECEIVER_ACCOUNT, mEdtRecevingAccount.getText().toString());

        intent.putExtra(Constant.SAVING_TERM, term);
        intent.putExtra(Constant.SAVING_INTEREST_RATE, interestRateNumber);

        TransferMoneySuccessAcitvity.setTransferMoneySuccessListener(this);
        startActivity(intent);
    }

    @Override
    public void doOtherTransaction() {
        if (changeSavingMoneyBankListener != null) {
            changeSavingMoneyBankListener.onChangeSavingMoneyBankDestroy();
        }
        finish();
    }

    public interface ChangeSavingMoneyBankListener {
        void onChangeSavingMoneyBankDestroy();
    }

    public static void setChangeSavingMoneyBankListener(ChangeSavingMoneyBankListener listener) {
        changeSavingMoneyBankListener = listener;
    }
}

package com.hungtran.bankingassistant.ui.detailAccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.dialog.DialogCommon;
import com.hungtran.bankingassistant.model.interestRate.InterestRate;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;
import com.hungtran.bankingassistant.model.respone.DataAccount.SavingAccount;
import com.hungtran.bankingassistant.ui.changeSavingMoneyBank.BeforeChangeSavingMoneyBankAcitivity;
import com.hungtran.bankingassistant.ui.changeSavingMoneyBank.ChangeSavingMoneyBankActivity;
import com.hungtran.bankingassistant.ui.withdrawMoney.WithDrawAllMoneyActivity;
import com.hungtran.bankingassistant.ui.withdrawMoney.WithdrawMoneyActivity;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailSavingAccountActivity extends BaseActivity implements View.OnClickListener, DetailSavingAccountContract.View, DialogCommon.DialogCommonListener, WithdrawMoneyActivity.WithdrawMoneyActivityListener, ChangeSavingMoneyBankActivity.ChangeSavingMoneyBankListener, WithDrawAllMoneyActivity.WithDrawAllMoneyListner, BeforeChangeSavingMoneyBankAcitivity.BeforeChangeSavingListener {

    @BindView(R.id.txtNumberAcount)
    TextView mTxtNumberAccount;

    @BindView(R.id.txtAccountHolder)
    TextView mTxtAccountHoler;

    @BindView(R.id.txtMoney)
    TextView mTxtMoney;

    @BindView(R.id.imgClose)
    ImageView mImgClose;

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

    @BindView(R.id.layoutChangeSavingBank)
    LinearLayout mLayoutChangeSavingBank;

    @BindView(R.id.layoutWithdraw)
    LinearLayout mLayoutWithdraw;

    @BindView(R.id.layoutWithdrawAll)
    LinearLayout mLayoutWithdrawAll;


    @BindView(R.id.layoutDueDate)
    LinearLayout mLayoutDueDate;

    @BindView(R.id.layoutTransaction)
    LinearLayout mLayoutTransaction;

    @BindView(R.id.layoutWithdrawAllTwo)
    LinearLayout mLayoutWithdrawAll2;

    private DataAcount dataAcount;
    private SavingAccount savingAccount;
    private InterestRate interestRate;
    private int idBank;
    Date createDate;
    Date dueDate;
    private DetailSavingAccountPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_saving_account;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        dataAcount = (DataAcount) getIntent().getSerializableExtra(Constant.DATA_ACCOUNT);
        savingAccount = (SavingAccount) getIntent().getSerializableExtra(Constant.SAVING_ACCOUNT);
        idBank = getIntent().getIntExtra(Constant.ID_BANK, 0);

        mPresenter = new DetailSavingAccountPresenter(this);
        showDialogProgress();
        mPresenter.getInterestRate(idBank);


        setupData();
        setupLogo();

        mLayoutChangeSavingBank.setOnClickListener(this);
        mLayoutWithdraw.setOnClickListener(this);
        mLayoutWithdrawAll.setOnClickListener(this);
        mLayoutWithdrawAll2.setOnClickListener(this);
    }

    private void setupData() {

        mTxtNumberAccount.setText(savingAccount.getNumberSaving());
        mTxtAccountHoler.setText(dataAcount.getName());
        mTxtMoney.setText(DataHelper.formatMoney(Long.parseLong(savingAccount.getSavingMoney())) + " VND");
        mTxtInterestRate.setText(savingAccount
                .getInterestRate() + getResources().getString(R.string.interest_rate_per_year)
        );
        mTxtTerm.setText(savingAccount
                .getTerm() + " " + getResources().getString(R.string.month)
        );
        mImgClose.setOnClickListener(this);

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
        checkDueDate(calCreateDate);
    }

    private void checkDueDate(Calendar dueDateCal) {
        Date currentDate = Calendar.getInstance().getTime();
        Date dueDate = dueDateCal.getTime();
        if (dueDate.before(currentDate) || dueDate.equals(currentDate)) {
            mLayoutTransaction.setVisibility(View.GONE);
            mLayoutDueDate.setVisibility(View.VISIBLE);
        } else {
            mLayoutTransaction.setVisibility(View.VISIBLE);
            mLayoutDueDate.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                finish();
                break;
            case R.id.layoutWithdraw:
//                showDialogWarning();
                Intent intent = new Intent(this, WithdrawMoneyActivity.class);
                intent.putExtra(Constant.SAVING_INTEREST_RATE, interestRate);
                intent.putExtra(Constant.DATA_ACCOUNT, dataAcount);
                intent.putExtra(Constant.SAVING_ACCOUNT, savingAccount);
                intent.putExtra(Constant.ID_BANK, idBank);
                WithdrawMoneyActivity.setWithdrawMoneyActivityListener(this);
                startActivity(intent);
                break;
            case R.id.layoutWithdrawAll:
            case R.id.layoutWithdrawAllTwo:
//                showDialogWarning();
                Intent intent2 = new Intent(this, WithDrawAllMoneyActivity.class);
                intent2.putExtra(Constant.SAVING_INTEREST_RATE, interestRate);
                intent2.putExtra(Constant.DATA_ACCOUNT, dataAcount);
                intent2.putExtra(Constant.SAVING_ACCOUNT, savingAccount);
                intent2.putExtra(Constant.ID_BANK, idBank);
                startActivity(intent2);
                WithDrawAllMoneyActivity.setWithDrawAllMoneyListner(this);
                break;
            case R.id.layoutChangeSavingBank:
                Intent intent4 = new Intent(this, BeforeChangeSavingMoneyBankAcitivity.class);
                intent4.putExtra(Constant.SAVING_INTEREST_RATE, interestRate);
                intent4.putExtra(Constant.DATA_ACCOUNT, dataAcount);
                intent4.putExtra(Constant.SAVING_ACCOUNT, savingAccount);
                intent4.putExtra(Constant.ID_BANK, idBank);
                startActivity(intent4);
                BeforeChangeSavingMoneyBankAcitivity.setBeforeChangeSavingListener(this);

                break;

        }
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

    @Override
    public void getInterestRateSuccess(InterestRate interestRate) {
        Log.d(TAG, "getInterestRateSuccess: " + interestRate);
        this.interestRate = interestRate;
    }

    @Override
    public void getInterestRateFail(String message) {

    }

    @Override
    public void hideProgressBar() {
        hideDialogProgress();
    }

    private void showDialogWarning() {
        String message = "Sổ tiết kiệm chưa đến ngày đáo hạn nên số tiền rút sẽ được tính theo lãi suất không kỳ hạn ";
        DialogCommon dialogCommon = DialogCommon.newInstance(message);
        dialogCommon.show(getSupportFragmentManager(), Constant.DIALOG);
        dialogCommon.setDialogListener(this);
    }

    @Override
    public void onDialogOkClicked() {

    }

    @Override
    public void withDrawMoneySuccess() {
        finish();
    }

    @Override
    public void onChangeSavingMoneyBankDestroy() {
        finish();
    }

    @Override
    public void WithDrawAllAcvitiyDestroyed() {
        finish();
    }

    @Override
    public void doChangeSavingMoney() {
        Intent intent1 = new Intent(this, ChangeSavingMoneyBankActivity.class);
        intent1.putExtra(Constant.SAVING_INTEREST_RATE, interestRate);
        intent1.putExtra(Constant.DATA_ACCOUNT, dataAcount);
        intent1.putExtra(Constant.SAVING_ACCOUNT, savingAccount);
        intent1.putExtra(Constant.ID_BANK, idBank);
        ChangeSavingMoneyBankActivity.setChangeSavingMoneyBankListener(this);
        startActivity(intent1);
    }
}

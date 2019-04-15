package com.hungtran.bankingassistant.ui.detailAccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;
import com.hungtran.bankingassistant.model.respone.DataAccount.SavingAccount;
import com.hungtran.bankingassistant.ui.createSavingAccount.CreateSavingAccountActivity;
import com.hungtran.bankingassistant.ui.transferMoneyATM.TransferMoneyATMActivity;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailSavingAccountActivity extends BaseActivity implements View.OnClickListener {

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

    private DataAcount dataAcount;
    private SavingAccount savingAccount;
    private int idBank;
    Date createDate;
    Date dueDate;

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
                + calCreateDate.get(Calendar.MONTH) + "/"
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
                + calCreateDate.get(Calendar.MONTH) + "/"
                + calCreateDate.get(Calendar.YEAR)
        );

        setupLogo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                finish();
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
}

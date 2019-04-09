package com.hungtran.bankingassistant.ui.detailAccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAcount;
import com.hungtran.bankingassistant.ui.createSavingAccount.CreateSavingAccountActivity;
import com.hungtran.bankingassistant.ui.transferMoneyATM.TransferMoneyATMActivity;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.DataHelper;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailATMAccountActivity extends BaseActivity implements View.OnClickListener, CreateSavingAccountActivity.CreateSavingAccountActivityListener, TransferMoneyATMActivity.TransferMoneyActivityListener {

    private DataAcount dataAcount;
    private int idBank;

    @BindView(R.id.txtNumberAcount)
    TextView mTxtNumberAccount;

    @BindView(R.id.txtAccountHolder)
    TextView mTxtAccountHoler;

    @BindView(R.id.txtMoney)
    TextView mTxtMoney;

    @BindView(R.id.imgClose)
    ImageView mImgClose;

    @BindView(R.id.layoutCreateSavingAccount)
    LinearLayout mLayoutCreateSavingAccount;

    @BindView(R.id.imgLogo)
    ImageView mLogo;

    @BindView(R.id.layoutTransfer)
    LinearLayout mLayoutTranser;

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_atm_account;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        dataAcount = (DataAcount) getIntent().getSerializableExtra(Constant.DATA_ACCOUNT);
        idBank = getIntent().getIntExtra(Constant.ID_BANK, 0);
        mTxtNumberAccount.setText(dataAcount.getNumberAccount());
        mTxtAccountHoler.setText(dataAcount.getName());
        mTxtMoney.setText(DataHelper.formatMoney(Long.parseLong(dataAcount.getAtmMoney())) + " VND");

        mImgClose.setOnClickListener(this);
        mLayoutCreateSavingAccount.setOnClickListener(this);
        mLayoutTranser.setOnClickListener(this);
        setupLogo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                finish();
                break;
            case R.id.layoutCreateSavingAccount:
                Intent intent = new Intent(this, CreateSavingAccountActivity.class);
                CreateSavingAccountActivity.setCreateSavingAccountActivityListener(this);
                intent.putExtra(Constant.DATA_ACCOUNT, dataAcount);
                intent.putExtra(Constant.ID_BANK, idBank);
                startActivity(intent);
                break;
            case R.id.layoutTransfer:
                Intent intent2 = new Intent(this, TransferMoneyATMActivity.class);
                TransferMoneyATMActivity.setTransferMoneyActivityListener(this);
                intent2.putExtra(Constant.DATA_ACCOUNT, dataAcount);
                intent2.putExtra(Constant.ID_BANK, idBank);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void createSavingAccountSuccess() {
        finish();
    }

    private void setupLogo() {
        switch (idBank) {
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

    @Override
    public void transferMoneySuccess() {
        finish();
    }
}

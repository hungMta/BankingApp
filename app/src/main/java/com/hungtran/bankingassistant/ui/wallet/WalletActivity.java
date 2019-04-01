package com.hungtran.bankingassistant.ui.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.ui.linkingBankMockup.LinkingBankMockupActivity;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.layoutAddBank)
    LinearLayout mLayoutAddBank;

    private static OnWalletActivityListener mOnWalletActivityListener;

    @Override
    public int getLayoutId() {
        return R.layout.activty_wallet;
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
        mLayoutAddBank.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mOnWalletActivityListener != null) {
            mOnWalletActivityListener.onWalletActivtyDestroy();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutAddBank:
                Intent intent = new Intent(this, LinkingBankMockupActivity.class);
                startActivity(intent);
                break;
        }
    }

    public interface OnWalletActivityListener {
        void onWalletActivtyDestroy();
    }

    public static void setOnWalletActivityListener(OnWalletActivityListener listener) {
        mOnWalletActivityListener = listener;
    }
}

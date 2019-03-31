package com.hungtran.bankingassistant.ui.wallet;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.util.base.BaseActivity;

public class WalletActivity extends BaseActivity {
    private static OnWalletActivityListener mOnWalletActivityListener;

    @Override
    public int getLayoutId() {
        return R.layout.activty_wallet;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mOnWalletActivityListener != null) {
            mOnWalletActivityListener.onWalletActivtyDestroy();
        }
    }

    public interface OnWalletActivityListener {
        void onWalletActivtyDestroy();
    }

    public static void setOnWalletActivityListener(OnWalletActivityListener listener) {
        mOnWalletActivityListener = listener;
    }
}

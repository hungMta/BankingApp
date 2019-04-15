package com.hungtran.bankingassistant.util.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hungtran.bankingassistant.dialog.DialogProgress;
import com.hungtran.bankingassistant.util.Constant;


/**
 * Created by hungtd on 2/18/19.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static String TAG = "HUNGTD";
    DialogProgress dialogProgress = DialogProgress.newInstance();

    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    public void showDialogProgress() {
        hideDialogProgress();
        dialogProgress.show(getSupportFragmentManager(), Constant.PROGRESS);
    }

    public void hideDialogProgress() {
        if (dialogProgress != null
                && dialogProgress.getDialog() != null
                && dialogProgress.getDialog().isShowing()) {
            dialogProgress.dismiss();
        }
    }
}

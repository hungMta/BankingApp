package com.hungtran.bankingassistant.ui.changePassword;

/**
 * Created by hungtd on 5/14/19.
 */

public class ChangePasswordPresenter implements ChangePaswordContract.Presenter {

    ChangePaswordContract.View mView;

    public ChangePasswordPresenter(ChangePaswordContract.View view){
        this.mView = view;
    }

    @Override
    public void getOTP() {

    }
}

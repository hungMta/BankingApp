package com.hungtran.bankingassistant.ui.login;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.respone.MyError;
import com.hungtran.bankingassistant.model.user.Account;
import com.hungtran.bankingassistant.model.user.AccountRequest;
import com.hungtran.bankingassistant.model.user.AccountResponse;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.SharePreference;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Headers;
import okhttp3.Response;
import retrofit2.http.Header;

public class LoginPresenter implements LoginContract.Presenter {
    private final String TAG = "hungtd";

    private LoginContract.View mView;
    private Context mContext;

    public LoginPresenter(Context context, LoginContract.View view) {
        this.mContext = context;
        this.mView = view;
    }

    @Override
    public void login(Account account) {
        AccountRequest accountRequest = new AccountRequest(account);
        loginObserverable(accountRequest).subscribeWith(loginObserver());
    }

    private Observable<retrofit2.Response<AccountResponse>> loginObserverable(AccountRequest accountRequest) {
        return ServiceGenerator.resquest().login(accountRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<retrofit2.Response<AccountResponse>> loginObserver() {
        return new DisposableObserver<retrofit2.Response<AccountResponse>>() {
            @Override
            public void onNext(retrofit2.Response<AccountResponse> response) {
                Headers headers = response.headers();
                if (response.code() == 200) {
                    String token = headers.get("authentication");
                    SharePreference.setVal(Constant.TOKEN_KEY, token);
                    AccountResponse accountResponse = response.body();
                    if (accountResponse != null && accountResponse.getUser() != null) {
                        SharePreference.setVal(Constant.ID_USER_KEY, accountResponse.getUser().getId());
                        SharePreference.setVal(Constant.USER_KEY, accountResponse.getUser());
                    }
                    Log.d(TAG, "onNext: header : " + headers);
                    mView.loginSuccess();
                } else if (response.code() == 401) {
                    String responseError = null;
                    try {
                        responseError = response.errorBody().string();
                        Gson gson = new Gson();
                        MyError error = gson.fromJson(responseError, MyError.class);
                        mView.loginError(mContext.getResources().getString(R.string.error_login));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
                mView.hideProgressBar();
            }

            @Override
            public void onComplete() {
                mView.hideProgressBar();
            }
        };
    }
}

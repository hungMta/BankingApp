package com.hungtran.bankingassistant.ui.register;

import android.util.Log;

import com.google.gson.Gson;
import com.hungtran.bankingassistant.model.base.BaseResponse;
import com.hungtran.bankingassistant.model.error.AppError;
import com.hungtran.bankingassistant.model.error.AppErrors;
import com.hungtran.bankingassistant.model.respone.DataAccount.DataAccountRespone;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.SharePreference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by hungtd on 5/14/19.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    RegisterContract.View mView;

    public RegisterPresenter(RegisterContract.View view) {
        this.mView = view;
    }

    @Override
    public void verifyEmail(String email) {
        verifyObservable(email).subscribeWith(verifyAccountObserver());
    }

    @Override
    public void register() {

    }


    private Observable<BaseResponse> verifyObservable(String email) {
        return ServiceGenerator.resquest().verifyEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<BaseResponse> verifyAccountObserver() {
        return new DisposableObserver<BaseResponse>() {
            @Override
            public void onNext(BaseResponse response) {
                Log.d("hungtd", "onNext:  successs");
                mView.verifyEmailSuccess();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("hungtd", "onNext:  error " + e.getMessage());

                ResponseBody responseBody = ((HttpException) e).response().errorBody();
                Gson gson = new Gson();
                assert responseBody != null;
                AppErrors appErrors = null;
                try {
                    appErrors = gson.fromJson(responseBody.string(), AppErrors.class);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                List<String> listError = new ArrayList<>();

                if (appErrors != null && appErrors.getErrors() != null) {
                    for (AppError error : appErrors.getErrors()
                            ) {
                        listError.add(AppError.mapError(error.getCode()));
                    }
                }

                if (listError.size() == 0) {
                    listError.add(Constant.ERROR_UNKNOWN);
                }

                mView.verifyEmailFail(listError.get(0));
                mView.hideProgressBar();
            }

            @Override
            public void onComplete() {
                Log.d("hungtd", "onComplete: ");
                mView.hideProgressBar();
            }
        };
    }
}

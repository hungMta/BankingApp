package com.hungtran.bankingassistant.ui.maket.interestRate.personal;

import android.content.Context;
import android.util.Log;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.interestRate.InterestRateResponse;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hungtd on 3/11/19.
 */

public class PersonalInterestRatePresenter implements PersonalInterestRateContract.Presenter {

    private static String TAG = "HUNGTD";
    private PersonalInterestRateContract.View mView;


    public PersonalInterestRatePresenter(PersonalInterestRateContract.View view) {
        this.mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void getPersonalInterestRate() {
        getInterestRateObserverable().subscribeWith(getInterestRateObserver());
    }

    @Override
    public void getRateTypeFromString(Context context, int id, String timeDeposit) {
        int type = Constant.TYPE_MONTH_0_RATE;
        if (timeDeposit.equals(context.getString(R.string.unlimited))) {
            type = Constant.TYPE_MONTH_0_RATE;
        } else if (timeDeposit.equals(context.getString(R.string.one_month))) {
            type = Constant.TYPE_MONTH_1_RATE;
        } else if (timeDeposit.equals(context.getString(R.string.two_month))) {
            type = Constant.TYPE_MONTH_2_RATE;
        } else if (timeDeposit.equals(context.getString(R.string.three_month))) {
            type = Constant.TYPE_MONTH_3_RATE;
        } else if (timeDeposit.equals(context.getString(R.string.six_month))) {
            type = Constant.TYPE_MONTH_6_RATE;
        } else if (timeDeposit.equals(context.getString(R.string.nine_month))) {
            type = Constant.TYPE_MONTH_9_RATE;
        } else if (timeDeposit.equals(context.getString(R.string.twelve_month))) {
            type = Constant.TYPE_MONTH_12_RATE;
        } else if (timeDeposit.equals(context.getString(R.string.eight_teen_month))) {
            type = Constant.TYPE_MONTH_18_RATE;
        } else if (timeDeposit.equals(context.getString(R.string.twenty_four_month))) {
            type = Constant.TYPE_MONTH_24_RATE;
        } else if (timeDeposit.equals(context.getString(R.string.thirty_six_month))) {
            type = Constant.TYPE_MONTH_36_RATE;
        }
        mView.onGetRateType(id, type);
    }

    @Override
    public String getStringFromRateType(Context context, int rateType) {
        switch (rateType) {
            case 0:
                return context.getResources().getString(R.string.unlimited_acronym);
            case 1:
                return context.getResources().getString(R.string.one_month);
            case 2:
                return context.getResources().getString(R.string.two_month);
            case 3:
                return context.getResources().getString(R.string.three_month);
            case 6:
                return context.getResources().getString(R.string.six_month);
            case 9:
                return context.getResources().getString(R.string.nine_month);
            case 12:
                return context.getResources().getString(R.string.twelve_month);
            case 24:
                return context.getResources().getString(R.string.twenty_four_month);
            case 36:
                return context.getResources().getString(R.string.thirty_six_month);
            default:
                return null;
        }
    }

    private Observable<InterestRateResponse> getInterestRateObserverable() {
        return ServiceGenerator.resquest()
                .getInterestRate(Constant.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<InterestRateResponse> getInterestRateObserver() {
        return new DisposableObserver<InterestRateResponse>() {
            @Override
            public void onNext(InterestRateResponse interestRateResponse) {
                Log.d(TAG, "onNext: " + interestRateResponse);
                mView.onGetPersonalInterestRateSuccess(interestRateResponse);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }
}

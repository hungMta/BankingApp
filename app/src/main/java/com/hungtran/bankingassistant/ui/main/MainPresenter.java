package com.hungtran.bankingassistant.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.hungtran.bankingassistant.model.base.BaseResponse;
import com.hungtran.bankingassistant.model.firebase.FCMToken;
import com.hungtran.bankingassistant.model.firebase.FCMTokenRequest;
import com.hungtran.bankingassistant.network.ServiceGenerator;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.SharePreference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    private Context mContext;
    private MainContract.View mView;

    public MainPresenter(Context context, MainContract.View view) {
        this.mContext = context;
        this.mView = view;
    }

    @Override
    public void submitFirebaseToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("FCM token", "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        // Log and toast
                        Log.d("FCM token ", token);
                        FCMToken fcmToken = new FCMToken(token);
                        FCMTokenRequest fcmTokenRequest = new FCMTokenRequest(fcmToken);
                        submitFirebaseTokenObservable(fcmTokenRequest).subscribeWith(submitFirebaseTokenObserver());
                    }
                });
    }


    private Observable<BaseResponse> submitFirebaseTokenObservable(FCMTokenRequest fcmTokenRequest) {
        return ServiceGenerator.resquest().postFCMToken(SharePreference.getStringVal(Constant.TOKEN_KEY), fcmTokenRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<BaseResponse> submitFirebaseTokenObserver(){
        return new DisposableObserver<BaseResponse>() {
            @Override
            public void onNext(BaseResponse response) {
                Log.d("fcm", "onNext: fcm successs");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("fcm", "onNext: fcm error " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("fcm", "onComplete: ");
            }
        };
    }
}

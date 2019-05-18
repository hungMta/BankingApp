package com.hungtran.bankingassistant.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.noti.NotiModel;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.DataHelper;

import java.util.List;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("FCM token", s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
//        Toast.makeText(getApplicationContext(),"onMessageReceived", Toast.LENGTH_SHORT).show();
        Log.d("fcm", "onMessageReceived: ");
        if (isAppIsInBackground(getApplicationContext())) {
            return;
        }

        Map<String, String> map = remoteMessage.getData();
        String body = map.get("body");
        String title = map.get("title");
        String message = "";
        try {
            Gson gson = new Gson();
            NotiModel notiModel = gson.fromJson(body, NotiModel.class);
            if (notiModel.getType() == Constant.NOTI_SAVING_WITH_DRAW) {
                message = "Ngân hàng " + notiModel.getNameBank() +
                        " thống báo: sổ tiết kiệm " +
                        notiModel.getSavingId() + "đã đến ngày đáo hạn. Qúy khách vui lòng tất toán sổ.";
            } else if (notiModel.getType() == Constant.NOTI_RECEIVE_MONEY) {
                message = "Ngân hàng " + notiModel.getNameBankReceive() + " thông báo bạn vừa nhận được "
                        + DataHelper.formatMoney(notiModel.getMoney()) + "VND từ "
                        + notiModel.getNotiReceverModel().getName() + " Với lời nhắn: " + notiModel.getMessage();

            }
        } catch (Exception e) {

        }

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setChannelId("CHANNEL_ID")
                .build();
        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        manager.notify(123, notification);
    }

    private boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }


}

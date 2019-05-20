package com.hungtran.bankingassistant.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
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
                title = "Thông báo sổ tiết kiệm";
                message = "Ngân hàng " + notiModel.getNameBank() +
                        " thông báo: sổ tiết kiệm " +
                        notiModel.getSavingId() + "đã đến ngày đáo hạn. Qúy khách vui lòng tất toán sổ.";
            } else if (notiModel.getType() == Constant.NOTI_RECEIVE_MONEY) {
                title = "Thông báo nhận tiền";
                message = "Ngân hàng " + notiModel.getNameBankReceive() + " thông báo bạn vừa nhận được "
                        + DataHelper.formatMoney(notiModel.getMoney()) + "VND từ "
                        + notiModel.getNotiReceverModel().getName() + " Với lời nhắn: " + notiModel.getMessage();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_app)
                .setChannelId("123")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message))
                .build();
        createNotificationChannel(notification);
//        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
//        manager.notify(123, notification);
    }

    private void createNotificationChannel(Notification notification) {
        int id = (int)(Math.random() * 50 + 1);
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Banking";
            String description = "CrossEBanking";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("123", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            notificationManager.notify(id, notification);
        }  else {
            NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
            manager.notify(id, notification);
        }
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

package com.example.myapplication.UI;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication.R;

public class DateNotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String dateType = intent.getStringExtra("dateType");
        String contentTitle = intent.getStringExtra("entityType") + " " + dateType +  " Notification";
        String contentText = intent.getStringExtra("instanceName")  + " " +  dateType + " is today!";
        NotificationCompat.Builder build = new NotificationCompat.Builder(context, "notifyDate")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify((int) (System.currentTimeMillis()/1000),build.build());
    }
}

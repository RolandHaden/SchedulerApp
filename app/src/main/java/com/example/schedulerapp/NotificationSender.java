package com.example.schedulerapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.example.schedulerapp.R;

public class NotificationSender {

    private static final String CHANNEL_ID = "schedulerChannel";
    private static final String CHANNEL_NAME = "SchedulerNotifications";

    public static void sendNotification(Context context, String title, String description) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(context, CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(description)
                    .setSmallIcon(R.drawable.baseline_check_24);
        }
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }
}
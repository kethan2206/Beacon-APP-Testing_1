package com.example.testing2.utilities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.testing2.R;
import com.example.testing2.BeaconMonitoringService;

public class NotificationHelper {

    //constants
    public static final int NOTIFICATION_ID = 1;

    //Actions
    public static final String ACTION_STOP_SERVICE = "testing2.stop_service";

    public static Notification createNotification(Context cx) {
        //Create Variables
        String channelId = "com.example.testing2";
        String channelName = "Custom Background Notification Channel";

        // Create Channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId,
                    channelName, android.app.NotificationManager.IMPORTANCE_NONE);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            // Set Channel
            android.app.NotificationManager manager = (android.app.NotificationManager)
                    cx.getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.createNotificationChannel(notificationChannel);

            }
        }

        // Build Notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(cx,
                channelId);

        // Set Notification Options
        notificationBuilder.setContentTitle("YenVeez Monitoring Active")
                .setSmallIcon(R.drawable.yenveez_logo)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setOngoing(true);

        // Stop Service Button
        Intent stopIntent = new Intent(cx, BeaconMonitoringService.class);
        stopIntent.setAction(ACTION_STOP_SERVICE);
        PendingIntent stopPendingIntent = PendingIntent.getService(cx,
                0, stopIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Action stopServiceAction = new NotificationCompat.Action(
                R.drawable.ic_stop,
                "Stop Service",
                stopPendingIntent
        );

        notificationBuilder.addAction(stopServiceAction);

        // Build & Return Notification
        return notificationBuilder.build();
    }

}

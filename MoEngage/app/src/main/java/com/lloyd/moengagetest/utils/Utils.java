package com.lloyd.moengagetest.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.lloyd.moengagetest.R;
import com.lloyd.moengagetest.activities.HomeScreenActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public final class Utils {
    public static final String BASE_URL = "https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String getFormattedDate(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date parsedDate = dateFormat.parse(date);
            SimpleDateFormat outFormat = new SimpleDateFormat("dd-MM-yy HH:mm");
            return outFormat.format(parsedDate);
        } catch (Exception e) {

        }
        return "";
    }

    public static long getTimeStampFromDate(String publishedAt) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date parsedDate = dateFormat.parse(publishedAt);
            if (parsedDate != null) {
                return parsedDate.getTime();
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public static void createChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("PRIMARY_CHANNEL", "Message Channel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Message Notifications");
            Objects.requireNonNull(context.getSystemService(NotificationManager.class)).createNotificationChannel(channel);
        }
    }

    public static void showNotification(String body, Context context) {
        NotificationCompat.Builder notificationBuilder = null;
        Intent action1Intent = new Intent(context, HomeScreenActivity.class);
        PendingIntent activityPendingIntent = PendingIntent.getActivity(context, 0,
                action1Intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder = new NotificationCompat.Builder(context, "PRIMARY_CHANNEL")
                .setContentText(body)
                .setContentTitle("My notification")
                .setPriority(NotificationCompat.DEFAULT_SOUND)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(activityPendingIntent);
        if (notificationBuilder != null) {
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(1, notificationBuilder.build());
        }
    }
}

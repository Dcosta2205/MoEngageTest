package com.lloyd.moengagetest.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        } catch (Exception e) { //this generic but you can control another types of exception
            // look the origin of excption
        }
        return "";
    }

}

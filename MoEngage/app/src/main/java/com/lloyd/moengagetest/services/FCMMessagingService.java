package com.lloyd.moengagetest.services;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.lloyd.moengagetest.utils.Utils;

import java.util.Map;

public class FCMMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("Lloyd", "Firebase token is " + token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        handleFCMDataPayload(remoteMessage);
        handleFCMNotificationPayload(remoteMessage);
    }

    /**
     * This method handles FCM notification payload
     */
    private void handleFCMNotificationPayload(@NonNull RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            Utils.createChannel(getApplicationContext());
            Utils.showNotification(remoteMessage.getNotification().getBody(), getApplicationContext());
        }
    }

    /**
     * This method handles FCM notification payload.
     */
    private void handleFCMDataPayload(@NonNull RemoteMessage remoteMessage) {
        if (remoteMessage.getData() != null && remoteMessage.getData().size() > 0) {
            Map<String, String> extras = remoteMessage.getData();
            if (extras.size() > 0) {
                Bundle data = new Bundle();
                for (Map.Entry<String, String> entry : extras.entrySet()) {
                    data.putString(entry.getKey(), entry.getValue());
                }
            }
        }
    }
}

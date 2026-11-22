// Created with Wevlo Studio
package com.example.notificationreader;

import android.app.Notification;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class NotificationListener extends NotificationListenerService {

    private static final String TAG = "NotifListener";

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        if (sbn == null) return;

        // নিজের অ্যাপের নোটিফিকেশন বাদ দিন
        if (sbn.getPackageName().equals(getPackageName())) return;

        Bundle extras = sbn.getNotification().extras;
        if (extras == null) return;

        String title = extras.getString(Notification.EXTRA_TITLE, "");
        String text = extras.getString(Notification.EXTRA_TEXT, "");
        String bigText = extras.getString(Notification.EXTRA_BIG_TEXT, "");
        String packageName = sbn.getPackageName();
        long postTime = sbn.getPostTime();

        Log.d(TAG, "App: " + packageName + " | Title: " + title + " | Text: " + text);

        // Firebase-এ পাঠান
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("notifications");

        Map<String, Object> notif = new HashMap<>();
        notif.put("app", packageName);
        notif.put("title", title);
        notif.put("text", text);
        notif.put("bigText", bigText);
        notif.put("time", postTime);

        ref.push().setValue(notif);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        Log.d(TAG, "Notification listener connected ✓");
    }
}
package com.example.notificationreader;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Firebase anonymous login (rules-এর জন্য)
        FirebaseAuth.getInstance().signInAnonymously();

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 50, 50, 50);

        TextView tv = new TextView(this);
        tv.setText("🔔 Notification Reader\n\nনিচের বাটনে চাপ দিয়ে নোটিফিকেশন অ্যাক্সেস চালু করুন।\nচালু না করলে অ্যাপ কাজ করবে না।");
        tv.setTextSize(18);

        Button btn = new Button(this);
        btn.setText("Notification Access চালু করুন");

        Button btnCheck = new Button(this);
        btnCheck.setText("অনুমতি চেক করুন");

        layout.addView(tv);
        layout.addView(btn);
        layout.addView(btnCheck);
        setContentView(layout);

        // Settings পেজে নিয়ে যাওয়া
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            startActivity(intent);
            Toast.makeText(this, "তালিকা থেকে অ্যাপটি খুঁজে চালু করুন", Toast.LENGTH_LONG).show();
        });

        // অনুমতি চেক
        btnCheck.setOnClickListener(v -> {
            if (isNotificationAccessGranted()) {
                Toast.makeText(this, "✓ অনুমতি দেওয়া আছে", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "✗ অনুমতি দেওয়া হয়নি", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isNotificationAccessGranted() {
        String enabled = Settings.Secure.getString(getContentResolver(),
                "enabled_notification_listeners");
        return enabled != null && enabled.contains(getPackageName());
    }
}package com.example.notificationreader;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Firebase anonymous login (rules-এর জন্য)
        FirebaseAuth.getInstance().signInAnonymously();

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 50, 50, 50);

        TextView tv = new TextView(this);
        tv.setText("🔔 Notification Reader\n\nনিচের বাটনে চাপ দিয়ে নোটিফিকেশন অ্যাক্সেস চালু করুন।\nচালু না করলে অ্যাপ কাজ করবে না।");
        tv.setTextSize(18);

        Button btn = new Button(this);
        btn.setText("Notification Access চালু করুন");

        Button btnCheck = new Button(this);
        btnCheck.setText("অনুমতি চেক করুন");

        layout.addView(tv);
        layout.addView(btn);
        layout.addView(btnCheck);
        setContentView(layout);

        // Settings পেজে নিয়ে যাওয়া
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            startActivity(intent);
            Toast.makeText(this, "তালিকা থেকে অ্যাপটি খুঁজে চালু করুন", Toast.LENGTH_LONG).show();
        });

        // অনুমতি চেক
        btnCheck.setOnClickListener(v -> {
            if (isNotificationAccessGranted()) {
                Toast.makeText(this, "✓ অনুমতি দেওয়া আছে", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "✗ অনুমতি দেওয়া হয়নি", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isNotificationAccessGranted() {
        String enabled = Settings.Secure.getString(getContentResolver(),
                "enabled_notification_listeners");
        return enabled != null && enabled.contains(getPackageName());
    }
}
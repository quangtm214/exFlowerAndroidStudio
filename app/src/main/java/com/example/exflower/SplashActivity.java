package com.example.exflower;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    // Thời gian hiển thị splash screen (milliseconds)
    private static final long SPLASH_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Khởi tạo notification channels
        NotificationHelper.createNotificationChannel(this);

        // Handler để chuyển sang MainActivity sau một khoảng thời gian
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Chuyển sang MainActivity
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);

            // Kết thúc SplashActivity
            finish();
        }, SPLASH_DELAY);
    }
}

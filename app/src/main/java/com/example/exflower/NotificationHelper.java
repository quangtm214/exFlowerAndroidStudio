package com.example.exflower;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper {
    // ID của kênh thông báo mặc định
    public static final String DEFAULT_CHANNEL_ID = "default";

    public static void createNotificationChannel(Context context) {
        // Kiểm tra version Android O trở lên (API 26+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Tạo một kênh thông báo mới
            NotificationChannel channel = new NotificationChannel(
                    DEFAULT_CHANNEL_ID,      // ID của kênh
                    "Default Channel",       // Tên kênh (hiển thị cho người dùng)
                    NotificationManager.IMPORTANCE_DEFAULT  // Mức độ ưu tiên của thông báo
            );

            // Thêm mô tả cho kênh
            channel.setDescription("Default notification channel");

            // Có thể thêm các cấu hình khác cho kênh
            channel.enableLights(true);        // Bật đèn LED thông báo
            channel.setLightColor(Color.BLUE); // Màu LED
            channel.enableVibration(true);     // Bật rung
            channel.setVibrationPattern(new long[]{100, 200, 300}); // Pattern rung

            // Lấy NotificationManager và đăng ký kênh
            NotificationManager notificationManager =
                    context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // Phương thức để gửi thông báo
    public static void sendNotification(Context context, String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, DEFAULT_CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        // Kiểm tra permission
        if (ActivityCompat.checkSelfPermission(context,
                android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(1, builder.build());
        }
    }
}

package com.app.bicicreta.app.service;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.app.bicicreta.R;

public class NotificationLocalService {
    private final String CHANNEL_NAME = "Notificações Gerais";
    private final String CHANNEL_ID = "canal_padrao";
    private final int REQUEST_CODE = 0;
    private final int importance = NotificationManager.IMPORTANCE_DEFAULT;
    private NotificationManager notificationManager;
    private Context context;
    private Class<?> classe;
    private static final int REQUEST_NOTIFICATION_PERMISSION = 1;
    private Activity activity;
    public NotificationLocalService(Context context, Class<?> classe, Activity activity){
        this.context = context;
        this.classe = classe;
        this.activity = activity;
        createChannel(context);
    }

    private void solicitarPermissao(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if(ContextCompat.checkSelfPermission(this.context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_NOTIFICATION_PERMISSION);
            }
        }
    }

    private boolean hasPermissionNotification(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
    }

    public void createNotification(String title, String texto){
        if(hasPermissionNotification()){
            Intent intent = new Intent( context, classe);
            PendingIntent pendingIntent = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S)
                pendingIntent = PendingIntent.getActivity(context, REQUEST_CODE, intent, PendingIntent.FLAG_MUTABLE);
            else
                pendingIntent = PendingIntent.getActivity(context, REQUEST_CODE, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

            Notification.Builder builder = null;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                builder = new Notification.Builder(context, CHANNEL_ID).setSmallIcon(R.drawable.bike)
                        .setContentTitle(title)
                        .setContentText(texto)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
            Notification notification = builder.build();
            notificationManager.notify(1, notification);
        }else{
            solicitarPermissao();
        }
    }

    private void createChannel(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String channelDescription = "Canal para notificações do app";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(channelDescription);
            notificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}

package fcb.de.fcbayernemotions.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import fcb.de.fcbayernemotions.MainActivity;
import fcb.de.fcbayernemotions.R;

public class SimpleNotification {

    private static final String CHANNEL_ID = "fcb.de.fcbayernemotions";

    private final Context context;
    private final NotificationManager notificationManager;

    public SimpleNotification(Context appContext) {
        this.context = appContext;
        notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        createChannel(CHANNEL_ID, "FCB Emotions", "Channel description");
    }

    private void createChannel(String id, String name, String description) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name,
                    NotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 100});
            notificationManager.createNotificationChannel(mChannel);
        }
    }

    public void show(int id, String content) {
        Notification.Builder nb;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nb  = new Notification.Builder(context, CHANNEL_ID);
        } else {
            nb  = new Notification.Builder(context);
        }

        PendingIntent intent = getPendingIntent();
        Notification notification = nb.setContentTitle("FCB Emotions")
                .setContentText(content)
                .setSmallIcon(R.mipmap.fcb_logo)
                .setContentIntent(intent)
                .setAutoCancel(true).build();

        notificationManager.notify(id, notification);
    }

    private PendingIntent getPendingIntent() {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(context, 0,
                notificationIntent, 0);
    }
}


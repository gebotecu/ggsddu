package com.example.ggsddu.utils;

import com.example.ggsddu.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

public class NotificationUtils {

	private Context mContext;
	private NotificationManager mNotificationManager;

	public NotificationUtils(Context context) {
		this.mContext = context;
		mNotificationManager = (NotificationManager) mContext
				.getSystemService(mContext.NOTIFICATION_SERVICE);
	}

	public void showNotification(String str) {
		// Notification notification = new Notification();
		// notification.icon = R.drawable.icon;
		// notification.tickerText = "點擊了"+str;
		// notification.when = System.currentTimeMillis();
		// notification.contentView = new RemoteViews(mContext.getPackageName(),
		// R.layout.recyclerview_item1);
		// mNotificationManager.notify(0, notification);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				mContext);
		builder.setTicker("點擊了" + str)
				.setSmallIcon(R.drawable.icon);
		Intent intent = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
		PendingIntent pIntent = PendingIntent.getBroadcast(mContext, 0, intent, 0);
		builder.setContentIntent(pIntent);
		Notification notification = builder.build();
		mNotificationManager.notify(0, notification);
	}
}

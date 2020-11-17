package com.example.mysimplenotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "dicoding channel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * OnClick Action in activity_main
     */
    fun sendNotification(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/bkn_org_jago"))
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentIntent(pendingIntent)// pending intent for action click in notification bar
                .setSmallIcon(R.drawable.ic_notifications_white_48px) // set small icon on notification bar
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_notifications_white_48px))
                .setContentTitle(resources.getString(R.string.content_title)) // for define title notification
                .setContentText(resources.getString(R.string.content_text)) // for define description
                .setSubText(resources.getString(R.string.subtext)) // sub text title
                .setAutoCancel(true) // Make this notification automatically dismissed when the user touches it.

        /*
        Untuk android Oreo ke atas perlu menambahkan notification channel
        */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            /* Create or update. */
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = CHANNEL_NAME

            mBuilder.setChannelId(CHANNEL_ID)

            mNotificationManager.createNotificationChannel(channel)
        }

        val notification = mBuilder.build()

        mNotificationManager.notify(NOTIFICATION_ID, notification)
    }
}
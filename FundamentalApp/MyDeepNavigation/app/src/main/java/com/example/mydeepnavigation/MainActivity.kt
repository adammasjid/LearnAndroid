package com.example.mydeepnavigation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

/**
 * TODO : Link Documentation for More Learning Forum
 *  https://developer.android.com/guide/components/activities/tasks-and-back-stack
 *  https://developer.android.com/training/implementing-navigation/temporal.html
 *  https://developer.android.com/training/implementing-navigation/ancestral.html
 *  https://developer.android.com/design/patterns/navigation.html#into-your-app
 */

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_open_detail.setOnClickListener(this)

        showNotification(this@MainActivity, getString(R.string.notification_title), getString(R.string.notification_message), 110)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_open_detail -> {
                val detailIntent = Intent (this@MainActivity, DetailActivity::class.java)
                detailIntent.putExtra(DetailActivity.EXTRA_TITLE, getString(R.string.detail_title))
                detailIntent.putExtra(DetailActivity.EXTRA_MESSAGE, getString(R.string.detail_message))
                startActivity(detailIntent)
            }
        }
    }

    private fun showNotification (context: Context, title: String, message: String, notifId: Int) {
        val CHANNEL_ID = "Channel_1"
        val CHANNEL_NAME = "Navigation Channel"

        val notifiDetailIntent = Intent(this, DetailActivity::class.java)
        notifiDetailIntent.putExtra(DetailActivity.EXTRA_TITLE, title)
        notifiDetailIntent.putExtra(DetailActivity.EXTRA_MESSAGE, message)

        /**
         * Di sini kita memanfaatkan TaskStackBuilder API untuk membuat sebuah back stack baru yang akan dimasukkan ke dalam task yang sudah ada.
         * Ketika kelas DetailActivity dijalankan, kemudian pengguna menekan tombol back baik itu system back button maupun up button,
         * maka pengguna akan diarahkan ke ParentActivity dari DetailActivity.
         */
        val pendingIntent = TaskStackBuilder.create(this)
            .addParentStack(DetailActivity::class.java)
            .addNextIntent(notifiDetailIntent)
            .getPendingIntent(110, PendingIntent.FLAG_UPDATE_CURRENT)
        val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_email_black_24dp)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.black))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
            builder.setChannelId(CHANNEL_ID)
            notificationManagerCompat.createNotificationChannel(channel)
        }
        val notification = builder.build()
        notificationManagerCompat.notify(notifId, notification)
    }
}
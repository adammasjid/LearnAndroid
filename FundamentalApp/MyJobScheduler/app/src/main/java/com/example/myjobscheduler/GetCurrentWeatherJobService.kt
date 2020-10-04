package com.example.myjobscheduler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.text.DecimalFormat

/**
 * TODO: this class created for :
 *  Melakukan koneksi HTTP ke openweathermap.org. Data akan diakses menggunakan endpoint berikut : http://api.openweathermap.org/data/2.5/weather?q="+CITY+"&appid="+APP_ID
 *  dan menampilkan notifikasi panel
 */

class GetCurrentWeatherJobService: JobService() {
    companion object {
        private val TAG = GetCurrentWeatherJobService::class.java.simpleName

        //Isikan dengan nama kota anda;
        internal const val CITY = "Surabaya"
        //Isikan dengan API Key Anda dari openweathermap;
        internal const val APP_ID = "ffd23980041058544ccc9b91f47834f3"
    }

    override fun onStartJob(params: JobParameters): Boolean {
        // Perlu diperhatikan bahwa angka 0 pada getJSONObject menunjukkan index ke-0
        // Jika data yang ingin kita ambil ada lebih dari satu maka gunakanlah looping
        Log.d(TAG, "onStartJob()")
        // onStartJob() selalu mengembalikan nilai true
        // maka harus membuat code untuk mengembalikan nilai false
        getCurrentWeather(params)
        return true
    }

    /* Metode onStopJob akan dijalankan jika job sudah berjalan, akan tetapi kondisinya kemudian tidak terpenuhi.
     Kita dapat mengembalikan nilai true jika kita ingin menjadwalkan kembali job tersebut. */
    override fun onStopJob(params: JobParameters): Boolean {
        Log.d(TAG, "onStopJob()")
        return true
    }

    private fun getCurrentWeather(job: JobParameters) {
        Log.d(TAG, "getCurrentWeather: Mulai.....")
        val client = AsyncHttpClient() // AsyncHttpClient, berarti kita akan menggunakan client yang bertanggung jawab untuk koneksi data dan sifatnya adalah asynchronous.
        val url = "http://api.openweathermap.org/data/2.5/weather?q=$CITY&appid=$APP_ID"
        Log.d(TAG, "getCurrentWeather: $url")
        client.get(url, object : AsyncHttpResponseHandler() {
                   // param 1 = get method HTTP / url
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                val result = String(responseBody)
               /**
                * @BelowCodeIs
                *  Kode dibawah memproses respons yang diterima dari webservice. Karena nilai awal respons adalah data byte, maka kita harus mengubahnya menjadi string.
                *  Hal ini akan memudahkan kita untuk mengubahnya menjadi bentuk JSON.
                *  [Attention] Perhatikan saat getJsonArray atau getString, pastikan teks yang diambil sesuai dengan yang berasal dari API dan harus sama persis.
                */
                Log.d(TAG, result)
                try {
                    val responseObject = JSONObject(result)
                    // Perlu diperhatikan bahwa angka 0 pada getJSONObject menunjukkan index ke-0
                    // Jika data yang ingin kita ambil ada lebih dari satu maka gunakanlah looping
                    val currentWeather = responseObject.getJSONArray("weather").getJSONObject(0).getString("main")
                    val description = responseObject.getJSONArray("weather").getJSONObject(0).getString("description")
                    val tempInKelvin = responseObject.getJSONObject("main").getDouble("temp")
                    val tempInCelsius = tempInKelvin - 273
                    val temperature = DecimalFormat("##.##").format(tempInCelsius)
                    val title = "Current Weather"
                    val message = "$currentWeather, $description with $temperature celsius"
                    val notifId = 100
                    showNotification(applicationContext, title, message, notifId)
                    // Ketika proses berjalan di thread yang berbeda, maka proses tersebut dapat mengabarkan kapan dia telah selesai.
                    // Caranya dengan menjalankan jobFinished()
                    Log.d(TAG, "onSuccess: Selesai.....")
                    jobFinished(job, false)
                } catch (e: Exception) {
                    Log.d(TAG, "onSuccess: Gagal.....")
                    jobFinished(job, true)
                    e.printStackTrace()
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d(TAG, "onFailure: Gagal.....")
                jobFinished(job, true)
            }
        })
    }
    private fun showNotification(context: Context, title: String, message: String, notifId: Int) {
        val CHANNEL_ID = "Channel_1"
        val CHANNEL_NAME = "Job scheduler channel"
        val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_replay_30_black_24p)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.black))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)
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
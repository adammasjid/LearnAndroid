package com.example.myworkmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.SyncHttpClient
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.text.DecimalFormat

class MyWorker (context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    companion object {
        private val TAG = MyWorker::class.java.simpleName
        const val APP_ID = "ffd23980041058544ccc9b91f47834f3"
        const val EXTRA_CITY = "city"
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "channel_01"
        const val CHANNEL_NAME = "dicoding channel"
    }
    private var resultStatus: Result? = null

    /* Metode doWork adalah metode yang akan dipanggil ketika WorkManager berjalan.
       Kode di dalamnya akan dijalankan di background thread secara otomatis.
       Metode ini juga mengembalikan nilai berupa Result yang berfungsi untuk mengetahui status WorkManager yang berjalan. */
    override fun doWork(): Result {
        val dataCity = inputData.getString(EXTRA_CITY)
        val result = getCurrentWeather(dataCity)
        return result
    }

    // method dibawah untuk parsing JSON API dengan LoopJ
    private fun getCurrentWeather(city: String?): Result {
        /**
         * @Marker
         * Di sini kita menggunakan SyncHttpClient untuk koneksi data yang sifatnya synchronous.
         * Selain itu, ada AsyncHttpClient untuk koneksi secara asynchronous.
         * Khusus di WorkManager Anda perlu menjalankan proses secara synchronous supaya bisa mendapatkan result success.
         * Selain itu WorkManager sudah berjalan di background thread, jadi aman saja menjalankan secara langsung.
         * Namun jika Anda ingin menggunakan LoopJ di Activity,
         * maka gunakanlah AsyncHttpClient supaya tidak terjadi error NetworkOnMainThread.
         */
        Log.d(TAG, "getCurrentWeather: Mulai.....")
        val client = SyncHttpClient()
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$APP_ID"
        Log.d(TAG, "getCurrentWeather: $url")
        client.post(url, object : AsyncHttpResponseHandler() {
            /* ada beberapa status yang bisa dikembalikan yaitu: */
              // Result.success(), result yang menandakan berhasil.
              // Result.failure(), result yang menandakan gagal.
              // Result.retry(), result yang menandakan untuk mengulang task lagi.
            override fun onSuccess(statusCode: Int, headers: Array<Header?>?, responseBody: ByteArray) {
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val responseObject = JSONObject(result)
                    val currentWeather: String = responseObject.getJSONArray("weather").getJSONObject(0).getString("main")
                    val description: String = responseObject.getJSONArray("weather").getJSONObject(0).getString("description")
                    val tempInKelvin = responseObject.getJSONObject("main").getDouble("temp")
                    val tempInCelcius = tempInKelvin - 273
                    val temprature: String = DecimalFormat("##.##").format(tempInCelcius)
                    val title = "Current Weather in $city"
                    val message = "$currentWeather, $description with $temprature celcius"
                    showNotification(title, message)
                    Log.d(TAG, "onSuccess: Selesai.....")
                    resultStatus = Result.success()
                } catch (e: Exception) {
                    showNotification("Get Current Weather Not Success", e.message)
                    Log.d(TAG, "onSuccess: Gagal.....")
                    resultStatus = Result.failure()
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<Header?>?, responseBody: ByteArray?, error: Throwable) {
                Log.d(TAG, "onFailure: Gagal.....")
                // ketika proses gagal, maka jobFinished diset dengan parameter true. Yang artinya job perlu di reschedule
                showNotification("Get Current Weather Failed", error.message)
                resultStatus = Result.failure()
            }
        })
        return resultStatus as Result
    }
    private fun showNotification(title: String, description: String?) {
        val notificationManager = applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notification: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications_black_24dp)
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            notification.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(NOTIFICATION_ID, notification.build())
    }
}
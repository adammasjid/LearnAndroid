package com.example.mybroadcastreceiver

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log


class DownloadService : IntentService("DownloadService") {

    companion object {
        val TAG = DownloadService::class.java.simpleName
    }

    /* Di sini kita akan menjalankan Intent Service yang akan melakukan proses mengunduh file secara Asynchronous di background.
       Kelas DownloadService mengambil peran di sini. Pada kenyataanya, DownloadService ini hanya melakukan proses sleep() selama 5 detik dan....
       kemudian mem-broadcast sebuah IntentFilter dengan Action yang telah ditentukan, ACTION_DOWNLOAD_STATUS. */
    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "Download Service dijalankan")
        if (intent != null) {
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            // Ketika baris dibawah ini dijalankan pada DownloadService.
            // Maka seketika itu pula metode onReceive() pada mainActivity akan melakukan proses di dalamnya:
            val notifyFinishIntent = Intent(MainActivity.ACTION_DOWNLOAD_STATUS)
            sendBroadcast(notifyFinishIntent)
        }
    } // Ketika proses pengunduhan berkas tersebut selesai, service akan mem-broadcast sebuah event dan akan ada Activity yang merespon.
}

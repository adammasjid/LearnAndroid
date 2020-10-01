package com.example.myasynchronousapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @MARKER
 * untuk melihat background process, lihat pada [Logcat] dan filter perncarian menjadi [MyService] / NameClass
 * Pada metode tersebut kita menjalankan sebuah background process untuk melakukan simulasi proses yang sulit. Dan ia berjalan secara asynchronous.
 */

class MyService : Service() {

    companion object {
        internal val TAG = MyService::class.java.simpleName
    }

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    // method dibawah dijalankan setelah intent terpanggil
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service Dijalankan: ")
        GlobalScope.launch {
            delay(3000)
            stopSelf() // berfungsi untuk  memberhentikan atau mematikan MyService dari sistem Android.
            Log.d(TAG, "Service dihentikan")
        }
        return START_STICKY
        /**
         * START_STICKY menandakan bahwa bila service tersebut dimatikan oleh sistem Android karena kekurangan memori,...
         * ia akan diciptakan kembali jika sudah ada memori yang bisa digunakan. Metode onStartCommand() juga akan kembali dijalankan.
         */
    }

    // method on Destroy akan active setelah method stopSelf dijalankan
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}
package com.example.myasynchronousapp

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log

/**
 * @Scenario
    - Kita menjalankan IntentService tersebut dengan sebuah obyek Intent dari MainActivity dengan membawa data, dalam konteks ini adalah nilai integer yang menentukan berapa lama background process akan dijalankan.
    - MyIntentService dijalankan dan kemudian memproses obyek Intent yang dikirimkan untuk menjalankan background.
    - Seperti sifatnya, IntentService tidak perlu mematikan dirinya sendiri. Secara otomatis ketika proses yang dilakukan selesai, maka IntentService berhenti dengan sendirinya.
 */

class MyIntentService : IntentService("MyIntentService") {

    companion object {
        internal const val EXTRA_DURATION = "extra_duration"
        private val TAG = MyIntentService::class.java.simpleName
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent: Mulai.....")
        val duration = intent?.getLongExtra(EXTRA_DURATION, 0) as Long
        try {
            Thread.sleep(duration);
            Log.d(TAG, "onHandleIntent: Selesai.....")
        } catch (e: InterruptedException) {
            e.printStackTrace()
            Thread.currentThread().interrupt()
        }
    }
    /**
     * Terakhir, IntentService tak perlu mematikan dirinya sendiri. Service ini akan berhenti dengan sendirinya...
     * ketika sudah selesai menyelesaikan tugasnya.
     */

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionFoo(param1: String, param2: String) {
        TODO("Handle action Foo")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }
}

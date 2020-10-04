package com.example.mybroadcastreceiver

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

// TODO : Check manifest for implementation component which required for BroadcastReceived

/**
 * @ThreePointSummary :
    - Registrasikan sebuah obyek BroadcastReceiver pada komponen aplikasi seperti Activity dan Fragment dan tentukan action/event apa yang ingin didengar/direspon.
    - Lakukan proses terkait pada metode onReceiver() ketika event atau action yang dipantau di-broadcast oleh komponen lain.
    - Jangan lupa untuk mencopot pemasangan obyek receiver sebelum komponen tersebut dihancurkan atau dimatikan.
 */

class MainActivity : AppCompatActivity(), View.OnClickListener {

    /* membuat sebuah obyek dari DownloadReceiver. Kemudian MainActivity diregistrasikan untuk mendengar event/action dengan tag: ACTION_DOWNLOAD_STATUS.
       Ketika event/action tersebut ditangkap oleh MainActivity, maka obyek downloadReceiver akan dijalankan. */
    private lateinit var downloadReceiver: BroadcastReceiver
    companion object {
        private const val SMS_REQUEST_CODE = 101
        const val ACTION_DOWNLOAD_STATUS = "download_status"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_permission.setOnClickListener(this)
        btn_download.setOnClickListener(this)

        downloadReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                Log.d(DownloadService.TAG, "Download Selesai")
                Toast.makeText(context, "Download Selesai", Toast.LENGTH_SHORT).show()
            }
        }
        val downloadIntentFilter = IntentFilter(ACTION_DOWNLOAD_STATUS)
        registerReceiver(downloadReceiver, downloadIntentFilter)
    }

    override fun onClick(v: View) {
        when {
            // below is code for calling checking permission
            v.id == R.id.btn_permission -> PermissionManager.check(this, Manifest.permission.RECEIVE_SMS, SMS_REQUEST_CODE)

            v.id == R.id.btn_download -> {
            }
        }
    }

    /* this function required for calling Static Class PermissionManager */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == SMS_REQUEST_CODE) {
            when {
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> Toast.makeText(this, "Sms receiver permission diterima", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "Sms receiver permission ditolak", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
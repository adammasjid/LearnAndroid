package com.example.myasynchronousapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        btn_start_service.setOnClickListener(this)
        btn_start_intent_service.setOnClickListener(this)
        btn_start_bound_service.setOnClickListener(this)
        btn_stop_bound_service.setOnClickListener(this)
    }

    /* Kode dibawah merupakan sebuah listener untuk menerima callback dari ServiceConnection. Kalau dilihat ada dua callback,
     yakni ketika mulai terhubung dengan kelas service dan juga ketika kelas service sudah terputus. */
    private var mServiceBound = false
    private lateinit var mBoundService: MyBoundService
    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mServiceBound = false
        }
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val myBinder = service as MyBoundService.MyBinder
            mBoundService = myBinder.getService
            mServiceBound = true
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_start_service -> {
                // below menggunakan startService() untuk melakukan service y
                val mStartServiceIntent = Intent(this@ServiceActivity, MyService::class.java)
                startService(mStartServiceIntent)
                // setelah menjalankan method ini maka, metode onStartCommand() pada MyService akan dijalankan.
            }
            R.id.btn_start_intent_service -> {
                val mStartIntentService = Intent(this@ServiceActivity, MyIntentService::class.java)
                mStartIntentService.putExtra(MyIntentService.EXTRA_DURATION, 5000L)
                startService(mStartIntentService)
            }
            R.id.btn_start_bound_service -> {
                // below code from start bindService
                val mBoundServiceIntent = Intent(this@ServiceActivity, MyBoundService::class.java)
                bindService(mBoundServiceIntent, mServiceConnection, Context.BIND_AUTO_CREATE)
                /**
                 * bindService yang digunakan untuk memulai mengikat kelas MyBoundService ke kelas MainActivity.
                 * Sedangkan mBoundServiceIntent adalah sebuah intent eksplisit yang digunakan untuk menjalankan komponen dari dalam sebuah aplikasi.
                 * Sedangkan mServiceConnection adalah sebuah ServiceConnection berfungsi sebagai callback dari kelas MyBoundService.
                 * Kemudian ada juga BIND_AUTO_CREATE yang membuat sebuah service jika service tersebut belum aktif.
                 */
                /**
                 * TODO: Selain BIND_AUTO_CREATE, ada fungsi lainnya :
                 * @BIND_ABOVE_CLIENT : yang digunakan ketika sebuah service lebih penting daripada aplikasi itu sendiri.
                 * @BIND_ADJUST_WITH_ACTIVITY : saat mengikat sebuah service dari activity, maka ia akan mengizinkan untuk menargetkan service mana yang lebih penting berdasarkan activity yang terlihat oleh pengguna.
                 * @BIND_ALLOW_OOM_MANAGEMENT : memungkinkan untuk mengikat service hosting untuk mengatur memori secara normal.
                 * @BIND_AUTO_CREATE : secara otomatis membuat service selama binding-nya aktif.
                 * @BIND_DEBUG_UNBIND : berfungsi sebagai bantuan ketika debug mengalami masalah pada pemanggilan unBind.
                 * @BIND_EXTERNAL_SERVICE : merupakan service yang terikat dengan service eksternal yang terisolasi.
                 * @BIND_IMPORTANT : service ini sangat penting bagi klien, jadi harus dibawa ke tingkat proses foreground.
                 * @BIND_NOT_FOREGROUND : pada service ini tak disarankan untuk mengubah ke tingkat proses foreground.
                 * @BIND_WAIVE_PRIORITY : service ini tidak akan mempengaruhi penjadwalan atau prioritas manajemen memori dari target proses layanan hosting.
                 */
            }
            R.id.btn_stop_bound_service -> {
                // untuk mengakhiri MyBoundService yang masih terikat
                unbindService(mServiceConnection)
                // Kode di atas berfungsi untuk melepaskan service dari activity pemanggil.
                // Secara tidak langsung maka ia akan memanggil metode unBind yang ada di kelas MyBoundService.
            }
        }
    }

    /* Kode onDestroy() seperti yang dijelaskan di metode sebelumnya, akan memanggil unBindService atau melakukan pelepasan service dari Activity.
       Pemanggilan unbindService di dalam onDestroy ditujukan untuk mencegah memory leaks dari bound services. */
    override fun onDestroy() {
        // Metode onDestroy() yang ada di MyBoundService ini berfungsi untuk melakukan penghapusan kelas MyBoundService dari memori.
        // Jadi setelah service sudah terlepas dari kelas MainActivity, kelas MyBoundService juga terlepas dari memori android.
        super.onDestroy()
        if (mServiceBound) {
            unbindService(mServiceConnection)
        }
    }
}
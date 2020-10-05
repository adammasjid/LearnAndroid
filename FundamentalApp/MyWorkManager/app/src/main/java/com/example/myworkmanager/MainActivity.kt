package com.example.myworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.work.*
import androidx.work.Data.Builder
import com.example.myworkmanager.R.layout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var periodicWorkRequest: PeriodicWorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        btnOneTimeTask.setOnClickListener(this)
        btnPeriodicTask.setOnClickListener(this)
        btnCancelTask.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnOneTimeTask -> startOneTimeTask()
            R.id.btnPeriodicTask -> startPeriodicTask()
            R.id.btnCancelTask -> cancelPeriodicTask()
        }
    }

    // method dibawah untuk menjalankan workManager sekali saja
    private fun startOneTimeTask() {
        // code dibawah untuk mengirim data di workManager
        textStatus.text = getString(R.string.status)
        val data = Builder()
            .putString(MyWorker.EXTRA_CITY, editCity.text.toString())
            .build()
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setInputData(data)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance().enqueue(oneTimeWorkRequest)
        /*
            Fungsi di atas digunakan untuk membuat one-time request. Saat membuat request,
            Anda bisa menambahkan data untuk dikirimkan dengan membuat object Data yang berisi data key-value,
            key yang dipakai di sini yaitu MyWorker.EXTRA_CITY, Setelah itu dikirimkan melalui setInputData()
            code dibawah WorkInfo digunakan untuk mengetahui status task yang dieksekusi
        */
        WorkManager.getInstance().getWorkInfoByIdLiveData(oneTimeWorkRequest.id).observe(this@MainActivity, object : Observer<WorkInfo> {
            override fun onChanged(workInfo: WorkInfo) {
                val status = workInfo.state.name
                textStatus.append("\n" + status)
            }
        })
        /*
            Anda dapat membaca status secara live dengan menggunakan getWorkInfoByIdLiveData.
            Anda juga bisa memberikan aksi pada state tertentu dengan mengambil data state dan membandingkannya dengan konstanta yang bisa didapat di WorkInfo.State.
            Misalnya, pada kode di atas kita mengatur tombol Cancel task aktif jika task dalam state ENQUEUED.
        */
    }

    // method dibawah untuk menjalankan workManager secara periodic
    private fun startPeriodicTask() {
        // code dibawah untuk mengirim data di workManager ke MainActivity
        textStatus.text = getString(R.string.status)
        val data = Builder()
            .putString(MyWorker.EXTRA_CITY, editCity.text.toString())
            .build()
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        periodicWorkRequest = PeriodicWorkRequest.Builder(MyWorker::class.java, 15, TimeUnit.MINUTES)
                                                                                // Batas minimal interval yang diperbolehkan yaitu 15 menit, sama dengan JobScheduler.
            .setInputData(data)
            .setConstraints(constraints)
            .build()
        /* kondisi yang bisa diSet diatas adalah */
        /**
            1)  tRequiredNetworkType, ketika bernilai CONNECTED berarti dia harus terhubung ke koneksi internet,
                apa pun jenisnya.Bila kita ingin memasang ketentuan bahwa job hanya akan berjalan ketika perangkat terhubung ke network Wi-fi,
                maka kita perlu memberikan nilai UNMETERED.
            2)  setRequiresDeviceIdle, menentukan apakah task akan dijalankan ketika perangkat dalam keadaan sedang digunakan atau tidak.
                Secara default, parameter ini bernilai false. Bila kita ingin task dijalankan ketika perangkat dalam kondisi tidak digunakan,
                maka kita beri nilai true.
            3)  setRequiresCharging, menentukan apakah task akan dijalankan ketika baterai sedang diisi atau tidak.
                Nilai true akan mengindikasikan bahwa task hanya berjalan ketika baterai sedang diisi.
                Kondisi ini dapat digunakan bila task yang dijalankan akan memakan waktu yang lama.
            4)  setRequiresStorageNotLow, menentukan apakah task yang dijalankan membutuhkan ruang storage yang tidak sedikit.
                Secara default, nilainya bersifat false.
                Dan ketentuan lainnya yang bisa kita gunakan.
         */
        WorkManager.getInstance().enqueue(periodicWorkRequest)

        // code dibawah WorkInfo digunakan untuk mengetahui status task yang dieksekusi
        WorkManager.getInstance().getWorkInfoByIdLiveData(periodicWorkRequest.id).observe(this@MainActivity, object : Observer<WorkInfo> {
            override fun onChanged(workInfo: WorkInfo) {
                val status = workInfo.state.name
                textStatus.append("\n" + status)
                btnCancelTask.isEnabled = false
                if (workInfo.state == WorkInfo.State.ENQUEUED) {
                    btnCancelTask.isEnabled = true
                }
            }
        })
    }

    // method dibawah digunakan untuk membatalkan workManager
    private fun cancelPeriodicTask() {
        WorkManager.getInstance().cancelWorkById(periodicWorkRequest.id)
        // Kode di atas digunakan untuk membatalkan task berdasarkan id request. Selain menggunakan id, Anda juga bisa menambahkan tag pada request.
    }
}
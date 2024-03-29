package com.example.myjobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

/**
 * TODO: Koneksi data ke webservice openweathermap.org
 *  Untuk memperoleh data dari openweathermap.org, kita membutuhkan API KEY.
    Kita akan terhubung ke layanan openweathermap.org menggunakan protokol HTTP.
    Proses umum untuk terhubung ke webservice adalah sebagai berikut:
    Melakukan request ke webservice (endpoint) melalui HTTP GET.
    1. Webservice akan memberikan respons berupa data yang dibutuhkan dalam bentuk teks JSON.
    2. Aplikasi akan menerima respons tersebut dan kemudian dilakukan proses parsing.
    3. Proses parsing ini akan mengambil informasi yang dibutuhkan.
 */

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        private const val JOB_ID = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start.setOnClickListener(this)
        btn_cancel.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_start -> startJob()
            R.id.btn_cancel -> cancelJob()
        }
    }

    private fun startJob() {
        if (isJobRunning(this)) {
            Toast.makeText(this, "Job Service is already scheduled", Toast.LENGTH_SHORT).show()
            return
        }
        val mServiceComponent = ComponentName(this, GetCurrentWeatherJobService::class.java)

        // TODO : about JobInfo.Builder ( https://developer.android.com/reference/android/app/job/JobInfo.Builder.html )
        val builder = JobInfo.Builder(JOB_ID, mServiceComponent)
        /*
        Kondisi network,
        NETWORK_TYPE_ANY, berarti tidak ada ketentuan tertentu
        NETWORK_TYPE_UNMETERED, adalah network yang tidak dibatasi misalnya wifi
        */
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        /*
        Kondisi device, secara default sudah pada false
        false, berarti device tidak perlu idle ketika job ke trigger
        true, berarti device perlu dalam kondisi idle ketika job ke trigger
        */
        builder.setRequiresDeviceIdle(false)
        /*
        Kondisi charging
        false, berarti device tidak perlu di charge
        true, berarti device perlu dicharge
        */
        builder.setRequiresCharging(false)
        /*
        Periode interval sampai ke trigger
        Dalam milisecond, 1000ms = 1detik
        */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setPeriodic(900000) //15 menit
        } else {
            builder.setPeriodic(180000) //3 menit
        }
        val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.schedule(builder.build())
        Toast.makeText(this, "Job Service started", Toast.LENGTH_SHORT).show()
    }

    private fun cancelJob() {
        val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.cancel(JOB_ID)
        Toast.makeText(this, "Job Service canceled", Toast.LENGTH_SHORT).show()
    }

    /* Fungsi dibawah digunakan untuk mengecek apakah job sudah berjalan atau belum,
       sehingga job tidak dibuat secara berulang-ulang. Fungsi tersebut dipanggil di dalam startJob. */
    private fun isJobRunning(context: Context): Boolean {
        var isScheduled = false
        val scheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        for (jobInfo in scheduler.allPendingJobs) {
            if (jobInfo.id == JOB_ID) {
                isScheduled = true
                break
            }
        }
        return isScheduled
    }
}
package com.example.myasynchronousapp

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.ref.WeakReference

/**
 * TODO : Best Practice for implementation Asynchronous
 * @BackgroundThread : example code below, (https://developer.android.com/guide/components/processes-and-threads.html)
 *   fun onClick(v: View) {
        Thread(Runnable {
            val txt = loadStringFromNetwork("http://example.com/string.json")
            textView.post(Runnable() {
                textView.setText(txt)
            }
        }).start()
    }
 * @Handler
 * @AsyncTask, point about asyncTask, below : ( https://developer.android.com/reference/android/os/AsyncTask.html )
    - Diperuntukkan untuk proses asynchronous dan mampu berkomunikasi dengan ui thread untuk mengirimkan hasil proses.
    - Kelas Java yang dibuat harus inherit kepada AsyncTask.
    - Kasus umum yang biasa diterapkan adalah ketika mengunduh berkas dari Internet dan dengan menampilkan perkembangan pengunduhan di layar.
 * @Params_Progress_and_Result, meaning about them is :
    - Params :  Tipe parameter yang akan menjadi inputan untuk proses asynchronous.
    - Progress :  Tipe satuan unit untuk memberi kabar perkembangan ke ui thread.
    - Result  :  Tipe hasil dari proses asynchronous yang dijalankan.
 * @MainMethodFromAsyncTask :
    - [onPreExecute] Metode ini akan dijalankan pertama kali sebelum proses asynchronous dilakukan.
    - [doInBackground] Metode ini akan dijalankan setelah [onPreExecute]. Di sinilah proses asynchronous terjadi. Pada kasus di atas, metode [doInBackground]...
      akan melakukan pengunduhan berkas dari network melalui metode [downloadFile],dan hasil perkembangannya dikirim melalui metode [publishProgress].
    - [onProgressUpdate] Metode ini yang akan menerima input dari apa yang dilakukan oleh metode publishProgress.
    - [onPostExecute] Setelah proses di doInBackground() selesai, maka hasilnya akan dikirimkan ke metode onPostExecute(). Kemudian prosesnya akan dikembalikan lagi ke ui thread.
 * @RunningAsyncTask, code for this task
 *   DownloadFilesTask().execute(url1,url2,url3)
 * @Coroutine(Kotlin) : ( https://kotlinlang.org/docs/tutorials/coroutines/coroutines-basic-jvm.html ) and ( https://www.lukaslechner.com/understanding-kotlin-coroutines-with-this-mental-model/ )
    - Coroutine adalah salah satu fitur unggulan dari Kotlin yang diperkenalkan sejak versi 1.3. Walaupun coroutines dan threads bekerja dengan cara sama,
    - coroutines jauh lebih ringan dan efisien. Jutaan coroutines dapat berjalan pada beberapa threads.
    - harus menambahkan dependency untuk mengimplementasikan coroutine <code
 */
/* TODO: is implementation asynchronous with coroutine.kt */
//class MainActivity : AppCompatActivity() { // if make async without espresso must extend with interface for AsyncCallBack
//    // TODO : perhatikan logcat dan filter pada class DemoAsync nya saja
//    //  Proyek yang dikerjakan adalah contoh sederhana dari penggunaan AsyncTask untuk membuat proses berjalan secara asynchronous dan tetap bisa berkomunikasi (synchronize) dengan ui thread.
//    companion object {
//        private const val INPUT_STRING = "Halo Ini Demo AsyncTask!!"
//        private const val LOG_ASYNC = "DemoAsync"
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        /* textView dibawah digunakan untuk memantau AsyncTask
//        *   dan akan membuat obyek DemoAsync dan menjanlakan AsyncTask dengan inputan berupa obyek string. */
//        tv_status.setText(R.string.status_pre)
//        tv_desc.text = INPUT_STRING
//
//        GlobalScope.launch(Dispatchers.Default) {
//            //background thread
//            val input = INPUT_STRING
//
//            var output: String? = null
//
//            Log.d(LOG_ASYNC, "status : doInBackground")
//            try {
//                // Input stringnya ditambahkan dengan string ' Selamat Belajar!!"
//                output = "$input Selamat Belajar!!"
//
//                /*
//                Sleep thread digunakan untuk simulasi bahwa ada proses yang sedang berjalan selama 2 detik
//                2000 miliseconds = 2 detik
//                */
//                delay(2000)
//
//                //pindah ke Main thread untuk update UI
//                withContext(Dispatchers.Main) {
//                    tv_status.setText(R.string.status_post)
//                    tv_desc.text = output
//                }
//
//            } catch (e: Exception) {
//                Log.d(LOG_ASYNC, e.message.toString())
//            }
//        }
//    }
//}
/* TODO: is implementation asynchronous with Pure kt */
    /**
     * @Marker Proses Async dibawah terdapat 3 proses
     * AsyncTask ini memiliki 3 tipe generik.
       Param : Menerima inputan obyek string.
       Progress : Void ⇒ Merupakan obyek dari void dengan kata lain, tidak ada progress yang akan ditampilkan.
       Result : Hasil dari proses berupa obyek string.
     */
      // code dibawah berfungsi untuk mempersiapkan asyncTask. Disini masih berjalan di main thread dan bisa digunakan untuk mengakses view.
class MainActivity : AppCompatActivity(), MyAsyncCallback {

    companion object {
        private const val INPUT_STRING = "Halo Ini Demo AsyncTask!!"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val demoAsync = DemoAsync(this)
        demoAsync.execute(INPUT_STRING)

        btn_move_to_service.setOnClickListener {
            if (it.id == R.id.btn_move_to_service) {
                val moveToServiceActivity = Intent(this@MainActivity, ServiceActivity::class.java)
                startActivity(moveToServiceActivity)
            }
        }
    }

    /* textView dibawah digunakan untuk memantau AsyncTask
    dan akan membuat obyek DemoAsync dan menjanlakan AsyncTask dengan inputan berupa obyek string. */
    override fun onPreExecute() {
        // Pada metode onPreExecute(), kita hanya menuliskan status onPreExecute ke dalam obyek TextView untuk....
        // menandakan bahwa metode onPreExecute telah dijalankan.
        tv_status.setText(R.string.status_pre)
        tv_desc.text = INPUT_STRING
    }

    override fun onPostExecute(result: String) {
        // metode onPostExecute() akan menampilkan hasil proses yang dilakukan di doInBackground().
        tv_status.setText(R.string.status_post)
        tv_desc.text = result
    }

    /* pada class DemoAsync ini kita menggunakan WeakReference
    * WeakReference disarankan untuk menghindari memory leak yang bisa terjadi dalam AsyncTask. */
    private class DemoAsync(myListener: MyAsyncCallback) : AsyncTask<String, Void, String>() {

        private val myListener: WeakReference<MyAsyncCallback>
        init {
            this.myListener = WeakReference(myListener)
        }

        companion object {
            private val LOG_ASYNC = "DemoAsync"
        }

        /* Kode dibawah berfungsi untuk mempersiapkan asyncTask. Di sini masih berjalan di main thread dan bisa digunakan untuk mengakses view.*/
        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(LOG_ASYNC, "status : onPreExecute")
            // Kelas DemoAsync menggunakan WeakReference untuk mengisi status yang ada pada bagian callback tersebut.
            // WeakReference dipanggil di bagian berikut:
            val myListener = this.myListener.get()
            myListener?.onPreExecute()
            // Kode di atas akan mengirimkan informasi bahwa kelas DemoAsync sedang dalam proses onPreExecute atau ...
            // proses persiapan. Informasi tersebut akan dikirim ke kelas MainActivity melalui myListener.onPreExecute()
        }

        /* dibawah adalah kode yang berjalan secara asynchronous */
        override fun doInBackground(vararg params: String?): String {
            Log.d(LOG_ASYNC, "status : doInBackground")

            var output: String? = null

            try {
                val input = params[0]
                output = "$input Selamat Belajar!!"
                Thread.sleep(2000)

            } catch (e: Exception) {
                e.message?.let { Log.d(LOG_ASYNC, it) }
            }

            return output.toString()
        }

        /* Kode dibawah, yakni onPostExecute berjalan ketika proses doInBackground telah selesai dan...
         akan dijalankan di main thread yang mana state/kondisi ini dapat mengakses view. */
        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            Log.d(LOG_ASYNC, "status : onPostExecute")

            val myListener = this.myListener.get()
            myListener?.onPostExecute(result)
        }
    }
}

internal interface MyAsyncCallback {
    fun onPreExecute()
    fun onPostExecute(text: String)
}

package com.example.myquote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getRandomQuote()

        btnAllQuotes.setOnClickListener {
            startActivity(Intent(this@MainActivity, ListQuoteActivity::class.java))
        }
    }

     private fun getRandomQuote () {
         /**
          * Untuk menggunakan library LoopJ, Anda cukup menggunakan AsyncHttpClient untuk membuat koneksi ke server secara asynchronous.
          * Kemudian karena kita hanya mengambil data (READ), maka Anda menggunakan kode client.get().
          */
         progressBar.visibility = View.VISIBLE
         val client = AsyncHttpClient()
         val url = "https://programming-quotes-api.herokuapp.com/quotes/random"

         client.get(url, object: AsyncHttpResponseHandler(){
             override fun onSuccess(
                 statusCode: Int,
                 headers: Array<out Header>?,
                 responseBody: ByteArray?
             ) {
                 // jika koneksi berhasil
                 progressBar.visibility = View.INVISIBLE
                 val result = responseBody?.let { String(it) }
                 if (result != null) {
                     Log.d(TAG, result)
                 }
                 try {
                     val responseObject = JSONObject(result) // mengambil data dari JSONObject
                     val quote = responseObject.getString("en") // mengambil value dari key "en"
                     val author = responseObject.getString("author") // mengambil value dari key "author"
                     // dibawah untuk menampilkan JSON ke view
                     tvQuote.text = quote
                     tvAuthor.text = author
                 } catch (e: Exception) {
                     Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
                     e.printStackTrace()
                 }

             }

             override fun onFailure(
                 statusCode: Int,
                 headers: Array<out Header>?,
                 responseBody: ByteArray?,
                 error: Throwable?
             ) {
                 progressBar.visibility = View.INVISIBLE
                 val errorMessage = when (statusCode) {
                     401 -> "$statusCode : Bad Request"
                     403 -> "$statusCode : Forbidden"
                     404 -> "$statusCode : Not Found"
                     else -> "$statusCode : ${error?.message}"
                 }
                 Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
             }

         })
    }

}
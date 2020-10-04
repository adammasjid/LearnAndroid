package com.example.myquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_list_quote.*
import org.json.JSONArray

class ListQuoteActivity : AppCompatActivity() {
    companion object {
        private val TAG = ListQuoteActivity::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_quote)
        supportActionBar?.title = "List of Quotes"
        getListQuotes()
    }
    private fun getListQuotes() {
        progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://programming-quotes-api.herokuapp.com/quotes/page/1"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                // Jika koneksi berhasil
                progressBar.visibility = View.INVISIBLE // Parsing JSON

                val listQuote = ArrayList<String>()
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonArray = JSONArray(result) // untuk mengambil data dari JSONArray
                    // below code for loop JSONObject in JSONArray
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val quote = jsonObject.getString("en")
                        val author = jsonObject.getString("author")
                        listQuote.add("\n$quote\n — $author\n")
                    }
                    val adapter = ArrayAdapter(this@ListQuoteActivity, android.R.layout.simple_list_item_1, listQuote)
                    listQuotes.adapter = adapter
                } catch (e: Exception) {
                    Toast.makeText(this@ListQuoteActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                // Jika koneksi gagal
                progressBar.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@ListQuoteActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
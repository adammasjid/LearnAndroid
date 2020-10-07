package com.example.myviewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.text.DecimalFormat

/*
* Dengan menambahkan turunan kelas ViewModel ke kelas MainViewModel menandakan bahwa kelas tersebut sebagai kelas ViewModel.
  Segala sesuatu yang ada di kelas tersebut akan terjaga selama Activity masih dalam keadaan aktif. Pada kelas MainViewModel,
  listWeathers akan selalu dipertahankan selama kelas tersebut masih terikat dengan Activity.
 */

class MainViewModel : ViewModel() {
    // Perhatikan kode dibawah. Terdapat LiveData dan MutableLiveData. Lalu apa bedanya?
    // Keduanya sebenarnya mirip, namun bedanya MutableLiveData bisa kita ubah value-nya,
    // sedangkan LiveData bersifat read-only.
    private val listWeathers = MutableLiveData<ArrayList<WeatherItems>>()

    fun setWeather(cities: String) {
        val listItems = ArrayList<WeatherItems>()

        val apiKey = "ffd23980041058544ccc9b91f47834f3"
        val url = "https://api.openweathermap.org/data/2.5/group?id=$cities&units=metric&appid=${apiKey}"

        val client = AsyncHttpClient()
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                try {
                    //parsing json
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("list")

                    for (i in 0 until list.length()) {
                        val weather = list.getJSONObject(i)
                        val weatherItems = WeatherItems()
                        weatherItems.id = weather.getInt("id")
                        weatherItems.name = weather.getString("name")
                        weatherItems.currentWeather = weather.getJSONArray("weather").getJSONObject(0).getString("main")
                        weatherItems.description = weather.getJSONArray("weather").getJSONObject(0).getString("description")
                        val tempInKelvin = weather.getJSONObject("main").getDouble("temp")
                        val tempInCelsius = tempInKelvin - 273
                        weatherItems.temperature = DecimalFormat("##.##").format(tempInCelsius)
                        listItems.add(weatherItems)
                    }

                    // dibawah code Yang dimaksud untuk mengubah value-nya adalah pada bagian ini dari MutableLiveData
                    listWeathers.postValue(listItems)
                    /*
                    * setValue()  Menetapkan sebuah nilai dari LiveData. Jika ada observer yang aktif,
                    *             nilai akan dikirim kepada mereka. Metode ini harus dipanggil dari main thread.
                    * postValue() Posting tugas ke main thread untuk menetapkan nilai yang diberikan dari background thread,
                                  seperti melalui dalam kelas MainViewModel. Jika Anda memanggil metode ini beberapa kali sebelum main thread menjalankan tugas yang di-posting, hanya nilai terakhir yang akan dikirim.
                    * getValue()  Mendapatkan nilai dari sebuah LiveData.
                    */
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }

            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    fun getWeathers(): LiveData<ArrayList<WeatherItems>> {
        return listWeathers
    }
}
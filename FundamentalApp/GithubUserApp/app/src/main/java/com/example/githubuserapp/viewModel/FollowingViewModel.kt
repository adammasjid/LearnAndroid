package com.example.githubuserapp.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.model.DataFollowing
import com.example.githubuserapp.model.DataUsers
import com.example.githubuserapp.view.FollowingFragment
import com.example.githubuserapp.view.MainActivity
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class FollowingViewModel: ViewModel() {

    private val listFollowingNonMutable = ArrayList<DataFollowing>()
    private val listFollowingMutable = MutableLiveData<ArrayList<DataFollowing>>()

    fun getListFollowing() : LiveData<ArrayList<DataFollowing>> {
        return listFollowingMutable
    }

    fun getDataGit(context: Context, id: String) {
        val httpClient = AsyncHttpClient()
        httpClient.addHeader("Authorization","fc6d9d964734f1e2dce354574f814dd45cdcc6b0")
        httpClient.addHeader("User-Agent", "request")
        val urlClient = "https://api.github.com/users/$id/following"

        httpClient.get(urlClient, object: AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray? )
            {
                val result = responseBody?.let { String(it) }
                AsyncHttpClient.log.d(FollowingFragment.TAG, result)
                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val usernameLogin = jsonObject.getString("login")
                        getDataGitDetail(usernameLogin, context)
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable? )
            {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun getDataGitDetail (usernameLogin: String, context: Context) {
        val httpClient = AsyncHttpClient()
        httpClient.addHeader("Authorization","fc6d9d964734f1e2dce354574f814dd45cdcc6b0")
        httpClient.addHeader("User-Agent","request")
        val urlClient = "https://api.github.com/users/$usernameLogin"

        httpClient.get(urlClient, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray? )
            {
                val result = responseBody?.let { String(it) }
                AsyncHttpClient.log.d(MainActivity.TAG , result)
                try {
                    val jsonObject = JSONObject(result)
                    val usersData = DataFollowing()
                    usersData.avatar =jsonObject.getString("avatar_url")
                    usersData.name = jsonObject.getString("name")
                    usersData.username = jsonObject.getString("login")
                    usersData.repository = jsonObject.getString("public_repos")
                    usersData.location = jsonObject.getString("location")
                    usersData.company = jsonObject.getString("company")
                    usersData.followers = jsonObject.getString("followers")
                    usersData.following = jsonObject.getString("following")
                    listFollowingNonMutable.add(usersData)
                    listFollowingMutable.postValue(listFollowingNonMutable)
                } catch (e: Exception) {
                    Toast.makeText(context, e.message , Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable? )
            {
                val errorMessage = when(statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(context, errorMessage , Toast.LENGTH_SHORT).show()
            }
        })
    }

}
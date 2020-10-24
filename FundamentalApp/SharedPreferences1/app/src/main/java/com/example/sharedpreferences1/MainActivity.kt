package com.example.sharedpreferences1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val PREFS_NAME = "BelajarSharedPreferences"
    val KEY_EMAIL = "key.email"
    val KEY_PASSWORD = "key.password"
    private lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // method getSharedPreferences() yang berfungsi untuk membuat sebuah file Shared Preference yang baru.
        sharedPreference = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        textView_email.text = getEmail()
        textView_password.text = getPassword()
    }

    fun onLogout(view: View) {
        clearData()
        startActivity(Intent( this, LoginActivity::class.java ))
    }

    @SuppressLint("CommitPrefEdits")
    fun clearData () {
        val editor = sharedPreference.edit()
        editor.clear()
        editor.apply()
    }

    private fun getEmail(): String? = sharedPreference.getString(KEY_EMAIL, null)

    private fun getPassword(): String? = sharedPreference.getString(KEY_PASSWORD, null)
}
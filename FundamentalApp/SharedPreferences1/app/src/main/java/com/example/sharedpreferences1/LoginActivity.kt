package com.example.sharedpreferences1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val PREFS_NAME = "BelajarSharedPreferences"
    val KEY_EMAIL = "key.email"
    val KEY_PASSWORD = "key.password"
    private lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // method getSharedPreferences() yang berfungsi untuk membuat sebuah file Shared Preference yang baru.
        sharedPreference = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun onLogin (view: View) {
        val email: String = input_email.text.toString()
        saveEmail(email)
        val password: String = input_password.text.toString()
        savePassword(password)
        msg()
    }

    @SuppressLint("CommitPrefEdits")
    private fun saveEmail (email: String) {
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(KEY_EMAIL, email)
        editor.apply()
    }

    @SuppressLint("CommitPrefEdits")
    private fun savePassword (pass: String) {
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(KEY_PASSWORD, pass)
        editor.apply()
    }

    private fun msg () {
        val email: String = input_email.text.toString()
        val password: String = input_password.text.toString()
        when {
            email == "" -> {
                val msg = Toast.makeText(applicationContext , "Masukan Email", Toast.LENGTH_LONG)
                msg.setGravity(Gravity.TOP, 0,140)
                msg.show()
            }
            password == "" -> {
                val msg = Toast.makeText(applicationContext , "Masukan Password", Toast.LENGTH_LONG)
                msg.setGravity(Gravity.TOP, 0,140)
                msg.show()
            }
            else -> {
                val msg = Toast.makeText(applicationContext , " Berhasil Login", Toast.LENGTH_LONG)
                msg.setGravity(Gravity.TOP, 0,140)
                msg.show()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}
package com.example.TestApp.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.TestApp.R

// TODO : saat menerapkan deepLink harus menuliskan ini di manifest
/**
 * @code :
 * <nav-graph android:value="@navigation/main_navigation" />
 * tambahkan didalam tag main activity dari app fragment
 */

class MyNavigation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_navigation)

    }
}
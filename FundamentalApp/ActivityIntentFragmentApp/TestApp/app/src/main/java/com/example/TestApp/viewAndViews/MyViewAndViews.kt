package com.example.TestApp.viewAndViews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.TestApp.R

class MyViewAndViews : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_view_and_views)

        // below object dari layout_seller bawaan android
        supportActionBar?.title = "Google Pixel"
    }
}
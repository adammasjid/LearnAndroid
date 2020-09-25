package com.example.TestApp.viewAndViews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.TestApp.R

class MyConstraintView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_constraint_view)

        supportActionBar?.title = " Google Pixel "
    }
}
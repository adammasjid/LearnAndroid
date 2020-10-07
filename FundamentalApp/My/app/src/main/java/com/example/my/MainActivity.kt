package com.example.my

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_move.setOnClickListener {
            if (it.id == R.id.btn_move) {
                val moveToDetailActivity = Intent(this@MainActivity, DetailActivity::class.java)
                startActivity(moveToDetailActivity)
            }

        }

    }
}
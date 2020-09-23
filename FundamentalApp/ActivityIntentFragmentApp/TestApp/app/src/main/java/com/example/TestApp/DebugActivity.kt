package com.example.TestApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DebugActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnSetValue: Button
    private lateinit var tvText: TextView
    private lateinit var imgPreview: ImageView
    private var names: ArrayList<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)

        tvText = findViewById(R.id.tv_text)
        btnSetValue = findViewById(R.id.btn_set_value)
        names.add("Adam")
        names.add("Masjid")
        names.add("Fajar")
        btnSetValue.setOnClickListener(this)
        imgPreview = findViewById(R.id.img_preview)
//        imgPreview.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.fronalpstock_big))
                                                // resource file name must be used lower case
        Glide.with(this).load(R.drawable.fronalpstock_big).into(imgPreview)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_set_value) {
            val name = StringBuilder()
            for (i in 0..2) {
                name.append(names[i]).append("\n")
            }
            tvText.text = name.toString()
        }
    }
}
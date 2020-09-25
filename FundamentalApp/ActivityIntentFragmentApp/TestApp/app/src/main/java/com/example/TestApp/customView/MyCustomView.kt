package com.example.TestApp.customView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.TestApp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_my_custom_view.*

class MyCustomView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_custom_view)

        // Melakukan pengecekan saat pertama kali activity terbentuk
        setMyButtonEnable()

        // Menambahkan metode ketika text terjadi perubahan
        my_edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        // Menambahkan aksi klik kepada button
        my_button.setOnClickListener { Toast.makeText(this@MyCustomView, my_edit_text.text, Toast.LENGTH_SHORT).show() }
    }

    // Metode untuk mengubah disable dan enable pada button
    private fun setMyButtonEnable() {
        val result = my_edit_text.text
        my_button.isEnabled = result != null && result.toString().isNotEmpty()
    }
}
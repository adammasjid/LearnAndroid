package com.example.intentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

/**
 * Jika terjadi error diluar syntax error dan nullpointer, kemungkinan error terjadi karena,
 * Activity belum terdaftar di manifest
 */
/* open the Manifest, there's note there*/

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)
        val btnMoveWithDataActivity: Button = findViewById(R.id.btn_move_activity_data)
        btnMoveWithDataActivity.setOnClickListener(this)
        val btnDialNumber: Button = findViewById(R.id.btn_dial_number)
        btnDialNumber.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) { // .id == getId() on java
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }
            // below is explicit intent
            R.id.btn_move_activity_data -> {
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Adam Masjid Fajar P" )
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 23)
                startActivity(moveWithDataIntent)
            }
            // below is implicit intent
            R.id.btn_dial_number -> {
                var phoneNumber = "081299224589"
                val dialNumber = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialNumber)
                /**
                 * @link https://developer.android.com/reference/android/content/Intent
                 * for detail of intent built in Android
                 * @Uri = (Uniform Resource Identifier)
                 * @link https://developer.android.com/guide/components/intents-common.html
                 * @link2 https://www.dicoding.com/academies/51/tutorials/1200?from=1197
                 * for detail of Uri
                 */
            }
        }
    }
}
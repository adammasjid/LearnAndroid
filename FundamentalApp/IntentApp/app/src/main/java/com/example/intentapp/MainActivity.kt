package com.example.intentapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Jika terjadi error diluar syntax error dan nullpointer, kemungkinan error terjadi karena,
 * Activity belum terdaftar di manifest
 */
/* open the Manifest, there's note there*/

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvResult: TextView
    companion object {
        private const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)
        val btnMoveWithDataActivity: Button = findViewById(R.id.btn_move_activity_data)
        btnMoveWithDataActivity.setOnClickListener(this)
        val btnDialNumber: Button = findViewById(R.id.btn_dial_number)
        btnDialNumber.setOnClickListener(this)
        val btnMoveBarVolume: Button = findViewById(R.id.btn_barVolume)
        btnMoveBarVolume.setOnClickListener(this)
        val btnMoveWithObjectActivity: Button = findViewById(R.id.btn_move_activity_object)
        btnMoveWithObjectActivity.setOnClickListener(this)
        val btnMoveForResult:Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)

        tvResult= findViewById(R.id.tv_result)
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
            R.id.btn_barVolume -> {
                val moveToBarVolume = Intent(this@MainActivity, BarVolume::class.java)
                startActivity(moveToBarVolume);
            }
            R.id.btn_move_activity_object -> {
                val person = Person (
                    name = "Adam",
                    age = 23,
                    email = "adam@gmail.com",
                    city = "jakarta"
                )
                val moveWithObjectIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObjectIntent)
            }
            R.id.btn_move_for_result -> {
                val moveForResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                // below Intent method for return value
                startActivityForResult(moveForResultIntent, REQUEST_CODE)
            }
        }
    }

    /**
     * Ketika MoveForResultActivity telah tertutup sempurna,
     * maka metode onActivityResult() pada MainActivity akan dijalankan.
     * @onActivityResult adalah method bawaan
     */
    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // pada real case kombinasi antara request code dan result code akan diperlukan untuk activity yang merespon banyak skenario
        if (requestCode == REQUEST_CODE) {
            if (resultCode == MoveForResultActivity.RESULT_CODE) {
                val selectedValue = data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
                tv_result.text = "Hasil : $selectedValue"
            }
        }
    }
}
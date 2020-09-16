package com.example.intentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_move_for_result.*

class MoveForResultActivity : AppCompatActivity() {
    private lateinit var btnChoose: Button
    private lateinit var rgNumber: RadioGroup

    companion object {
        const val EXTRA_SELECTED_VALUE = "extra_selected_value"
        const val RESULT_CODE = 140 // penentuan value dibebaskan, tapi ini adalah best practice
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_for_result)

        // catch id from layout for this class
        rgNumber = findViewById(R.id.rg_number)

        btnChoose = findViewById(R.id.btn_choose)
        btnChoose.setOnClickListener {
            if (it.id == R.id.btn_choose) {
                if (rg_number.checkedRadioButtonId != 0) {
                    var value = 0
                    when (rg_number.checkedRadioButtonId) {
                        R.id.rb_50 -> value = 50
                        R.id.rb_100 -> value = 100
                        R.id.rb_150 -> value = 150
                        R.id.rb_200 -> value = 200
                    }
                    val resultIntent = Intent()
                                // putExtra(key, value)
                    resultIntent.putExtra(EXTRA_SELECTED_VALUE, value)
                                // setResult(key, intent())
                    setResult(RESULT_CODE, resultIntent)
                    finish() // method pada lifeCyc for closing activity
                }
            }
        }
    }
}
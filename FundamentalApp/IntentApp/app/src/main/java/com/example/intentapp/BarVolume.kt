package com.example.intentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_bar_volume.*
import java.lang.NumberFormatException
import java.text.DecimalFormat

class BarVolume : AppCompatActivity() {
    private lateinit var edtWidth: EditText
    private lateinit var edtLength: EditText
    private lateinit var edtHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var result: TextView
    companion object { // ini menggunakan konsep key value
        private const val STATE_RESULT = "state_result"
    }

    // state dibawah ini untuk menyimpan data pada saat terdari perubahan orientasi
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tv_result.text.toString())
        /** penjelasan code diatas
         * @param1 = key yang dibuat dengan companion object
         * @param2 = value / hasil dari calculate
         * @NOTE: Method onSaveInstanceState will be called automatically before an Activity onDestroy().
        karena pada saat orientation changing activity akan menjalankan state onDestroy()
         */
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // for set layout
        setContentView(R.layout.activity_bar_volume)

        edtWidth = findViewById(R.id.edt_width)
        edtLength = findViewById(R.id.edt_length)
        edtHeight = findViewById(R.id.edt_height)
        btnCalculate = findViewById(R.id.btn_caclculate)
        result = findViewById(R.id.tv_result)

        //  below for show the data if changing orientation
        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tv_result.text = result
        }

        btnCalculate.setOnClickListener {
            if (it.id == R.id.btn_caclculate) {
                val inputLength = edtLength.text.toString().trim() // trim() = to ignore spacing
                val inputHeight = edtHeight.text.toString().trim()
                val inputWidth = edtWidth.text.toString().trim()

                //algorithm for hint of error
                var isEmptyFields = false
                var isInvalidDouble = false
                var isZeroInput = false
                when {
                    inputLength.isEmpty() -> {
                        isEmptyFields = true
                        edtLength.error = "Field ini tidak boleh kosong"
                    }
                    inputLength == "0" -> {
                        isZeroInput = true
                        edtLength.error = "Field harus berisi lebih dari 0"
                    }
                    inputWidth.isEmpty() -> {
                        isEmptyFields = true
                        edtWidth.error = "Field ini tidak boleh kosong"
                    }
                    inputWidth == "0" -> {
                        isZeroInput = true
                        edtWidth.error = "Field harus berisi lebih dari 0"
                    }
                    inputHeight.isEmpty() -> {
                        isEmptyFields = true
                        edtHeight.error = "Field ini tidak boleh kosong"
                    }
                    inputHeight == "0" -> {
                        isZeroInput = true
                        edtHeight.error = "Field harus berisi lebih dari 0"
                    }
                }
                val length = toDouble(inputLength)
                val width = toDouble(inputWidth)
                val height = toDouble(inputHeight)

                when {
                    length == null -> {
                        isInvalidDouble = true
                        edtLength.error = "Field ini harus berupa nomer yang valid"
                    }
                    width == null -> {
                        isInvalidDouble = true
                        edtWidth.error = "Field ini harus berupa nomer yang valid"
                    }
                    height == null -> {
                        isInvalidDouble = true
                        edtHeight.error = "Field ini harus berupa nomer yang valid"
                    }
                }
                if (!isEmptyFields && !isInvalidDouble && !isZeroInput) {
                    val formatter: DecimalFormat = DecimalFormat("#.####")
                    val volume = inputWidth.toDouble() * inputLength.toDouble() * inputHeight.toDouble()
                    tv_result.text = formatter.format(volume).toString()
                }
            }
        }
    }

    private fun toDouble (str: String): Double? {
        return try {
            // on kotlin, keyword return has been set by default on exception
            str.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }
}
package com.example.TestApp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import com.dicoding.picodiploma.myflexiblefragment.DetailCategoryFragment
import com.example.TestApp.R
import kotlinx.android.synthetic.main.fragment_option_dialog.*


class OptionDialogFragment : DialogFragment(), View.OnClickListener {
                          // inherit with DialogFragment() untuk
    /**
     * @DialogFragment()
     * Saat menggunakan [DialogFragment] Dengan begitu obyek fragment sekarang merupakan obyek dialog yang akan tampil mengambang di layout_seller.
     * Seperti pada obyek modal pada platform lain, obyek DialogFragment dapat disesuaikan tampilan dan fungsinya secara spesifik.
     * Di sini kita menampillkan sebuah dialog ke pengguna untuk memilih sebuah opsi yang tersedia
     */

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }

    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var rgOptions: RadioGroup
    private lateinit var rbSaf: RadioButton
    private lateinit var rbMou: RadioButton
    private lateinit var rbLvg: RadioButton
    private lateinit var rbMoyes: RadioButton
    private var optionDialogListener: OnOptionDialogListener? = null // property dari interface

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose = view.findViewById(R.id.btn_choose)
        btnChoose.setOnClickListener(this)
        btnClose = view.findViewById(R.id.btn_close)
        btnClose.setOnClickListener(this)
        rgOptions = view.findViewById(R.id.rg_options)
        rbSaf = view.findViewById(R.id.rb_saf)
        rbLvg = view.findViewById(R.id.rb_lvg)
        rbMou = view.findViewById(R.id.rb_mou)
        rbMoyes = view.findViewById(R.id.rb_moyes)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment

        if (fragment is DetailCategoryFragment) {
            this.optionDialogListener = fragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_close -> dialog?.cancel()

            R.id.btn_choose -> {
                val checkedRadioButtonId = rg_options.checkedRadioButtonId
                if (checkedRadioButtonId != -1) {
                    var coach: String? = null
                    when (checkedRadioButtonId) {
                        R.id.rb_saf -> coach = rbSaf.text.toString().trim()

                        R.id.rb_mou -> coach = rbMou.text.toString().trim()

                        R.id.rb_lvg -> coach = rbLvg.text.toString().trim()

                        R.id.rb_moyes -> coach = rbMoyes.text.toString().trim()
                    }
                    // below bentuk penggunaan interface
                    if (optionDialogListener != null) {
                        // Jadi ketika pengguna menekan tombol Pilih setelah memilih salah satu pilihan, baris ini akan dieksekusi:
                        optionDialogListener?.onOptionChosen(coach)
                                      // onOptionChosen adalah inline function yang dibuat di fragment@DetailCategoryFragment
                    }
                    dialog?.dismiss()
                }
            }
        }
    }

}
package com.example.intentapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.example.intentapp.R

class HomeFragment : Fragment(), View.OnClickListener {

    // method ini untuk layout interface yang didefinisikan dan ditransformasikan dari layout berupa file xml ke dalam objek view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
        /**
         * @Explaining
         * inflater.inflate() merupakan objek dari LayoutInflater yang berfungsi untuk ...
         * mengubah layout xml ke dalam bentuk objek viewer oup atau widget melalui pemanggilan metode inflate()
         * fungsi inflate di sini yaitu untuk menampilkan layout dari Fragment,
         * di mana layout yang ditampilkan di sini yaitu fragment_home
         */
    }
    /**
     *  metode onViewCreated() yang akan bekerja setelah metode onCreateView().
     *  Di sini kita bisa gunakan untuk inisialisasi view, dan juga mengatur action-nya (set listener).
     */
    // Penyesuaian kode pada Fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnCategory: Button = view.findViewById(R.id.btn_category)
                                // menggunakan view karena btnCategory berada pada object view bukan root layout
        btnCategory.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        // TODO("Not yet implemented")
        if (v.id == R.id.btn_category) {
            /**
             * Berbeda dengan Activity yang memanfaatkan supportFragmentManager,
             * Fragment menggunakan [fragmentManager] untuk mendapatkan FragmentManager.
             * Setelah mendapatkannya, Anda dapat memulai transaksi pergantian fragment.
             */
            val mFragmentManager = fragmentManager

            val mCategoryFragment = CategoryFragment()
            mFragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_container, mCategoryFragment, CategoryFragment::class.java.simpleName) // menggunakan method replace
                addToBackStack(null) // method untuk kembali ke fragment sebelumnya
                commit()
            }
        }
    }

}

package com.example.TestApp.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.TestApp.R

class FlexibleFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flexible_fragment)

        /**
         * menggunakan instance dari FragmentManager yang merupakan antarmuka untuk...
         * mengorganisir objek fragment yang terdapat didalam sebuah activity.
         * FragmentTransaction merupakan fungsi untuk melakukan operasi pada fragment...
         * seperti add(), replace(), commit() dsb.
         */
        val mFragmentManager = supportFragmentManager

        // Di sinilah proses manipulasi penambahan fragment ke dalam activity terjadi.
        val mHomeFragment = HomeFragment()
        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)
        if (fragment !is HomeFragment) {
            Log.d("MyFlexibleFragment", "Fragment Name :" + HomeFragment::class.java.simpleName)
            /** cara dibawah ini disebut juga [RXOperator] **/
            mFragmentManager
                .beginTransaction() // method ini untuk memulai proses perubahan.
                .add(R.id.frame_container, mHomeFragment, HomeFragment::class.java.simpleName) // akan menambahkan objek fragment ke dalam layout container. Layout container ini merupakan objek framelayout dengan ID frame_container
                .commit() // akan mengeksekusi untuk melakukan pemasangan objek secara asynchronous untuk ditampilkan ke antar muka pengguna (user interface).
        }
    }
}
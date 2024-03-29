package com.example.TestApp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.dicoding.picodiploma.myflexiblefragment.DetailCategoryFragment
import com.example.TestApp.R

class CategoryFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout_seller for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnDetailCategory: Button = view.findViewById(R.id.btn_detail_category)
        btnDetailCategory.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_detail_category) {
            // below for sending data via bundle
            val mDetailCategoryFragment = DetailCategoryFragment()
            val mBundle = Bundle() // object bundle
            mBundle.putString(DetailCategoryFragment.EXTRA_NAME, "Lifestyle") // mengirim data melalui object bundle
            val description = "Kategori ini akan berisi produk-produk lifestyle"
            mDetailCategoryFragment.arguments = mBundle // .arguments adalah property bawaan kotlin
            mDetailCategoryFragment.description = description
            val mFragmentManager = fragmentManager
            mFragmentManager?.beginTransaction()?.apply {
                replace(
                    R.id.frame_container,
                    mDetailCategoryFragment,
                    DetailCategoryFragment::class.java.simpleName
                )
                addToBackStack(null)
                commit()
            }
        }
    }
}
package com.example.TestApp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.TestApp.R
import kotlinx.android.synthetic.main.fragment_detail_category.*

class DetailCategoryFragment : Fragment(), View.OnClickListener {

    private lateinit var tvCategoryName: TextView
    private lateinit var tvCategoryDescription: TextView
    private lateinit var btnProfile: Button
    private lateinit var btnShowDialog: Button
    // below adalah setter untuk untuk bisa ditangkap pada fragment lain
    var description: String? = null
    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_DESCRIPTION = "extra_description"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCategoryName = view.findViewById(R.id.tv_category_name)
        tvCategoryDescription = view.findViewById(R.id.tv_category_description)
        btnProfile = view.findViewById(R.id.btn_profile)
        btnProfile.setOnClickListener(this)
        btnShowDialog = view.findViewById(R.id.btn_show_dialog)
        btnShowDialog.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            val descFromBundle = savedInstanceState.getString(EXTRA_DESCRIPTION)
            description = descFromBundle
        }
        if (arguments != null) {
            val categoryName = arguments?.getString(EXTRA_NAME)
            tv_category_name.text = categoryName
            tv_category_description.text = description // this is the getter
        }
    }

    override fun onClick(v: View){
        when (v.id){
            R.id.btn_profile -> {
                val mIntent = Intent(activity, ProfileActivity::class.java)
                                    /**
                                     * @param1 menggunakan value [activity] karena ini halaman ini adalah fragment
                                     */
                startActivity(mIntent)
            }
            R.id.btn_show_dialog -> {
                val mOptionDialogFragment = OptionDialogFragment()
                val mFragmentManager = childFragmentManager
                mOptionDialogFragment.show(mFragmentManager, OptionDialogFragment::class.java.simpleName)
                /**
                 * @getChildFragmentManager() merupakan pilihan yang tepat untuk kondisi saat ini,
                 * yakni sebuah nested fragment / fragment bersarang. Karena OptionDialogFragment dipanggil di dalam sebuah obyek fragment yang telah ada sebelumnya yaitu DetailDialogFragment,
                 * maka demi performa lebih baik gunakanlah getChildFragmentManager() sebagai pilihan yang lebih tepat.
                 * fragment ini akan terlihat seperti tidak berubah halaman sama sekali
                 */
            }
        }
    }

    // ini adalah internal variable
    // dan ini adalah bentuk implementation from interface in Fragment@OptionDialogFragment
    internal var optionDialogListener: OptionDialogFragment.OnOptionDialogListener = object : OptionDialogFragment.OnOptionDialogListener {
        override fun onOptionChosen(text: String?) {
            Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
        }
    }

}
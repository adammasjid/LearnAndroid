package com.example.TestApp.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat.Token.fromBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.Person.fromBundle
import androidx.media.AudioAttributesCompat.fromBundle
import androidx.navigation.Navigation
import com.example.TestApp.R
import kotlinx.android.synthetic.main.fragment_detail_category_navigation.*

class DetailCategoryNavigationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category_navigation, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val dataName = arguments?.getString(CategoryNavigationFragment.EXTRA_NAME)
//        val dataDescription = arguments?.getLong(CategoryNavigationFragment.EXTRA_STOCK)

        val dataName = DetailCategoryNavigationFragmentArgs.fromBundle(arguments as Bundle).name
        val dataDescription = DetailCategoryNavigationFragmentArgs.fromBundle(arguments as Bundle).stock
        tv_category_name.text = dataName
        tv_category_description.text = "Stock : $dataDescription"

        btn_profile_fragment.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_detailCategoryNavigationFragment_to_navigationFragment)
        )

    }

}
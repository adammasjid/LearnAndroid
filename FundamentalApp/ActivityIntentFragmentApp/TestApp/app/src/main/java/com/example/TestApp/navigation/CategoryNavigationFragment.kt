package com.example.TestApp.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.TestApp.R
import kotlinx.android.synthetic.main.fragment_category_navigation.*
import kotlinx.android.synthetic.main.fragment_detail_category_navigation.*

class CategoryNavigationFragment : Fragment() {
    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_STOCK = "extra_stock"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            btn_category_lifestyle.setOnClickListener { view ->
                // TODO below send data with bundle
//              val mBundle = Bundle()
//              mBundle.putString(EXTRA_NAME, "Lifestyle")
//              mBundle.putLong(EXTRA_STOCK, 7)
//              view.findNavController().navigate(R.id.action_categoryNavigationFragment_to_detailCategoryNavigationFragment, mBundle)
                // TODO below send data with safe args
                val toDetailCategoryFragmentNavigation = CategoryNavigationFragmentDirections.actionCategoryNavigationFragmentToDetailCategoryNavigationFragment()
                toDetailCategoryFragmentNavigation.name = "Bacot"
                toDetailCategoryFragmentNavigation.stock = 7
                view.findNavController().navigate(toDetailCategoryFragmentNavigation)
        }
    }
}
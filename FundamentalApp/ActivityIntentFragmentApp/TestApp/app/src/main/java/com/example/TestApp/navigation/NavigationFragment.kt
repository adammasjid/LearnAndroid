package com.example.TestApp.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.TestApp.R
import kotlinx.android.synthetic.main.fragment_navigation.*


class NavigationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO : below for set on click listener after configuration on navigation layout
        // below move to fragment
        btn_category.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigationFragment_to_categoryNavigationFragment)
        )
        // below for move to activity
        btn_profile.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigationFragment_to_navigationProfile)
        }
    }
}
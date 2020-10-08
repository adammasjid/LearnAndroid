package com.example.githubuserapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.R
import com.example.githubuserapp.model.DataFollowers
import com.example.githubuserapp.model.DataUsers
import com.example.githubuserapp.viewModel.FollowersViewModel
import com.example.githubuserapp.viewModel.ListFollowersAdapter
import kotlinx.android.synthetic.main.fragment_followers.*

class FollowersFragment : Fragment() {

    companion object {
        val TAG = FollowersFragment::class.java.simpleName
        const val EXTRA_DETAIL = "Extra_Detail"
    }
    private val listData: ArrayList<DataFollowers> = ArrayList()
    private lateinit var adapter: ListFollowersAdapter
    private lateinit var followersViewModel: FollowersViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListFollowersAdapter(listData)
        followersViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(FollowersViewModel::class.java)

        val dataUser = activity!!.intent.getParcelableExtra <DataUsers> (EXTRA_DETAIL)
        config()

        followersViewModel.getDataGit(activity!!.applicationContext, dataUser?.username.toString())
        showLoading(true)

        followersViewModel.getListFollowers().observe(activity!!, { listFollower ->
            if (listFollower != null) {
                adapter.setData(listFollower)
                showLoading(false)
            }
        })
    }

    private fun config() {
        recyclerViewFollowers.layoutManager = LinearLayoutManager(activity)
        recyclerViewFollowers.setHasFixedSize(true)
        recyclerViewFollowers.adapter = adapter
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressbarFollowers.visibility = View.VISIBLE
        } else {
            progressbarFollowers.visibility = View.INVISIBLE
        }
    }

}
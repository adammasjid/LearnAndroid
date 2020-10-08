package com.example.githubuserapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.viewModel.adapter.UserViewAdapter
import com.example.githubuserapp.model.UserData
import com.example.githubuserapp.viewModel.adapter.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var list = ArrayList<UserData>()
    val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_user.setHasFixedSize(true)

        list.addAll(viewModel.initData(this))
        showRecycleCardView()

    }

    private fun showRecycleCardView() {
        rv_user.layoutManager = LinearLayoutManager(this)
        val cardViewAdapter = UserViewAdapter(list)
        rv_user.adapter = cardViewAdapter

        cardViewAdapter.setOnItemClickCallback(object: UserViewAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UserData) {
                val moveIntent1 = Intent(this@MainActivity, DetailActivity::class.java)
                moveIntent1.putExtra(DetailActivity.ITEM_EXTRA, data)
                startActivity(moveIntent1)
            }
        })
    }

}


package com.example.githubuserapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.adapter.UserViewAdapter
import com.example.githubuserapp.model.UserData
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var list = ArrayList<UserData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_user.setHasFixedSize(true)

        list.addAll(initData())
        showRecycleCardView()

    }

    @SuppressLint("Recycle")
    fun initData(): ArrayList<UserData> {
        val name = resources.getStringArray(R.array.name)
        val username = resources.getStringArray(R.array.username)
        val location = resources.getStringArray(R.array.location)
        val repository = resources.getStringArray(R.array.repository)
        val company = resources.getStringArray(R.array.company)
        val followers = resources.getStringArray(R.array.followers)
        val following = resources.getStringArray(R.array.following)
        val avatar = resources.obtainTypedArray(R.array.avatar)

        /* below harus berurutan dengan layout dan data class */
        val listUser = ArrayList<UserData>()
        for (position in name.indices) {
            val user = UserData(
                avatar.getResourceId(position,0 ),
                name[position],
                followers[position],
                following[position],
                username[position],
                company[position],
                location[position],
                repository[position]
            )
            listUser.add(user)
        }
        return listUser
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


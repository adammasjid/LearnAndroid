package com.example.githubuserapp.viewModel.adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.R
import com.example.githubuserapp.model.UserData

class MainViewModel : ViewModel() {

    @SuppressLint("Recycle")
    fun initData(context: Context): ArrayList<UserData> {
        val name = context.resources.getStringArray(R.array.name)
        val username = context.resources.getStringArray(R.array.username)
        val location = context.resources.getStringArray(R.array.location)
        val repository = context.resources.getStringArray(R.array.repository)
        val company = context.resources.getStringArray(R.array.company)
        val followers = context.resources.getStringArray(R.array.followers)
        val following = context.resources.getStringArray(R.array.following)
        val avatar = context.resources.obtainTypedArray(R.array.avatar)

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

}
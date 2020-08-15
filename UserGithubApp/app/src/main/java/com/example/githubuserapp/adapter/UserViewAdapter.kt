package com.example.githubuserapp.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuserapp.DetailActivity
import com.example.githubuserapp.R
import com.example.githubuserapp.model.UserData
import kotlinx.android.synthetic.main.item_card_user.view.*

class UserViewAdapter(private val userData: ArrayList<UserData>) : RecyclerView.Adapter<UserViewAdapter.UserViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewAdapter.UserViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewAdapter.UserViewHolder, position: Int) {
        holder.bind(userData[position])
    }

    override fun getItemCount(): Int {
        return userData.size
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(userData: UserData) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(userData.avatar)
                    .apply(RequestOptions())
                    .into(img_item_photo)
                tv_item_name.text = userData.name
                tv_item_username.text = "Github Username : \n@" + userData.username
                btn_set_detail.setOnClickListener { onItemClickCallback?.onItemClicked(userData)}
            }
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: UserData)
    }
}
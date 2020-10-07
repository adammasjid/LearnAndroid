package com.example.githubuserapp.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuserapp.R
import com.example.githubuserapp.model.DataFollowing
import com.example.githubuserapp.model.DataUsers
import kotlinx.android.synthetic.main.item_user.view.*

class ListFollowingAdapter (private val listDataFollowing: ArrayList<DataFollowing>)
    : RecyclerView.Adapter<ListFollowingAdapter.ListFollowingViewHolder>() {

    fun setData(items: ArrayList<DataFollowing>) {
        listDataFollowing.clear()
        listDataFollowing.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListFollowingViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
        fun bind (followingData: DataFollowing) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(followingData.avatar)
                    .apply(RequestOptions())
                    .into(img_item_photo)
                tv_item_name.text = followingData.name
                tv_item_username.text = followingData.username
            }
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ListFollowingAdapter.ListFollowingViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup , false)
        return ListFollowingViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ListFollowingAdapter.ListFollowingViewHolder,
        position: Int
    ) {
        holder.bind(listDataFollowing[position])
    }

    override fun getItemCount(): Int = listDataFollowing.size

}
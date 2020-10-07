package com.example.githubuserapp.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuserapp.R
import com.example.githubuserapp.model.DataFollowers
import kotlinx.android.synthetic.main.item_user.view.*

class ListFollowersAdapter (private val listDataFollower: ArrayList<DataFollowers>)
    : RecyclerView.Adapter<ListFollowersAdapter.ListFollowersViewHolder>() {

    fun setData(items: ArrayList<DataFollowers>) {
        listDataFollower.clear()
        listDataFollower.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListFollowersViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
        fun bind (followersData: DataFollowers) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(followersData.avatar)
                    .apply(RequestOptions())
                    .into(img_item_photo)
                tv_item_name.text = followersData.name
                tv_item_username.text = followersData.username
            }
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ListFollowersAdapter.ListFollowersViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup , false)
        return ListFollowersViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ListFollowersAdapter.ListFollowersViewHolder,
        position: Int
    ) {
        holder.bind(listDataFollower[position])
    }

    override fun getItemCount(): Int = listDataFollower.size
}
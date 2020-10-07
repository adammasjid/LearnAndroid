package com.example.githubuserapp.viewModel

import android.content.Intent
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuserapp.R
import com.example.githubuserapp.model.DataUsers
import com.example.githubuserapp.view.DetailActivity
import kotlinx.android.synthetic.main.item_user.view.*

class ListDataUsersAdapter (private val listDataUsersGithub: ArrayList<DataUsers>)
    : RecyclerView.Adapter<ListDataUsersAdapter.ListDataViewHolder>() {

    fun setData(items: ArrayList<DataUsers>) {
        listDataUsersGithub.clear()
        listDataUsersGithub.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListDataViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind (usersData: DataUsers) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(usersData.avatar)
                    .apply(RequestOptions())
                    .into(img_item_photo)
                tv_item_name.text = usersData.name
                tv_item_username.text = usersData.username
            }
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ListDataUsersAdapter.ListDataViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup , false)
        return ListDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListDataUsersAdapter.ListDataViewHolder, position: Int) {
        holder.bind(listDataUsersGithub[position])

        val data = listDataUsersGithub[position]
        holder.itemView.setOnClickListener{
            val dataUserIntent = DataUsers (
                data.avatar,
                data.name,
                data.username,
                data.repository,
                data.location,
                data.company,
                data.followers,
                data.following
            )
            val mIntent = Intent (it.context , DetailActivity::class.java)
            mIntent.putExtra(DetailActivity.EXTRA_DETAIL, dataUserIntent)
            it.context.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int = listDataUsersGithub.size

}
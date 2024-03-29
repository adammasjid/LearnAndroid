package com.example.popularprogramminglanguage.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.popularprogramminglanguage.DetailActivity
import com.example.popularprogramminglanguage.R
import com.example.popularprogramminglanguage.model.Language

class ListLangAdapter (private val listLang: ArrayList<Language>): RecyclerView.Adapter<ListLangAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListLangAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_card_lang, viewGroup,false )
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listLang.size
    }

    override fun onBindViewHolder(holder: ListLangAdapter.ListViewHolder, position: Int) {
        val (name, rank, photo, overview, identity) = listLang[position]

        Glide.with(holder.itemView.context)
            .load(photo)
            .apply(RequestOptions())
            .into(holder.imgPhoto)

        holder.tvName.text = name
        holder.tvRank.text = rank

        val mContext = holder.itemView.context

        holder.itemView.setOnClickListener {
            val moveDetail = Intent(mContext, DetailActivity::class.java)
            moveDetail.putExtra(DetailActivity.EXTRA_RANK, rank)
            moveDetail.putExtra(DetailActivity.EXTRA_NAME, name)
            moveDetail.putExtra(DetailActivity.EXTRA_PHOTO, photo)
            moveDetail.putExtra(DetailActivity.EXTRA_IDENTITY, identity)
            moveDetail.putExtra(DetailActivity.EXTRA_OVERVIEW, overview)
            mContext.startActivity(moveDetail)
        }
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvRank: TextView = itemView.findViewById(R.id.tv_item_rank)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }
}
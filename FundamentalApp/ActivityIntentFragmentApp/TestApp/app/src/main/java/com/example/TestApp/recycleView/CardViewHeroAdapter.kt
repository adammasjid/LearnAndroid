package com.example.TestApp.recycleView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.TestApp.R
import com.example.TestApp.model.Hero
import kotlinx.android.synthetic.main.item_cardview_hero.view.*
import kotlinx.android.synthetic.main.item_grid_hero.view.*
import kotlinx.android.synthetic.main.item_grid_hero.view.img_item_photo

class CardViewHeroAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<CardViewHeroAdapter.CardViewViewHolder>() {

    inner class CardViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind (hero: Hero) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(hero.photo)
                    .apply(RequestOptions())
                    .into(img_item_photo)
                tv_item_name.text = hero.name
                tv_item_description.text = hero.description
                // EventListener
                btn_set_favorite.setOnClickListener { Toast.makeText(itemView.context, "Favorite ${hero.name}",Toast.LENGTH_SHORT).show() }
                btn_set_share.setOnClickListener { Toast.makeText(itemView.context, "Share ${hero.name}",Toast.LENGTH_SHORT).show() }
                itemView.setOnClickListener { Toast.makeText(itemView.context, "Kamu Memilih ${hero.name}",Toast.LENGTH_SHORT).show() }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardViewViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_cardview_hero,viewGroup,false )
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listHero[position])
    }

    override fun getItemCount(): Int = listHero.size
}
package com.example.TestApp.recycleView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.TestApp.R
import com.example.TestApp.model.Hero
import kotlinx.android.synthetic.main.item_row_hero.view.*

/**
 * @default adapter dari recyclerview harus lah mengimplementasikan [ViewHolderPattern]
 * tujuan dari pendekatan ini adalah agar recyclerview tetap responsif dengan mempertahankan peforma terbaik ketika menampilkan koleksi data dalam jumlah yang banyak.
 * cara menerapkan nya dengan best practice seperti below yaitu dengan melakukan extension dengan [ViewHolder]
 */
class ListHeroAdapter(private val listHero: ArrayList<Hero>): RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {

    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(hero: Hero) {
            with(itemView) {
                /**
                 * @with: digunakan untuk memasukkan context.
                 * @load: digunakan untuk memasukkan sumber gambar, contohnya menggunakan url.
                 * @apply: digunakan untuk melakukan memanipulasi gambar, contohnya menggunakan RequestOption untuk me-override ukuran gambar.
                 * @into: digunakan untuk memasukkan imageView sebagai obyek penampil gambar.
                 * @BestPractice nya bisa juga check di [https://github.com/bumptech/glide]
                 */
                Glide.with(itemView.context)
                    .load(hero.photo)
                    .apply(RequestOptions().override(55,55))
                    .into(img_item_photo)
                tv_item_name.text = hero.name
                tv_item_description.text = hero.description
                itemView.setOnClickListener{ onItemClickCallback?.onItemClicked(hero) }
            }
        }
    }

    /**
     * Setiap kali kita melakukan scroll pada RecyclerView,
     * ia akan memeriksa memori apakah item view yang hendak ditampilkan tertentu sudah berada di memori  atau belum.
     * Jika belum, maka akan dijalankan sebuah proses yang cukup mahal dari segi memori, yaitu dijalankannya onCreateViewHolder().
     * Di dalam metode ini, terjadi sebuah [CastingView] (findViewById) yang akan menampilkan koleksi data dalam format tampilan yang ditentukan,
     * baris per baris jika pada bentuk list atau baris dan kolom pada bentuk grid.
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_hero, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHero[position])
    }

    override fun getItemCount(): Int = listHero.size

}
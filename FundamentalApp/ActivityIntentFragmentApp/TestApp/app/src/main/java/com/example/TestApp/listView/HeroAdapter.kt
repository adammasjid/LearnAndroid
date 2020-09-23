package com.example.TestApp.listView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.TestApp.R
import com.example.TestApp.model.Hero
import kotlinx.android.synthetic.main.item_hero.view.*

class HeroAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var heroes = arrayListOf<Hero>()
    //below is method from base adapter
    /** @getCount digunakan untuk mengetahui berapa banyak item yang akan ditampilkan **/
    override fun getCount(): Int = heroes.size
    //below is method from base adapter
    override fun getItem(i: Int): Any = heroes[i]
    //below is method from base adapter
    override fun getItemId(i: Int): Long = i.toLong()
    //below is method from base adapter
    /**
     * @getView
     * digunakan untuk memanggil layout item xml yang sudah dibuat dan ...
     * melakukan proses manipulasi setiap komponennya seperti textview dan imageview melalui kelas ViewHolder.
     */
    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
        var itemView = view
        if (itemView == null) {
            //Menghubungkan ViewHolder dengan View
            itemView = LayoutInflater.from(context).inflate(R.layout.item_hero, viewGroup, false)
        }

        val viewHolder = ViewHolder(itemView as View)

        //Mengubah nilai pahlawan sesuai dari posisinya
        val hero = getItem(position) as Hero
        viewHolder.bind(hero)
        return itemView
    }
    // dibawah adalah inner class untuk casting object pada layout
     private inner class ViewHolder constructor(private val view: View) {
        fun bind(hero: Hero) {
            // ini menggunakan kotlin android extension
            // penggunaan with dibawah untuk menyederhanakan syntax tanpa perlu extensions dari param view
            with(view) {
                txt_name.text = hero.name
                txt_description.text = hero.description
                img_photo.setImageResource(hero.photo)
            }
        }
    }
}
package com.example.TestApp.listView

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.TestApp.R
import com.example.TestApp.model.Hero

class ListViewActivity : AppCompatActivity() {

    private lateinit var adapter: HeroAdapter
    private lateinit var dataName: Array<String>
    private lateinit var dataDescription: Array<String>
    private lateinit var dataPhoto: TypedArray
    private var heroes = arrayListOf<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val listView: ListView = findViewById(R.id.lv_list)

                // class hero adapter yang berparamenter context
        adapter = HeroAdapter(this)

        listView.adapter = adapter

        //Menyipakan data dari resource
        prepare()

        //Menambahkan data dari resource ke adapter
        addItem()

        //Memberi aksi pada lv_list
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(this@ListViewActivity, heroes[position].name, Toast.LENGTH_SHORT).show()
        }

    }

    private fun prepare() { // Metode prepare digunakan untuk inisiasi setiap data.
        dataName = resources.getStringArray(R.array.data_name)
        dataDescription = resources.getStringArray(R.array.data_description)
        dataPhoto = resources.obtainTypedArray(R.array.data_photo)
    }

    private fun addItem() { // Metode ini digunakan untuk memasukan data data ke array list untuk bisa diproses oleh adapter.
        for (position in dataName.indices) {
            val hero = Hero(
                dataPhoto.getResourceId(position, -1),
                dataName[position],
                dataDescription[position]
            )
            heroes.add(hero)
        }
        adapter.heroes = heroes
    }
}
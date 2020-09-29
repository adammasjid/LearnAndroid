package com.example.TestApp.recycleView

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.TestApp.R
import com.example.TestApp.model.Hero
import kotlinx.android.synthetic.main.activity_my_recycle_view.*
import kotlinx.android.synthetic.main.item_cardview_hero.*

// TODO : PENJELASAN LENGKAP TENTANG ADAPTER BISA DI CHECK DI CLASS ListHeroAdapter

class MyRecycleView : AppCompatActivity() {

    private val list = arrayListOf<Hero>()
    private var title = "Mode List"
    private var mode: Int = 0
    companion object {
        private const val STATE_TITLE = "state_string"
        private const val STATE_LIST = "state_list"
        private const val STATE_MODE = "state_mode"
    }

    // below is function user defined for set the title of action bar
    private fun setActionBarTitle(name: String?) {
        supportActionBar?.title = name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_recycle_view)

        rv_heroes.setHasFixedSize(true) // bila fixed size bernilai true, maka recyclerview dapat melakukan optimasi ukuran lebar dan tinggi secara otomatis.

        // below is algorithm for save data if orientation display changed
        if (savedInstanceState == null ) {
            list.addAll(getListHeroes())
            showRecyclerList()
            setActionBarTitle(title)
            mode = R.id.action_list
        } else {
            title = savedInstanceState.getString(STATE_TITLE).toString()
            val stateList = savedInstanceState.getParcelableArrayList<Hero>(STATE_LIST)
            val stateMode = savedInstanceState.getInt(STATE_MODE)
            setActionBarTitle(title)
            if (stateList != null) {
                list.addAll(stateList)
            }
            setMode(stateMode)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_TITLE,title)
        outState.putParcelableArrayList(STATE_LIST,list)
        outState.putInt(STATE_MODE ,mode)
    }


    @SuppressLint("Recycle")
    fun getListHeroes(): ArrayList<Hero> {
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)

        for (position in dataName.indices) {
            val hero = Hero(
                dataPhoto.getResourceId(position, -1),
                dataName[position],
                dataDescription[position]
            )
            list.add(hero)
        }
        return list
    }

    // below is function user-Defined
    private fun showSelectedHero(hero: Hero) {
        Toast.makeText(this, "Kamu memilih ${hero.name}", Toast.LENGTH_SHORT).show()
    }

    private fun showRecyclerList() {
        // LayoutManager untuk menentukan bagaimana layout ditampilkan
        rv_heroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list) // parameter yang ber type data harus ditampung kedalam property
        rv_heroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object: ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }

    private fun showRecyclerGrid() {
        rv_heroes.layoutManager = GridLayoutManager(this,2)
        val gridHeroAdapter = GridHeroAdapter(list)
        rv_heroes.adapter = gridHeroAdapter

        gridHeroAdapter.setOnItemClickCallback(object :GridHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(hero: Hero) {
                showSelectedHero(hero)
            }
        })
    }

    private fun showRecyclerCardView() {
        rv_heroes.layoutManager = LinearLayoutManager(this)
        val cardViewHeroAdapter = CardViewHeroAdapter(list)
        rv_heroes.adapter = cardViewHeroAdapter
    }

    //below is method from AppCompatActivity for showing the menu main in this case
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu) // casting layout menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }//                 |
//                      |
//                      V
    // below is function which dependency with fun onCreateOptionsMenu
    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList()
            }
            R.id.action_grid -> {
                title = "Mode Grid"
                showRecyclerGrid()
            }
            R.id.action_cardview -> {
                title = "Mode Card View"
                showRecyclerCardView()
            }
        }
        mode = selectedMode
        setActionBarTitle(title)
    }
}
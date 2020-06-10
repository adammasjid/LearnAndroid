package com.example.popularprogramminglanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularprogramminglanguage.adapter.ListLangAdapter
import com.example.popularprogramminglanguage.model.LangData
import com.example.popularprogramminglanguage.model.Language

class MainActivity : AppCompatActivity() {
    private lateinit var rvLang: RecyclerView
    private var list: ArrayList<Language> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionbar = supportActionBar
        actionbar?.title= "Home"

        rvLang = findViewById(R.id.rv_univ)
        rvLang.setHasFixedSize(true)

        list.addAll(LangData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvLang.layoutManager = LinearLayoutManager(this)
        val listUnivAdapter = ListLangAdapter(list)
        rvLang.adapter = listUnivAdapter
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.miCompose -> {
                val iAbout = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(iAbout)
            }
        }
    }
}
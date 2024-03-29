package com.example.githubuserapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.R
import com.example.githubuserapp.model.DataUsers
import com.example.githubuserapp.viewModel.ListDataUsersAdapter
import com.example.githubuserapp.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var listDataUser: ArrayList<DataUsers> = ArrayList()
    private lateinit var listAdapter: ListDataUsersAdapter
    private lateinit var mainViewModel: MainViewModel

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listAdapter = ListDataUsersAdapter(listDataUser)
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MainViewModel::class.java)

        showTitleActionBar()
        searchData()
        showRecycleView()
        runGetDataGit()
        configMainViewModel(listAdapter)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.change_language) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)

        }
        return super.onOptionsItemSelected(item)
    }

    private fun showRecycleView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        listAdapter.notifyDataSetChanged()
        recyclerView.adapter = listAdapter
    }

    private fun runGetDataGit() {
        mainViewModel.getDataGit(applicationContext)
        showLoading(true)
    }

    private fun configMainViewModel(adapter: ListDataUsersAdapter) {
        mainViewModel.getListUsers().observe(this, { listUsers ->
            if (listUsers != null) {
                adapter.setData(listUsers)
                showLoading(false)
            }
        })
    }

    private fun searchData() {
        user_search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    listDataUser.clear()
                    showRecycleView()
                    mainViewModel.getDataGitSearch(query, applicationContext)
                    showLoading(true)
                    configMainViewModel(listAdapter)
                } else {
                    return true
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })
    }

    private fun showTitleActionBar () {
        if (supportActionBar != null) {
            supportActionBar?.title = "Main Page"
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            loadingProgress.visibility = View.VISIBLE
        } else {
            loadingProgress.visibility = View.INVISIBLE
        }
    }
}

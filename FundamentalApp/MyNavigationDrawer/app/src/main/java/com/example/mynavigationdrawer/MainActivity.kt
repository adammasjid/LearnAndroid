package com.example.mynavigationdrawer
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_setting.*

/* TODO ( for more information about LOCALIZATION )
    - Check the HomeFragment.kt
    - Check the Res -> Values -> String
    - Check layout fragment_home.xml
    */

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    internal lateinit var profileCircleImageView: CircleImageView
    internal var profileImageUrl = "https://c4.wallpaperflare.com/wallpaper/610/384/886/gibson-gold-guitar-les-paul-wallpaper-thumb.jpg"

    private lateinit var announcement: Menu

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // set toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //below adalah snackBar, snackBar itu pemutakhiran dari Toast
        /** dan untuk melakukan action gunakan method function [setAction] **/
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", View.OnClickListener {
                Toast.makeText(this@MainActivity, "Halo ini action dari snackbar", Toast.LENGTH_SHORT).show() }).show()
        }

        // below for Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        // below for header navigation view
        profileCircleImageView = navView.getHeaderView(0).findViewById(R.id.imageView)
        Glide.with(this)
            .load(profileImageUrl)
            .into(profileCircleImageView)

        // below is calling fragment with right simple way in drawable navigation
        /**
         * @explaining:
         * [AppBarConfiguration] berisi kumpulan id yang ada di dalam menu NavigationDrawer
         * [setupActionBarWithNavController] digunakan untuk mengatur judul AppBar agar sesuai dengan Fragment yang ditampilkan.
         * [setupWithNavController] digunakan supaya NavigationDrawer menampilkan Fragment yang sesuai ketika menu dipilih.
         */
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
            .setDrawerLayout(drawerLayout)
            .build()
        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        // below for casting searchView component from resource
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /* below Gunakan method ini ketika search selesai atau OK */
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }

            /* below Gunakan method ini untuk merespon tiap perubahan huruf pada searchView */
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return true
    }

    //below will happen if menu has been selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tv_announcement -> {
                val mAnnouncementFragment = AnnouncementFragment()
                val mFragmentManager = supportFragmentManager
                mAnnouncementFragment.show(mFragmentManager, AnnouncementFragment::class.java.simpleName)
            }
            R.id.action_settings -> {
                val moveToSettingActivity = Intent(this,SettingActivity::class.java)
                startActivity(moveToSettingActivity)
            }
            R.id.action_change_settings -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // below for action if back button pressed
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
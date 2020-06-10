package com.example.popularprogramminglanguage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val actionbar = supportActionBar
        actionbar?.title = "About"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val imgMe: ImageView = findViewById(R.id.img_item_photo)

        val tImg = "https://instagram.fcgk10-1.fna.fbcdn.net/v/t51.2885-19/s150x150/97135410_678502279610341_7481067997172334592_n.jpg?_nc_ht=instagram.fcgk10-1.fna.fbcdn.net&_nc_ohc=4Fbs9Q5cmcEAX_hQAKR&oh=e6bfb5126aa2ae6526a89a994a67cd8a&oe=5F06D885"

        Glide.with(this)
            .load(tImg)
            .apply(RequestOptions())
            .into(imgMe)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
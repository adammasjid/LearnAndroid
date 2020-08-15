package com.example.githubuserapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.githubuserapp.model.UserData
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_card_user.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val ITEM_EXTRA = "extra_person"
    }

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.title = "User Detail";
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val user = intent.getParcelableExtra<UserData>(ITEM_EXTRA)

        Glide.with(this)
            .load(user?.avatar)
            .into(img_photo_profile)
        name.text = user?.name
        followers.text = user?.followers
        following.text = user?.following
        username.text = user?.username
        location.text = user?.location
        company.text = user?.company
        repository.text = user?.repository
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
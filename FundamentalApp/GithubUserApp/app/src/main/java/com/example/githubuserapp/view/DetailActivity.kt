package com.example.githubuserapp.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuserapp.R
import com.example.githubuserapp.model.DataUsers
import com.example.githubuserapp.viewModel.ViewPagerDetailAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.layout_description.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        showTitleActionBar()
        setData()
        orientationChanged()
        viewPagerConfig()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showTitleActionBar () {
        if (supportActionBar != null) {
            supportActionBar?.title = "Detail Activity"
        }
    }

    private fun setData() {
        val dataUser = intent.getParcelableExtra<DataUsers>(EXTRA_DETAIL)
        Glide.with(this)
            .load(dataUser?.avatar)
            .apply(RequestOptions().override(150, 130))
            .into(img_item_photo)
        tv_item_name.text = dataUser?.name
        tv_item_username.text = dataUser?.username
        tv_value_repository.text = dataUser?.repository
        tv_value_location.text = dataUser?.location
        tv_value_company.text = dataUser?.company
        tv_value_followers.text = dataUser?.followers
        tv_value_following.text = dataUser?.following
    }

    private fun orientationChanged () {
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            viewpager.layoutParams.height = resources.getDimension(R.dimen.height).toInt()
        } else {
            viewpager.layoutParams.height = resources.getDimension(R.dimen.height1).toInt()
        }
    }

    private fun viewPagerConfig() {
        val viewPagerDetail = ViewPagerDetailAdapter(this, supportFragmentManager)
        viewpager.adapter = viewPagerDetail
        tabs.setupWithViewPager(viewpager)
        supportActionBar?.elevation = 0f
    }

}
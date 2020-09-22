package com.example.TestApp.intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.TestApp.R
import com.example.TestApp.model.Person


class MoveWithObjectActivity : AppCompatActivity() {

    // below to het get data
    companion object {
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        val tvObject: TextView = findViewById(R.id.tv_object_received)

        // below to get parcelable
        // nama method intent pada class received pasti selalu lowerCase
        val person = intent.getParcelableExtra<Person>(EXTRA_PERSON)
        val text = "Name : ${person?.name.toString()},\nEmail : ${person?.email},\nAge : ${person?.age},\nLocation : ${person?.city}"
        tvObject.text = text
    }

}
package com.example.githubuserapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    var avatar: Int,
    var name: String,
    var followers: String,
    var following: String,
    var username: String,
    var company: String,
    var location: String,
    var repository: String
) : Parcelable
package com.example.githubuserapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataFollowers (
    var avatar: String? = null,
    var name: String? = null,
    var username: String? = null,
    var repository: String? = null,
    var location: String? = null,
    var company: String? = null,
    var followers: String? = null,
    var following: String? = null
): Parcelable
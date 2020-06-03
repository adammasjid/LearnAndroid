package com.tutorial

import kotlin.random.Random

fun main () {
    val value = 20
    val anyType: Any = 100L // it means for data Anything (all type data, used for checking the data) type
    // used else for default expression
    when (value) {
        6 -> println("value is 6")
        10 -> println("value is 10")
        17 -> println("value is 17")
        else -> println("value is 20")
    }
    // digunakan untuk mengecek suatu type data
    when (anyType) {
        is Int -> println("this is String")
        is Long -> println("this is Long")
        else -> println("this is Nothing")
    }
    //  Sejak Kotlin 1.3, kita dapat menangkap subjek dari when expression di dalam sebuah variabel.
    // .. adalah tipe data range untuk mengecek menggunakan kata kunci in atau !in
    val registerNumber = when(val regis = getRegisterNumber()) {
        in 1..50 -> "value under 50"
        in 51..100 -> regis
        else -> regis
    }
    println(registerNumber)
}

fun getRegisterNumber () = Random.nextInt(100)
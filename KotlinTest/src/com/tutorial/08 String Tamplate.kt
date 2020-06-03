package com.tutorial

fun main () {
    // string template can contain if expression
    val hour = 24
    println("Hours in earth is ${if (hour == 24 ) "24 hours" else "----"}")
}
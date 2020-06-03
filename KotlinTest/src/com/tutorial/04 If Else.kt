package com.tutorial

fun main () {
    val openHours= 7
    val now= 20
    // penulisan if expression bisa dilakukan seperti ini
    val office: String = if (now > openHours) {"open"} else {"office is close"}

    print(office)
}
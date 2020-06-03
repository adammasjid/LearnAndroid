package com.tutorial

fun main () {
    val openHours= 7
    val closeHours= 17
    val now= 20
    // operator AND (&&)
    var isOpen = now >= openHours && now <= closeHours
    println("Office is Open = $isOpen")
    // operator OR (||)
    isOpen = now >= openHours || now <= closeHours
    println("Office is Open = $isOpen")
    // operator NOT (!)
    isOpen = now > openHours
    isOpen = !isOpen
    println("Office is Open = $isOpen")
}
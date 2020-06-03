package com.tutorial

fun main () {
    var user1 = setUser("Adam", 23)
    println(user1)
}

fun setUser (name: String, age: Int ) = "My name is $name, and I $age years old"
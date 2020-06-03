package com.tutorial

fun main () {
    /* common null safety way */
    // add symbol ? after type data for null safety (NullPointerException)
//    val text: String? = null
//
//    if (text != null ) {
//        println(text.length)
//    }

    /* Safe calls operator */
    // add ? after variable for null safety
    val text: String? = null
    println(text?.length)

    /* Elvis Operator */
    // for set default value
    print(text?.length ?: 7)
}
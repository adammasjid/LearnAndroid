package com.tutorial

fun main () {
    /*Enumeration merupakan salah fitur yang bisa kita gunakan untuk menyimpan kumpulan objek yang telah didefinisikan menjadi tipe data konstanta.
     Enumeration dapat ditetapkan sebagai nilai ke dalam sebuah variabel dengan cara yang lebih efisien
     Konsep dari Enumeration sendiri sama seperti Array.
     Oleh karena itu, selain mendapatkan daftar dan nama dari tiap objek Enum,  */

    val colorRed = Color.RED
    val colorGreen = Color.GREEN
    val colorBlue = Color.BLUE
//    println(colorBlue)
//    println(colorGreen)
//    println(colorRed)
    val colors: Array<Color> = Color.values()
    colors.forEach { color ->
        println(color)
    }
}

// bisa juga menggunakan anonymous function
enum class Color(val value: Int) {
    RED(0xFF0000){
        override fun printValue() {
            println("value of RED")
        }
    },
    GREEN(0x00FF00){
        override fun printValue() {
            println("value of GREEN")
        }
    },
    BLUE(0x0000FF){
        override fun printValue() {
            println("value of BLUE")
        }
    };
    abstract fun printValue()
}
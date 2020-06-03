package com.tutorial

// Sebuah variabel akan membutuhkan kata kunci var atau val, identifier, type dan initialization.
fun main () {

    println("-------Type Char-------")
    var vocal = 'A'
    // Char Type just can only contain 1 character
    // can used increment and decrement on char
    println("Vocal " + vocal++)
    println("Vocal " + vocal++)
    println("Vocal " + vocal++)
    println("Vocal " + vocal--)
    println("Vocal " + vocal--)
    println("Vocal " + vocal--)
    println("Vocal " + vocal--)

    println("-------Type String-------")
    // kata kunci var kita bisa mengubah nilai yang sudah kita inisialisasikan
    var company : String = "Company"
    company = "Company Academy"
    println(company)
    // kata kunci val, kita tidak bisa mengubah nilai yang sebelumnya sudah kita inisialisasi
    val company2 : String = "Company"
    // company2 = "test" -> will be error massage
    println(company2)
    // bisa juga menaruh tamplate string dengan """
    val line = """
        Line 1
        Line 2
        Line 3
        Line 4
    """.trimIndent() // tambahkan trimIndent() untuk menghilangkan indentasinya, supaya outputnya menjadi bagus ;
    println(line)
}
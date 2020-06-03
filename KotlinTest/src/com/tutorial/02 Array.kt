package com.tutorial

import java.util.Arrays

fun main () {

    /**
     * specific functions on array :
        intArrayOf() : IntArray
        booleanArrayOf() : BooleanArray
        charArrayOf() : CharArray
        longArrayOf() : LongArray
        shortArrayOf() : ShortArray
        byteArrayOf() : ByteArray
     */
    val mixArray = arrayOf(1, 3, 5, 7 , "company" , true)
    println(Arrays.toString(mixArray)) // jika ingin mencetak array ke string
    // Pada kode dibawah kita menentukan angka 4 sebagai size Array. Fungsi lambda di atas ada dua. Pertama,
    // untuk mengambil indeks Array yang akan digunakan sebagai argumen. Kedua, menentukan elemen Array yang akan dimasukkan ke dalam indeks tersebut.
    val intArray = Array(4, {i -> i * i })
    println(Arrays.toString(intArray))
}
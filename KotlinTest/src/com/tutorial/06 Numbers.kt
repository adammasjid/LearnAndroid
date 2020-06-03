package com.tutorial

fun main () {
    /**
     * Type Data Numbers
        * @Byte = 8 bit
        * @Short = 16 bit
        * @Integer = 32 bit
        * @Float = 32 bit
        * @Long = 64 bit
        * @Double = 64 bit
     */
    println("--------------------------")
    val maxInt = Int.MAX_VALUE
    val minInt = Int.MIN_VALUE
    print(maxInt)
    print("\n")
    print(minInt)
    print("\n")
    val int1: Int = 1
    val int2: Int = 2
    print(int1 + int2)
    print("\n")

    println("--------Conversion-------")
    /**
     * Method to Convert Number Type
        * @Byte = toByte()
        * @Short = toShort()
        * @Integer = toInt()
        * @Float = toFloat()
        * @Long = toLong()
        * @Double = toDouble()
        * @Char = toChar()
        * @String = toString()
     */
    val stringNumber = "23"
    val intNumber = 3
    print(stringNumber.toInt() + intNumber)
}
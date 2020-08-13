typealias Arithmetic = (Int, Int) -> Int
/**
 * typealias cocok digunakan ketika kita mempunyai sebuah function type yang panjang. Dengannya,
 * kita bisa memberikan nama untuk sebuah function type dan menggunakannya sebagai tipe untuk fungsi lainnya.
 */

val sum: Arithmetic = { valueA, valueB -> valueA + valueB }

val multiply: Arithmetic = { valueA, valueB -> valueA * valueB }

fun main() {
    val sumResult = sum.invoke(10, 10)
    val multiplyResult = multiply.invoke(20, 20)

    println(sumResult)
    println(multiplyResult)
}
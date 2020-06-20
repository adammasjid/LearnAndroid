typealias Arithmetic = (Int, Int) -> Int
//   |        |________________________________
//   |                                         |
//   V                                         V
//keyword untuk menyederhanakan lambda    typealias name
val sum: Arithmetic = { valueA, valueB -> valueA + valueB }

val multiply: Arithmetic = { valueA, valueB -> valueA * valueB }

fun main() {
    val sumResult = sum.invoke(10, 10)
    val multiplyResult = multiply.invoke(20, 20)

    println(sumResult)
    println(multiplyResult)
}
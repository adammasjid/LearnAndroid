// main function
fun main() {
    printResult(10 ,sum)
}

fun printResult(value: Int, sum: (Int) -> Int) {
    val result = sum(value)
    println(result)
}

var sum: (Int) -> Int = { it + it } // jika parameter nya hanya satu, cukup menggunakan it saja, tidak perlu nama params
// main function
fun main() {
    val numberList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val multipliedBy5 = numberList.map { it * 5 }
    /* map akan kalkulasi atau memproses semua value yang ada di dalam list*/
    println(multipliedBy5)
}
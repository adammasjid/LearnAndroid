// main function
fun main() {
    val numberList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    /* count() menghitung jumlah value yang ada di list seperti method .size di Array*/
    print(numberList.count())
    print(numberList.count { it % 3 == 0 })
}
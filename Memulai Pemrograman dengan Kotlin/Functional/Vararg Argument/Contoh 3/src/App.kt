// main function
fun main() {
    val number = intArrayOf(10, 20, 30, 40)
    // berbeda dengan Array<T> , varargs bisa menampung array didalam parameter
    println(sets(10, 20, 20, *number , 10)) // menggunakan symbol * untuk memasukan array di params
}

fun sets(vararg number: Int): Int {
    return number.sum()
}
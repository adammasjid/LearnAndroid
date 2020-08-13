// main function
fun main() {
    val value = 7
    val stringOfValue = when (value) {
        6 -> "value is 6"
        7 -> "value is 7"
        8 -> "value is 8"
        else -> "value cannot be reached"
    }
    // 'when expression' in variable must using else condition
    println(stringOfValue)
}
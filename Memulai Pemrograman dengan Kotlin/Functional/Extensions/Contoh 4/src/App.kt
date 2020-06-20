// main function
fun main() {
    val value: Int? = null

    println(value.slice())
}

fun Int?.slice(): Int {
    return this?.div(2) ?: 0
}
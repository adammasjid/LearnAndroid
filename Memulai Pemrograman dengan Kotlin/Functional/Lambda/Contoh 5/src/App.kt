// main function
fun main() {
    val message = buildString {
        append("Hello ")
        append("from ")
        append("lambda ")
    }

    println(message)
}
                                      // Unit adalah tipe return jika () tidak ada param di lambda
fun buildString(action: StringBuilder.() -> Unit ): String {
    val stringBuilder = StringBuilder()
    stringBuilder.action()
    return stringBuilder.toString()
}
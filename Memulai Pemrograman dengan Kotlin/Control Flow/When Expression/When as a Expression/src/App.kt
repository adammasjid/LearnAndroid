// main function
fun main() {
    val value = 7
    // when expression juga bisa dimasukan ke dalam variable
    val stringOfValue = when (value) {
        6 -> "value is 6"
        7 -> "value is 7"
        8 -> "value is 8"
        // else adalah hal wajib jika kita menggunakan when expression untuk mengembalikan nilai
        else -> "value cannot be reached"
    }
    /**
     * if when expression that has a return, but without branch else, then will be an error message :
     * @errorMessage 'when' expression must be exhaustive, add necessary 'else' branch
     */
    println(stringOfValue)
}
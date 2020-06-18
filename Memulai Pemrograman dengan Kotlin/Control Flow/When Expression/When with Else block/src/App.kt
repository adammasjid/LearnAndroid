// main function
fun main() {
    val value = 20

    when(value){
        6 -> println("value is 6")
        7 -> println("value is 7")
        8 -> println("value is 8")
        else -> println("value cannot be reached")
    }
    // if when expression that has a return, but without branch else, then will be an error message :
    // 'when' expression must be exhaustive, add necessary 'else' branch
}
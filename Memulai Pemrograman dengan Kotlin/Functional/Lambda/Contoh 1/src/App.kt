// main function
fun main() {
    printMessage("Hello From Lambda")
}

val printMessage = { message: String -> println(message) }
//                                  |        |_________
//                                  |                  |
//                                  V                  V
//                            parameter lambda   return type lambda
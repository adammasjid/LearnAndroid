// main function
fun main() {
    printResult(10){ value ->
        value.plus(value)//  |
    }//                      |
}//                          V
//                     parameter name
fun printResult(value: Int, sum: (Int) -> Int) {
    val result = sum(value)//   |      |_____________
    println(result)//               |                    |
}//                                 V                    V
//                           data type param      data type Return
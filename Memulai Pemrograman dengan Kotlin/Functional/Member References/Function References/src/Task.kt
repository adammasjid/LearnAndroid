fun main() {
//  Example 1
    val numbers = 1.rangeTo(10)
    val evenNumbers = numbers.filter(Int::isEvenNumber)
    println(evenNumbers)//                              |
//  Example 2                                           |
    val sum: (Int, Int) -> Int = ::count//              V
    println(sum(10,19))//                  function reference with keyword ::
}

fun Int.isEvenNumber() = this % 2 == 0

fun count(valueA: Int, valueB
: Int): Int {
    return valueA + valueB
}
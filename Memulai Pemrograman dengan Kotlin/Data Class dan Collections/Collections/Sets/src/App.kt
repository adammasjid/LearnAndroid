// main function
fun main() {
    val integerSet = setOf(1, 2, 4, 2, 1, 5)
    println(integerSet)
    /* setOF() untuk mmencegah adanya duplikasi data */
    val setA = setOf(1, 2, 4, 2, 1, 5)
    val setB = setOf(1, 2, 4, 5)
    println(setA == setB)

    println(5 in setA)

    val setC = setOf(1, 5, 7)
    val union = setA.union(setC) /*.union untuk menggabungkan data tanpa ada duplikasi*/
    val intersect = setA.intersect(setC)

    println(union)
    println(intersect)

}
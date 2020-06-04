// main function
fun main() {
    /**
     * @setOf() adalah method untuk menghilangkan value yang sama
     */
    val integerSet = setOf(1, 2, 4, 2, 1, 5)
    println(integerSet)

    val setA = setOf(1, 2, 4, 2, 1, 5)
    val setB = setOf(1, 2, 4, 5)
    println(setA == setB) // output = True

    println(5 in setA) // dalam kasus ini untuk mengecek apakah ada 5 didalam variable setA
                       // output = True

    val setC = setOf(1, 5, 7)
    val union = setA.union(setC) // method union() untuk menggabungkan variable
    val intersect = setA.intersect(setC) // method intesect() untuk mengetahui value yang sama

    println(union)
    println(intersect)
}
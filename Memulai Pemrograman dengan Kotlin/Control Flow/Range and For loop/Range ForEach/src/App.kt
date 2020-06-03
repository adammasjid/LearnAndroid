// main function
fun main() {
    val rangeInt = 1..10 step 2

    // $it adalah variable default yang ada untuk range/intProgression
    rangeInt.forEach {
        print("$it ")
    }

    println(rangeInt.step)
}
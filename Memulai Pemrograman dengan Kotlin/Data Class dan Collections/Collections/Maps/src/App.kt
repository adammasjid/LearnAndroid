fun main() {
    val capital = mapOf(
            "Jakarta" to "Indonesia",
            "London" to "England",
            "New Delhi" to "India"
    )
    // String yang berada pada sebelah kiri dari kata kunci to adalah sebuah key,
    // sedangkan yang di sebelah kanan merupakan value-nya.
    println(capital["Jakarta"]) // output = "indonesia"
    println(capital.getValue("Jakarta")) // output = "indonesia"

    val mapKeys = capital.keys // function keys untuk menampilkan semua key yang ada di dalam variable
    val mapValues = capital.values // function values untuk menampilkan semua value

    println(mapKeys)
    println(mapValues)
}
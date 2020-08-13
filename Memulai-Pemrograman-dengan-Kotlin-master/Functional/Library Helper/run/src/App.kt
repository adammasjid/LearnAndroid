// main function
fun main() {
    val text = "Hello"
    val result = text.run {
        val from = this
        val to = this.replace("Hello", "Kotlin")
        "replace text from $from to $to"
        // dalam lambda, baris terakhir logika akan otomatis dianggap sebagai return
    }
    println("result : $result")
}
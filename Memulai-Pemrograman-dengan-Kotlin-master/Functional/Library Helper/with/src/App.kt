// main function
fun main() {
    val message = "Hello Kotlin!"
    // with bukan extension function, jadi cara penggunaannya seperti dibawah
    val result = with(message) {
        "First character is ${this[0]}" +
                " and last character is ${this[this.length - 1]}"
    }

    println(result)
}
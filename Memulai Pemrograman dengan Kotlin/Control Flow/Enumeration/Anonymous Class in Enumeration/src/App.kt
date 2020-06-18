// main function
fun main() {
    val colorRed = Color.RED
    // Enumeration dapat ditetapkan sebagai nilai ke dalam sebuah variabel dengan cara yang lebih efisien
    colorRed.printValue()
    Color.GREEN.printValue()
    Color.BLUE.printValue()
}

enum class Color(val value: Int) {
    RED(0xFF0000) {
        override fun printValue() {
            println("value of RED is $value")
        }
    },
    GREEN(0x00FF00) {
        override fun printValue() {
            println("value of GREEN is $value")
        }
    },
    BLUE(0x0000FF) {
        override fun printValue() {
            println("value of BLUE is $value")
        }
    };

    abstract fun printValue()
}
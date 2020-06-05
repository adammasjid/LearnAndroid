// main function
fun main() {
    // pada saat set parameter harus menggunakan named argument agar tidak error
    sets(10,10, name = "Kotlin")
}
// bisa memasukan type data yang berbeda
fun sets(vararg number: Int, name: String): Any {
    println(name)
    return println(number.sum())
}
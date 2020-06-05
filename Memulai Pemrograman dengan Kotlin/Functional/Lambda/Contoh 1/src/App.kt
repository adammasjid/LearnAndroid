// main function
fun main() {
    printMessage("Hello From Lambda")
}

val printMessage = {message: String -> println(message)}
//  parameter dari sebuah lambda berada di dalam kurung kurawal. Untuk membedakannya dengan body,
//  daftar parameter yang ada dipisahkan dengan tanda ->
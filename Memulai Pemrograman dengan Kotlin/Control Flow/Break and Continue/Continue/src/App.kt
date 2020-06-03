// main function
fun main() {
    val listOfInt = listOf(1, 2, 3, null, 5, null, 7)
    // untuk menghindari nullPointerException pada list, disini menggunakan CONTINUE pada for loop
    for (i in listOfInt) {
        if (i == null) continue
        print(i)
    }
}
// main function
fun main() {
    // pembuka label memiliki tanda @ setelah nama label
    loop@ for (i in 1..10) {
        println("Outside Loop")

        for (j in 1..10) {
            println("Inside Loop")
            if ( j > 5) break@loop // penutup label
        }
    } // jika tidak menggunakan label break yang ada di dalam loop 'j' hanya akan keluar dari scope loop 'j'
      // lalu akan kembali ke loop 'i'
}

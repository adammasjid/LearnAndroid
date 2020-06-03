// main function
fun main() {
    loop@ for (i in 1..10) {
        println("Outside Loop")

        for (j in 1..10) {
            println("Inside Loop")
            if ( j > 5) break@loop
        } // "Inside Loop" akan di tampilkan 6 kali karena expression if j > 5 ada dibawah for loop
    }
}
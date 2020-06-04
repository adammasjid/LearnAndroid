data class DataUser(val name : String, val age : Int)

fun main(){
    val dataUser = DataUser("nrohmen", 17)
    val dataUser2 = DataUser("nrohmen", 17)
    val dataUser3 = DataUser("dimas", 24)
    val dataUser4 = dataUser.copy()

    println(dataUser4)

    // Tanpa data class, untuk melakukan tugas seperti ini kita memerlukan sebuah instance baru untuk mengubah nilai dari suatu objek.
    // Dengan demikian kita harus memodifikasi properti yang kita maksud.
    // Tugas ini akan berulang dan membuat kode yang kita tulis, jauh dari paradigma clean code.
}
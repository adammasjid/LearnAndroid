data class DataUser(val name : String, val age : Int)

fun main(){
    val dataUser = DataUser("nrohmen", 17)

    val name = dataUser.component1()
    val age = dataUser.component2()

    println("My name is $name, I am $age years old")
    // Fungsi component1() dan component2() dihasilkan sesuai dengan jumlah properti yang ada pada data class tersebut.
    // Maka jika sebuah data class memiliki sejumlah N properti,
    // maka secara otomatis componentN() akan dihasilkan.
}
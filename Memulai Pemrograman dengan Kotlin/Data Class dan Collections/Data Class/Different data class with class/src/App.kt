class User(val name : String, val age : Int)

data class DataUser(val name : String, val age : Int)

// Hanya dengan satu baris kode diatas, compiler akan secara otomatis menghasilkan constructor,
// toString(), equals(), hashCode(), copy() dan juga fungsi componentN().

fun main(){
    val user = User("Adam", 17)
    val dataUser = DataUser("Adam", 17)

    println(user)
    println(dataUser)
}
/**
 * output :
 *  User@12bc6874
 *  DataUser(name=Adam, age=17), karena data class langsung menghasilkan funsi toString() ,etc
 */
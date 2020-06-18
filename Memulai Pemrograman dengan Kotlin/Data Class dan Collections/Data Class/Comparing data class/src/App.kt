data class DataUser(val name : String, val age : Int)

class User(val name: String, val age: Int)

fun main(){
    /*data class*/
    val dataUser = DataUser("nrohmen", 17)
    val dataUser2 = DataUser("nrohmen", 17)
    val dataUser3 = DataUser("dimas", 24)
    /*class*/
    val user = User ("Adam",23)
    val user2 = User ("Adam",23)
    /*data class*/
    println(dataUser == dataUser2) // equals() sama dengan ==
    println(dataUser.equals(dataUser3))
    /*class*/
    println(user == user2) // seharusnya ini true, tapi output akan error, karena object bukan dari data class
}
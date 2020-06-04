data class DataUser(val name : String, val age : Int)

fun main(){
    // with data class, we can modify value without making new Instance like on Java

    val dataUser = DataUser("nrohmen", 17)
    val dataUser2 = DataUser("nrohmen", 17)
    val dataUser3 = DataUser("dimas", 24)
    val dataUser4 = dataUser.copy()
    val dataUser5 = dataUser.copy(age = 18)

    println(dataUser4)
    println(dataUser5)
}
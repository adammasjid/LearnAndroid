class User(val name : String, val age : Int) {
//    Todo: harus menambahkan beberapa boilerplate code untuk seperti funsi pada data class untuk equals()
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as User
//
//        if (name != other.name) return false
//        if (age != other.age) return false
//
//        return true
    }

data class DataUser(val name : String, val age : Int)

fun main(){
    val dataUser = DataUser("nrohmen", 17)
    val dataUser2 = DataUser("nrohmen", 17)
    val dataUser3 = DataUser("dimas", 24)
    val user = User("Adam",23)
    val user2 = User("Adam",23)
    val user3 = User("Fajar",23)

    // pay attention different Data Class and Class for equals()
    println("------- USE DATA CLASS --------")
    println(dataUser.equals(dataUser2))
    println(dataUser.equals(dataUser3))
    println("---------- USE CLASS ----------")
    println(user.equals(user2))
    println(user.equals(user3))

}
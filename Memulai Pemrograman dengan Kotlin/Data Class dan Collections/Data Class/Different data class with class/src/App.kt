class User(val name : String, val age : Int)

data class DataUser(val name : String, val age : Int)
/**
 * data class sudah input beberapa method :
 * @toString()
 * @hashCode()
 * @copy()
 * @equals()
 * @componentN()
 */

fun main(){
    val user = User("nrohmen", 17)
    val dataUser = DataUser("nrohmen", 17)

    println(user)
    println(dataUser)

}
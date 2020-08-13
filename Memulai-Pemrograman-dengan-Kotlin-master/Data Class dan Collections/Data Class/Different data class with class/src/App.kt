class User(val name : String, val age : Int)

data class DataUser(val name : String, val age : Int)

/**
 * data class sudah memiliki function bawaan yang tidak dimiliki class biasa, yaitu:
 * @toString = untuk mengubah type data menjadi string
 * @copy = untuk menyalin data class
 * @hashcode
 * @equals
 * @componentN
 */

fun main(){
    val user = User("nrohmen", 17)
    val dataUser = DataUser("nrohmen", 17)

    println(user)
    println(dataUser)
}
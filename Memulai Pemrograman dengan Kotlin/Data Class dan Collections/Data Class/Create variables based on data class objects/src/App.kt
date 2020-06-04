data class DataUser(val name : String, val age : Int)

fun main(){
    val dataUser = DataUser("nrohmen", 17)
    /* the output same like previous case, for decipher(menguraikan) a Data Class */
    val (name, age) = dataUser

    println("My name is $name, I am $age years old")
}
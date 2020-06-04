class User

fun main() {
    val numberList = listOf(1, 2, 3, 4, 5)
    val charList = listOf('a', 'b', 'c')
    // list dapat menampung type data apapun, bahkan sebuah class
    val anyList = listOf('a', "Kotlin", 3, true, User())
    println(anyList[3])
    /**
     * List tersebut bersifat immutable alias tidak bisa diubah. Namun jangan khawatir,
     * Kotlin standard library juga menyediakan collection dengan tipe mutable
     * @mutableListOf <- menggunakan method ini
     */
    val anyList2 = mutableListOf('a', "Kotlin", 3, true, User())
    anyList2.add('d') // menambah item di akhir list
    anyList2.add(1, 'B') // menambah item pada index ke-1
    anyList2[3] = false // mengubah nilai item pada index ke-3
    println(anyList2)
    /* output [a, B, Kotlin, false, true, User@5474c6c, d] */
}
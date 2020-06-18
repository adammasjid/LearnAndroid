class User

fun main() {
    val numberList = listOf(1, 2, 3, 4, 5)
    val charList = listOf('a', 'b', 'c')

    val List = listOf('a', "Kotlin", 3, true, User())
    /* list dapat menyimpan berbagai type data, bahkan sebuah class sekalipun */
    println(List[3])

    /**
     *  type data List bersifat immutable alias tidak bisa diubah
     *  untuk melakukan perubahan nilai dengan cara seperti menambah, menghapus, atau mengganti nilai yang sudah ada
     *  @mutableListOf() dengan cara instance dengan itu maka Lis akan bisa diubah
     */
    val anyList = mutableListOf('a', "Kotlin", 3, true, User())
    anyList.add('d') // menambah item di akhir list
    anyList.add(1, "love") // menambah item pada index ke-1
    anyList[3] = false // mengubah nilai item pada index ke-3
    anyList.removeAt(0) // menghapus index ke-0 berdasarkan index atau posisi nilai di dalam Array
    println(anyList)
}
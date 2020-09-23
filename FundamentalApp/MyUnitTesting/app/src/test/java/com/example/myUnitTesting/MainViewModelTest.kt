package com.example.myUnitTesting

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.*

class MainViewModelTest {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var cuboidModel: CuboidModel
    private val dummyLength = 12.0
    private val dummyWidth = 7.0
    private val dummyHeight = 6.0

    private val dummyVolume = 504.0
    private val dummyCircumference = 100.0
    private val dummySurfaceArea = 396.0
    /**
     * @before fungsinya untuk menginisialisasi method sebelum melakukan test.
     * Method yang diberi anotasi @Before ini akan dijalankan sebelum menjalankan semua method dengan anotasi @[Test].
     * @Before, dalam melakukan Unit Testing juga ada anotasi @After yang berfungsi sebaliknya dari anotasi @Before,
     * yaitu untuk menginisialisai method yang akan dijalankan setelah method dengan anotasi @Test.
     */
    @Before
    fun before() {
        cuboidModel = mock(CuboidModel::class.java)
        mainViewModel = MainViewModel(cuboidModel)
    }

    @Test // Anotasi ini digunakan pada method yang akan di test.
    fun testVolume() {
        cuboidModel = CuboidModel()
        mainViewModel = MainViewModel(cuboidModel)
        mainViewModel.save(dummyWidth, dummyLength, dummyHeight)
        val volume = mainViewModel.getVolume()
        assertEquals(dummyVolume, volume, 0.0001)
        /** [assertEquals] Fungsi ini = fungsi dari JUnit yang digunakan untuk memvalidasi output yang diharapkan dan output yang sebenarnya. **/
        // param ke-3 assertEquals() is delta number which is a different range behind the point double type.
    }

    @Test
    fun testCircumference() {
        cuboidModel = CuboidModel()
        mainViewModel = MainViewModel(cuboidModel)
        mainViewModel.save(dummyWidth, dummyLength, dummyHeight)
        val volume = mainViewModel.getCircumference()
        assertEquals(dummyCircumference, volume, 0.0001)
    }

    @Test
    fun tesSurfaceArea() {
        cuboidModel = CuboidModel()
        mainViewModel = MainViewModel(cuboidModel)
        mainViewModel.save(dummyWidth, dummyLength, dummyHeight)
        val volume = mainViewModel.getSurfaceArea()
        assertEquals(dummySurfaceArea, volume, 0.0001)
    }

    /**
     * @when() = Digunakan untuk menandakan event di mana Anda ingin memanipulasi behavior dari mock object.
     * @verify() = Digunakan untuk memeriksa metode dipanggil dengan args yang diberikan. Verify merupkan fungsi dari framework Mockito
     * @thenReturn() = Digunakan untuk memanipulasi output dari mock object.
     * @any() = Merupakan fungsi dari Mockito yang digunakan untuk mencocokan argumen yang fleksibel. any digunakan bareng dengan verify.
     */
    @Test
    fun testMockVolume() {
        `when`(mainViewModel.getVolume()).thenReturn(dummyVolume)
        val volume = mainViewModel.getVolume()
        verify(cuboidModel).getVolume()
        assertEquals(dummyVolume, volume, 0.0001)
    }
    @Test
    fun testMockCircumference() {
        `when`(mainViewModel.getCircumference()).thenReturn(dummyCircumference)
        val circumference = mainViewModel.getCircumference()
        verify(cuboidModel).getCircumference()
        assertEquals(dummyCircumference, circumference, 0.0001)
    }
    @Test
    fun testMockSurfaceArea() {
        `when`(mainViewModel.getSurfaceArea()).thenReturn(dummySurfaceArea)
        val surfaceArea = mainViewModel.getSurfaceArea()
        verify(cuboidModel).getSurfaceArea()
        assertEquals(dummySurfaceArea, surfaceArea, 0.0001)
    }
}
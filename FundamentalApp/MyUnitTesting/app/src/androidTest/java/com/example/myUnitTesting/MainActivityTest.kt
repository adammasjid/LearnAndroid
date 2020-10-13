package com.example.myUnitTesting

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val dummyVolume = "504.0"
    private val dummyCircumference = "100.0"
    private val dummySurfaceArea = "396.0"
    private val dummyLength = "12.0"
    private val dummyWidth = "7.0"
    private val dummyHeight = "6.0"
    private val emptyInput = ""
    private val fieldEmpty = "Field ini tidak boleh kosong"

    // below code for decide which one activity will be execute
    @get:Rule
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    // below for test Circumference
    @Test
    fun assertGetCircumference() {
        // TODO code dibawah jika dibaca yaitu seperti:
        // Sebuah view dengan id diberi tindakan input dengan sebuah teks dummyLength dan menutup secara berlahan keyboard Android.
        onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard())
        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard())
        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight), closeSoftKeyboard())

        // TODO code dibawah jika dibaca yaitu seperti:
        // Memastikan sebuah view dengan id btn_save dalam keadaan tampil.
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_calculate_circumference)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_result)).check(matches(isDisplayed()))


        // TODO code dibawah jika dibaca yaitu seperti:
        // Sebuah view dengan id btn_save diberi aksi klik.
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.btn_calculate_circumference)).perform(click())

        // TODO code dibawah jika dibaca yaitu seperti:
        // Memastikan sebuah view dengan id tv_result mempunyai teks yang sama dengan dummyCircumference.
        onView(withId(R.id.tv_result)).check(matches(withText(dummyCircumference)))
        /**
         * @MARKER
         * TODO untuk melakukan testing harus dengan step yang sesuai seperti dibawah
         *  diatas hanya penjelasan dari setiap code
         */
//        onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard())
//        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard())
//        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight), closeSoftKeyboard())
//        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
//        onView(withId(R.id.btn_save)).perform(click())
//        onView(withId(R.id.btn_calculate_circumference)).check(matches(isDisplayed()))
//        onView(withId(R.id.btn_calculate_circumference)).perform(click())
//        onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_result)).check(matches(withText(dummyCircumference)))
    }

    // below for test Surface Area
    @Test
    fun assertGetSurfaceArea() {
        onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard())
        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard())
        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight), closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.btn_calculate_surface_area)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_calculate_surface_area)).perform(click())
        onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_result)).check(matches(withText(dummySurfaceArea)))
    }

    //below for test the volume
    @Test
    fun assertGetVolume() {
        onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard())
        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard())
        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight), closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.btn_calculate_volume)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_calculate_volume)).perform(click())
        onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_result)).check(matches(withText(dummyVolume)))
    }
    //Pengecekan untuk empty input
    @Test
    fun assertEmptyInput() {
        // pengecekan input untuk length
        onView(withId(R.id.edt_length)).perform(typeText(emptyInput), closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.edt_length)).check(matches(hasErrorText(fieldEmpty))) /**@HowToReadTheCode =  Memastikan sebuah view dengan id edt_length mempunyai pesan eror yang sama dengan fieldEmpty.**/
        onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard())
        // pengecekan input untuk width
        onView(withId(R.id.edt_width)).perform(typeText(emptyInput), closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.edt_width)).check(matches(hasErrorText(fieldEmpty)))
        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard())
        // pengecekan input untuk height
        onView(withId(R.id.edt_height)).perform(typeText(emptyInput), closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.edt_height)).check(matches(hasErrorText(fieldEmpty)))
        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight), closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
    }
}
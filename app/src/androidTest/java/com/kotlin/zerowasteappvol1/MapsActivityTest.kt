package com.kotlin.zerowasteappvol1

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.uiautomator.*
import com.kotlin.zerowasteappvol1.activities.MapsActivity
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import android.util.Log
import kotlinx.coroutines.delay


class MapsActivityTest {

    @Rule
    @JvmField
    var rule = ActivityTestRule(MapsActivity::class.java)

//    @Before
//    fun before(){
//
//    }

//    @Test
//    fun searchPanelClearsAfterEnter(){
//        onView(withId(R.id.SearchPanel)).perform(typeText("HELLO"),
//            pressImeActionButton(), closeSoftKeyboard())
//        onView(withId(R.id.SearchPanel)).check(matches(withText("")))
//    }

    @Test
    fun afterClickOnMarkerPopUpWindowOpens(){
//        Marker musi byc w 'polu widzenia' w momencie wlaczenia aplikacji
        UiDevice
            .getInstance(getInstrumentation())
            .let {
                it
                    .findObject(
                        UiSelector().descriptionContains("Miejsce 1")
                    )
                    .let {
                        it.click()
                        runBlocking { delay(3000) }
                    }
            }

        onView(
            withId(R.id.linearLayout_short_description)
        )
            .check(
                matches(withEffectiveVisibility(Visibility.VISIBLE))
            )
    }

    @Test
    fun popupWindowContainsPlaceInformation(){
        UiDevice
            .getInstance(getInstrumentation())
            .let {
                it
                    .findObject(
                        UiSelector().descriptionContains("Miejsce 1")
                    )
                    .let {
                        it.click()
                        runBlocking { delay(3000) }
                    }
            }

        onView(
            withId(R.id.textView_name)
        )
            .check(
                matches(withEffectiveVisibility(Visibility.VISIBLE))
            )

        onView(
            withId(R.id.textView_type_of_place)
        )
            .check(
                matches(withEffectiveVisibility(Visibility.VISIBLE))
            )

        onView(
            withId(R.id.textView_open_hours)
        )
            .check(
                matches(withEffectiveVisibility(Visibility.VISIBLE))
            )
    }
////
////    @Test
////    fun popupWindowContainsPlaceAddress(){
////        val device = UiDevice.getInstance(getInstrumentation())
////        val marker = device.findObject(UiSelector().descriptionContains("Miejsce 1"))
////        marker.click()
////        onView(withText("Orchidei 22H")).inRoot(isPlatformPopup()).check(matches(isDisplayed()))
////    }
////
////    @Test
////    fun popupWindowContainsPlacePhoneNumber(){
////        val device = UiDevice.getInstance(getInstrumentation())
////        val marker = device.findObject(UiSelector().descriptionContains("Miejsce 1"))
////        marker.click()
////        onView(withText("123456789")).inRoot(isPlatformPopup()).check(matches(isDisplayed()))
////    }
////
//    @Test
//    fun popupWindowClosesAfterClickingCloseButton(){
////        nie wiem jak sprawdzic, ze isNotDisplayed, wiec ten test powiniene nie przechodzic, jesli feature bedzie dzialal
//        val device = UiDevice.getInstance(getInstrumentation())
//        val marker = device.findObject(UiSelector().descriptionContains("Miejsce 1"))
//        marker.click()
//        onView(withId(R.id.closeButton)).inRoot(isPlatformPopup()).perform(click()).check(doesNotExist())
//    }
//
//    @Test
//    fun threeMarkersLoadAfterAppStarts(){
//        val device = UiDevice.getInstance(getInstrumentation())
//        val marker = device.findObject(UiSelector().className("Marker"))
//        marker.click()
//    }

}
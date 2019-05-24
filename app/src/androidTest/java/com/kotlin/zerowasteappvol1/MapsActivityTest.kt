package com.kotlin.zerowasteappvol1

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.isPlatformPopup
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.uiautomator.*
import com.google.android.gms.maps.model.Marker
import com.kotlin.zerowasteappvol1.UI.MapsActivity
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import android.support.test.uiautomator.Until.newWindow
import android.support.test.uiautomator.Until.findObject
import android.support.test.uiautomator.Until.hasObject
import java.util.regex.Pattern
import android.support.test.uiautomator.Until.newWindow
import android.support.test.uiautomator.Until.findObject
import android.support.test.uiautomator.Until.hasObject
import android.support.test.uiautomator.By
import android.support.test.uiautomator.BySelector
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
        runBlocking {
            delay(3000)
            val device = UiDevice.getInstance(getInstrumentation())
            delay(3000)
            val marker = device.findObject(UiSelector().descriptionContains("Miejsce 1"))//className("com.google.android.gms.maps.model.Marker"))
            Log.d("TU JEST MARKER", "${marker.className}, ${marker.packageName}, ${marker.contentDescription}," +
                    "${marker.text}, $marker, ${marker.bounds}")//click()
            marker.click()

            delay(3000)

        }
        onView(withId(R.id.linearLayout_short_description)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

//    @Test
//    fun popupWindowContainsPlaceInformation(){
//        val device = UiDevice.getInstance(getInstrumentation())
//        val marker = device.findObject(UiSelector().descriptionContains("Miejsce"))
//        marker.click()
//        onView(withText("Miejsce 1")).inRoot(isPlatformPopup()).check(matches(isDisplayed()))
//        onView(withText("Orchidei 22H")).inRoot(isPlatformPopup()).check(matches(isDisplayed()))
//        onView(withText("123456789")).inRoot(isPlatformPopup()).check(matches(isDisplayed()))
//    }
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
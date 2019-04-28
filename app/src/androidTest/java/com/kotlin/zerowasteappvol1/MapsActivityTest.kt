package com.kotlin.zerowasteappvol1

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.isPlatformPopup
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import com.google.android.gms.maps.model.Marker
import com.kotlin.zerowasteappvol1.UI.MapsActivity
import org.junit.Rule
import org.junit.Test

class MapsActivityTest {

    @Rule
    @JvmField
    var rule = ActivityTestRule(MapsActivity::class.java)

//    @Before
//    fun before(){
//
//    }

    @Test
    fun searchPanelClearsAfterEnter(){
        onView(withId(R.id.SearchPanel)).perform(typeText("HELLO"),
            pressImeActionButton(), closeSoftKeyboard())
        onView(withId(R.id.SearchPanel)).check(matches(withText("")))
    }

    @Test
    fun afterClickOnMarkerPopUpWindowOpens(){
//        Marker musi byc w 'polu widzenia' w momencie wlaczenia aplikacji
        val device = UiDevice.getInstance(getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains("Miejsce 1"))
        marker.click()
        onView(withText("Opis miejsca 1")).inRoot(isPlatformPopup()).check(matches(isDisplayed()))
    }

    @Test
    fun popupWindowContainsPlaceInformation(){
        val device = UiDevice.getInstance(getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains("Miejsce"))
        marker.click()
        onView(withText("Miejsce 1")).inRoot(isPlatformPopup()).check(matches(isDisplayed()))
        onView(withText("Orchidei 22H")).inRoot(isPlatformPopup()).check(matches(isDisplayed()))
        onView(withText("123456789")).inRoot(isPlatformPopup()).check(matches(isDisplayed()))
    }
//
//    @Test
//    fun popupWindowContainsPlaceAddress(){
//        val device = UiDevice.getInstance(getInstrumentation())
//        val marker = device.findObject(UiSelector().descriptionContains("Miejsce 1"))
//        marker.click()
//        onView(withText("Orchidei 22H")).inRoot(isPlatformPopup()).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun popupWindowContainsPlacePhoneNumber(){
//        val device = UiDevice.getInstance(getInstrumentation())
//        val marker = device.findObject(UiSelector().descriptionContains("Miejsce 1"))
//        marker.click()
//        onView(withText("123456789")).inRoot(isPlatformPopup()).check(matches(isDisplayed()))
//    }
//
    @Test
    fun popupWindowClosesAfterClickingCloseButton(){
//        nie wiem jak sprawdzic, ze isNotDisplayed, wiec ten test powiniene nie przechodzic, jesli feature bedzie dzialal
        val device = UiDevice.getInstance(getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains("Miejsce 1"))
        marker.click()
        onView(withId(R.id.closeButton)).inRoot(isPlatformPopup()).perform(click()).check(doesNotExist())
    }

    @Test
    fun threeMarkersLoadAfterAppStarts(){
        val device = UiDevice.getInstance(getInstrumentation())
        val marker = device.findObject(UiSelector().className("Marker"))
        marker.click()
    }

}
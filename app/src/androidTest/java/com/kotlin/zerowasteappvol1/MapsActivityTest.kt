package com.kotlin.zerowasteappvol1

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.RootMatchers.isPlatformPopup
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiObjectNotFoundException
import android.support.test.uiautomator.UiSelector
import org.junit.Before
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
    fun popupWindowContainsPlaceName(){
        val device = UiDevice.getInstance(getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains("Miejsce 1"))
        marker.click()
        onView(withText("Miejsce 1")).inRoot(isPlatformPopup()).check(matches(isDisplayed()))
    }

    @Test
    fun popupWindowContainsPlaceAddress(){
        val device = UiDevice.getInstance(getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains("Miejsce 1"))
        marker.click()
        onView(withText("Orchidei 22H")).inRoot(isPlatformPopup()).check(matches(isDisplayed()))
    }

    @Test
    fun popupWindowContainsPlacePhoneNumber(){
        val device = UiDevice.getInstance(getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains("Miejsce 1"))
        marker.click()
        onView(withText("123456789")).inRoot(isPlatformPopup()).check(matches(isDisplayed()))
    }

}
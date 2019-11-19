package com.ignaciosiel.theforkchallenge.domain.restaurant.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.ignaciosiel.theforkchallenge.domain.restaurant.details.RestaurantDetailsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class RestaurantListActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(RestaurantListActivity::class.java)

    @Test
    fun testListIsShown() {
        onView(withText("Kathmandu Coffee")).check(matches(isDisplayed()))
        onView(withText("L'épicerie Saint-Sabin")).check(matches(isDisplayed()))
        onView(withText("Angeethi")).check(matches(isDisplayed()))
        onView(withText("Indien")).check(matches(isDisplayed()))
    }

    @Test
    fun testOnItemClick_goToDetailsActivity() {
        initIntents()

        onView(withText("Kathmandu Coffee")).perform(click())

        intended(hasComponent(RestaurantDetailsActivity::class.java.getName()))
    }

    private fun initIntents() {
        Intents.init()
    }
}
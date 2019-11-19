package com.ignaciosiel.theforkchallenge.domain.restaurant.details

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.ignaciosiel.theforkchallenge.R
import kotlinx.android.synthetic.main.activity_restaurant_details.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class RestaurantDetailsActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<RestaurantDetailsActivity>
            = ActivityTestRule<RestaurantDetailsActivity>(
        RestaurantDetailsActivity::class.java, true, false)

    @Test
    fun testAllInfoIsShown() {
        activityRule.launchActivity(createIntent(6861L))

        assertEquals("Katmandou Café", activityRule.activity.toolbar_layout.title.toString())

        onView(withId(android.R.id.content)).perform(swipeUp())

        onView(withText("Excellente table")).check(matches(isDisplayed()))

        onView(withId(R.id.make_reservation_button)).check(matches(isDisplayed()))
        onView(withId(R.id.make_reservation_button)).check(matches(isEnabled()))
    }

    @Test
    fun testReservationClick() {
        activityRule.launchActivity(createIntent(6861L))

        onView(withId(R.id.make_reservation_button)).check(matches(isDisplayed()))
        onView(withId(R.id.make_reservation_button)).check(matches(isEnabled()))
        onView(withId(R.id.make_reservation_button)).perform(click())

        assert(activityRule.activity.isDestroyed)
    }

    @Test
    fun testNetworkError() {
        activityRule.launchActivity(createIntent(1234L))
        assert(activityRule.activity.isDestroyed)
    }

    private fun createIntent(id: Long): Intent {
        val intent = Intent()
        intent.putExtra(RestaurantDetailsActivity.RESTAURANT_ID_KEY, id)
        return intent
    }
}
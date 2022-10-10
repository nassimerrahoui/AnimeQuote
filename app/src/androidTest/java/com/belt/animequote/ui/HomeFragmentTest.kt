package com.belt.animequote.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.belt.animequote.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@LargeTest
class HomeFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun shouldDisplayAppTitle() {
        onView(ViewMatchers.withText("Anime Quote")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun shouldDisplayHomeTitle() {
        onView(ViewMatchers.withText("Les animés disponibles"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun shouldDisplayAvailableAnime() {
        onView(ViewMatchers.withId(R.id.anime_recycler_view))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("AnimeOne"))))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("AnimeTwo"))))
    }
}
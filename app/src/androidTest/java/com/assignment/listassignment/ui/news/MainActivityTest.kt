package com.assignment.listassignment.ui.news


import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.assignment.listassignment.R
import com.assignment.listassignment.ui.news.adapter.NewsListAdapter

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/*
class MainActivityTest {



    @Test
    fun setBinding() {
    }

    @Test
    fun setSwipeCount() {
    }

    @Test
    fun onCreate() {
    }

    @Test
    fun onResume() {
    }

    @Test
    fun onItemClick() {
    }
}*/
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    /**
     * Use [ActivityScenario] to create and launch the activity under test. This is a
     * replacement for [androidx.test.rule.ActivityTestRule].
     */
    @Rule
    var activityScenarioRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun scrollToItemBelowFold_checkItsText() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.rvNews))
            .perform(actionOnItemAtPosition<NewsListAdapter.ViewHolder>(ITEM_BELOW_THE_FOLD, click()))


        // Match the text in an item below the fold and check that it's displayed.
        val itemElementText = getApplicationContext<Context>().getResources().getString(
            R.string.item_element_text
        ) + ITEM_BELOW_THE_FOLD.toString()
        onView(withText(itemElementText)).check(matches(isDisplayed()))
    }

    @Test
    fun itemInMiddleOfList_hasSpecialText() {
        // First, scroll to the view holder using the isInTheMiddle matcher.
        onView(ViewMatchers.withId(R.id.rvNews))
            .perform(RecyclerViewActions.scrollToHolder<NewsListAdapter.ViewHolder>(isInTheMiddle))

        // Check that the item has the special text.
        val middleElementText =
            getApplicationContext<Context>().getResources().getString(R.string.middle)
        onView(withText(middleElementText)).check(matches(isDisplayed()))
    }

    companion object {

        private val ITEM_BELOW_THE_FOLD = 40

        /**
         * Matches the [CustomAdapter.ViewHolder]s in the middle of the list.
         */
        private val isInTheMiddle: Matcher<NewsListAdapter.ViewHolder>
            get() = object : TypeSafeMatcher<NewsListAdapter.ViewHolder>() {
                protected override fun matchesSafely(customHolder: NewsListAdapter.ViewHolder): Boolean {
                    return customHolder.getIsInTheMiddle()
                }

                override fun describeTo(description: Description) {
                    description.appendText("item in the middle")
                }
            }
    }
}
package io.github.zagori.nytimes.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import io.github.zagori.nytimes.R
import io.github.zagori.nytimes.ui.activities.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testMainActivityFlow() {
        sleep(2000)

        checkMainFragmentVisible()
        checkClickSearchOpensSearchFragment()
        checkSearchFragmentVisible()
        checkEnterTextEnablesSearchButton()
        checkClickSearchOpensArticlesFragment()
        checkSearchListScrollable()
        navigateBackToMain()
        openMostViewedAndCheckListScrollable()
        openMostSharedAndCheckListScrollable()
        openMostEmailedAndCheckListScrollable()
    }

    private fun checkMainFragmentVisible() {
        onView(withId(R.id.toolbar))
            .check(matches(hasDescendant(withText("The New York Times"))))

        onView(withId(R.id.item_search)).check(
            matches(isDisplayed())
        )

        onView(withId(R.id.item_most_viewed)).check(
            matches(isDisplayed())
        )

        onView(withId(R.id.item_most_shared)).check(
            matches(isDisplayed())
        )

        onView(withId(R.id.item_most_emailed)).check(
            matches(isDisplayed())
        )
    }

    private fun checkClickSearchOpensSearchFragment() {
        onView(withId(R.id.item_search)).perform(click())

        sleep(2000)

        onView(withId(R.id.toolbar))
            .check(matches(hasDescendant(withText("Search"))))
    }

    private fun checkSearchFragmentVisible() {
        onView(withId(R.id.search_input_layout)).check(
            matches(isDisplayed())
        )

        onView(withId(R.id.search_btn)).check(
            matches(isDisplayed())
        )

        onView(withId(R.id.search_btn)).check(
            matches(not(isEnabled()))
        )
    }

    private fun checkEnterTextEnablesSearchButton() {
        onView(withId(R.id.search_input))
            .perform(typeText("Election"))

        onView(withId(R.id.search_btn)).check(
            matches(isEnabled())
        )
    }

    private fun checkClickSearchOpensArticlesFragment() {
        onView(withId(R.id.search_btn)).perform(click())

        sleep(2000)

        onView(withId(R.id.toolbar))
            .check(matches(hasDescendant(withText("Search: Election"))))
    }

    private fun checkSearchListScrollable(){
        onView(withId(R.id.recycler_view)).perform(ViewActions.swipeUp())
        onView(withId(R.id.recycler_view)).perform(ViewActions.swipeDown())
    }

    private fun navigateBackToMain(){
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    private fun openMostViewedAndCheckListScrollable() {
        onView(withId(R.id.item_most_viewed)).perform(click())

        sleep(2000)

        onView(withId(R.id.toolbar))
            .check(matches(hasDescendant(withText("Most Viewed Articles"))))

        onView(withId(R.id.recycler_view)).perform(ViewActions.swipeUp())
        onView(withId(R.id.recycler_view)).perform(ViewActions.swipeDown())

        onView(isRoot()).perform(ViewActions.pressBack())
    }

    private fun openMostSharedAndCheckListScrollable() {
        onView(withId(R.id.item_most_shared)).perform(click())

        sleep(2000)

        onView(withId(R.id.toolbar))
            .check(matches(hasDescendant(withText("Most Shared Articles"))))

        onView(withId(R.id.recycler_view)).perform(ViewActions.swipeUp())
        onView(withId(R.id.recycler_view)).perform(ViewActions.swipeDown())

        onView(isRoot()).perform(ViewActions.pressBack())
    }

    private fun openMostEmailedAndCheckListScrollable() {
        onView(withId(R.id.item_most_emailed)).perform(click())

        sleep(2000)

        onView(withId(R.id.toolbar))
            .check(matches(hasDescendant(withText("Most emailed Articles"))))

        onView(withId(R.id.recycler_view)).perform(ViewActions.swipeUp())
        onView(withId(R.id.recycler_view)).perform(ViewActions.swipeDown())

        onView(isRoot()).perform(ViewActions.pressBack())
    }
}
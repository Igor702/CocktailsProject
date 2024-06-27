package com.example.cocktailsproject.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.cocktailsproject.R
import com.example.cocktailsproject.models.Onboarding
import com.example.cocktailsproject.withPagerData
import com.example.cocktailsproject.withSelectedTabPosition
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class CocktailsOnboardingFragmentTest {


    @Test
    fun testOfVisibility_allAppropriateElementsAreVisible() {
        val navController = mock(NavController::class.java)

        launchFragmentInContainer<CocktailsOnboardingFragment>(
            Bundle(),
            R.style.Theme_CocktailsProject
        ).onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }


        val onboarding = Onboarding(R.drawable.onboarding_favourites, R.string.onboarding_favourite)


        onView(withId(R.id.pager_onboardings))
            .check(matches(isDisplayed()))
            .check(matches(withPagerData(onboarding)))

        onView(withId(R.id.onboarding_tab_layout))
            .check(matches(isDisplayed()))
            .check(matches(withSelectedTabPosition(0)))

        onView(withId(R.id.btn_onboarding_go_to_cocktails))
            .check(matches(isDisplayed()))
    }


    @Test
    fun pressOnButtonToCocktails_navigateToInfoFragment() {

        val scenario = launchFragmentInContainer<CocktailsOnboardingFragment>(
            Bundle(),
            R.style.Theme_CocktailsProject
        )

        val navController = mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        onView(withId(R.id.btn_onboarding_go_to_cocktails)).perform(click())

        verify(navController).navigate(R.id.action_cocktailsOnboardingFragment_to_infoFragment)
    }


    //todo: make test: when onboardings were shown, then navigate to InfoFragment


}








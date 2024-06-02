package com.example.cocktailsproject.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.cocktailsproject.R
import com.example.cocktailsproject.ServiceLocator
import com.example.cocktailsproject.data.FakeAndroidCocktailsRepository
import com.example.cocktailsproject.isSwipeToRefreshAvailable
import com.example.cocktailsproject.models.CocktailRequest
import com.example.cocktailsproject.models.DrinkRawData
import com.example.cocktailsproject.withDrawable
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@RunWith(AndroidJUnit4::class)
class InfoFragmentTest {
    private lateinit var repository: FakeAndroidCocktailsRepository

    @Before
    fun setUpRepository() {

        repository = FakeAndroidCocktailsRepository()

        ServiceLocator.setTestRepository(repository)

    }

    @After
    fun cleanUpRepository() {
        ServiceLocator.resetRepository()
    }


    @Test
    fun uiStateSuccess_allViewIsVisible() {
        val cocktailRequest = CocktailRequest(
            listOf<DrinkRawData>(
                DrinkRawData(
                    idDrink = "id1",
                    strDrink = "YellowBird",
                    strCategory = "Cocktail",
                    strGlass = "Cocktail glass",
                    strAlcoholic = "Alcoholic",
                    strIngredient1 = "Vodka"
                )
            )
        )
        repository.setCocktail(cocktailRequest)



        launchFragmentInContainer<InfoFragment>(Bundle(), R.style.Theme_CocktailsProject)

        onView(withId(R.id.textview_name))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.name)))
        onView(withId(R.id.textview_category))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.category)))

        onView(withId(R.id.textview_type))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.type)))

        onView(withId(R.id.textview_glass))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.glass_type)))

        onView(withId(R.id.textview_ingredients))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.ingredients)))

        onView(withId(R.id.textview_cocktail_name))
            .check(matches(isDisplayed()))
            .check(matches(withText("YellowBird")))
        onView(withId(R.id.textview_cocktail_category))
            .check(matches(isDisplayed()))
            .check(matches(withText("Cocktail")))

        onView(withId(R.id.textview_cocktail_type))
            .check(matches(isDisplayed()))
            .check(matches(withText("Alcoholic")))

        onView(withId(R.id.textview_glass_type))
            .check(matches(isDisplayed()))
            .check(matches(withText("Cocktail glass")))

        onView(withId(R.id.textview_ingredients_cocktail))
            .check(matches(isDisplayed()))
            .check(matches(withText("Vodka")))

        onView(withId(R.id.imageview_info))
            .check(matches(not(withDrawable(R.drawable.drink_loading_error))))
        onView(withId(R.id.add_to_favourites))
            .check(matches(isDisplayed()))
    }


    @Test
    fun uiStateSuccess_addToFavouritesButton_removeFromFavourites_visibilityChanges() {
        val cocktailRequest = CocktailRequest(
            listOf<DrinkRawData>(
                DrinkRawData(
                    idDrink = "id1",
                    strDrink = "YellowBird",
                    strCategory = "Cocktail",
                    strGlass = "Cocktail glass",
                    strAlcoholic = "Alcoholic",
                    strIngredient1 = "Vodka"
                )
            )
        )
        repository.setCocktail(cocktailRequest)



        launchFragmentInContainer<InfoFragment>(Bundle(), R.style.Theme_CocktailsProject)

        onView(withId(R.id.add_to_favourites))
            .check(matches(isDisplayed()))
            .perform(click())


        onView(withId(R.id.remove_from_favourites))
            .check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favourites))
            .check(doesNotExist())


        onView(withId(R.id.remove_from_favourites))
            .perform(click())
            .check(doesNotExist())


        onView(withId(R.id.add_to_favourites))
            .check(matches(isDisplayed()))


    }

    @Test
    fun uiStateSuccess_swipeToRefresh_loadNewData() {
        val cocktailRequest = CocktailRequest(
            listOf<DrinkRawData>(
                DrinkRawData(
                    idDrink = "id1",
                    strDrink = "YellowBird",
                    strCategory = "Cocktail",
                    strGlass = "Cocktail glass",
                    strAlcoholic = "Alcoholic",
                    strIngredient1 = "Vodka"
                )
            )
        )
        repository.setCocktail(cocktailRequest)



        launchFragmentInContainer<InfoFragment>(Bundle(), R.style.Theme_CocktailsProject)

        onView(withId(R.id.textview_cocktail_name)).check(matches(withText("YellowBird")))
        val newCocktailRequest = CocktailRequest(
            listOf<DrinkRawData>(
                DrinkRawData(
                    idDrink = "id2",
                    strDrink = "Bloody Mary",
                    strCategory = "Cocktail",
                    strGlass = "Cocktail glass",
                    strAlcoholic = "Alcoholic",
                    strIngredient1 = "Tomato Juice",
                    strIngredient2 = "Vodka"
                )
            )
        )
        repository.setCocktail(newCocktailRequest)


        onView(withId(R.id.refresh_layout)).perform(swipeDown())

        onView(withId(R.id.textview_cocktail_name)).check(matches(withText("Bloody Mary")))


    }

    @Test
    fun uiStateSuccess_swipeToRefresh_changeRemoveFromFavouritesToAddToFavourites() {
        val cocktailRequest = CocktailRequest(
            listOf<DrinkRawData>(
                DrinkRawData(
                    idDrink = "id1",
                    strDrink = "YellowBird",
                    strCategory = "Cocktail",
                    strGlass = "Cocktail glass",
                    strAlcoholic = "Alcoholic",
                    strIngredient1 = "Vodka"
                )
            )
        )
        repository.setCocktail(cocktailRequest)



        launchFragmentInContainer<InfoFragment>(Bundle(), R.style.Theme_CocktailsProject)

        onView(withId(R.id.add_to_favourites))
            .perform(click())
        val newCocktailRequest = CocktailRequest(
            listOf<DrinkRawData>(
                DrinkRawData(
                    idDrink = "id2",
                    strDrink = "Bloody Mary",
                    strCategory = "Cocktail",
                    strGlass = "Cocktail glass",
                    strAlcoholic = "Alcoholic",
                    strIngredient1 = "Tomato Juice",
                    strIngredient2 = "Vodka"
                )
            )
        )
        repository.setCocktail(newCocktailRequest)


        onView(withId(R.id.refresh_layout)).perform(swipeDown())

        onView(withId(R.id.add_to_favourites)).check(matches(isDisplayed()))

    }

    @Test
    fun uiStateSuccess_swipeUpDown_swipeToRefreshOnlyFullInScreen() {

        val cocktailRequest = CocktailRequest(
            listOf<DrinkRawData>(
                DrinkRawData(
                    idDrink = "id1",
                    strDrink = "YellowBird",
                    strCategory = "Cocktail",
                    strGlass = "Cocktail glass",
                    strAlcoholic = "Alcoholic",
                    strIngredient1 = "Vodka"
                )
            )
        )
        repository.setCocktail(cocktailRequest)



        launchFragmentInContainer<InfoFragment>(Bundle(), R.style.Theme_CocktailsProject)

        onView(withId(R.id.refresh_layout))
            .perform(swipeUp())

        onView(withId(R.id.app_bar_layout))
            .check(matches(not(isSwipeToRefreshAvailable())))
        onView(withId(R.id.refresh_layout)).perform(swipeDown())

        onView(withId(R.id.app_bar_layout))
            .check(matches(isSwipeToRefreshAvailable()))
    }

    @Test
    fun uiStateLoading_appropriateUiElementsAreVisible() {
        val cocktailRequest = CocktailRequest(
            listOf<DrinkRawData>(
                DrinkRawData(
                    idDrink = "id1",
                    strDrink = "YellowBird",
                    strCategory = "Cocktail",
                    strGlass = "Cocktail glass",
                    strIngredient1 = "Vodka"
                )
            )
        )
        repository.setCocktail(cocktailRequest)

        repository.setDelayForLoadingTesting(true)

        launchFragmentInContainer<InfoFragment>(Bundle(), R.style.Theme_CocktailsProject)

        onView(withId(R.id.tv_loading_cocktail))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.we_re_loading_your_cocktail)))
        onView(withId(R.id.gif_view_info))
            .check(matches(isDisplayed()))

        onView(withId(R.id.add_to_favourites))
            .check(doesNotExist())

    }

    @Test
    fun uiStateError_errorUiElementsIsVisible() {


        launchFragmentInContainer<InfoFragment>(Bundle(), R.style.Theme_CocktailsProject)

        onView(withId(R.id.btn_error_try_again)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_ups))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.ups_something_get_wrong)))

        onView(withId(R.id.imageview_info))
            .check(matches(isDisplayed()))
            .check(matches(withDrawable(R.drawable.drink_loading_error)))

        onView(withId(R.id.refresh_layout))
            .check(matches(isEnabled()))

        onView(withId(R.id.add_to_favourites))
            .check(doesNotExist())

    }

    @Test
    fun uiStateError_btnTryAgain_loadData() {
        launchFragmentInContainer<InfoFragment>(Bundle(), R.style.Theme_CocktailsProject)

        val newCocktailRequest = CocktailRequest(
            listOf<DrinkRawData>(
                DrinkRawData(
                    idDrink = "id2",
                    strDrink = "Bloody Mary",
                    strCategory = "Cocktail",
                    strGlass = "Cocktail glass",
                    strAlcoholic = "Alcoholic",
                    strIngredient1 = "Tomato Juice",
                    strIngredient2 = "Vodka"
                )
            )
        )
        repository.setCocktail(newCocktailRequest)


        onView(withId(R.id.btn_error_try_again))
            .perform(click())

        onView(withId(R.id.textview_cocktail_name)).check(matches(withText("Bloody Mary")))
    }

    @Test
    fun uiStateError_swipeToRefresh_loadData() {
        launchFragmentInContainer<InfoFragment>(Bundle(), R.style.Theme_CocktailsProject)

        val newCocktailRequest = CocktailRequest(
            listOf<DrinkRawData>(
                DrinkRawData(
                    idDrink = "id2",
                    strDrink = "Bloody Mary",
                    strCategory = "Cocktail",
                    strGlass = "Cocktail glass",
                    strAlcoholic = "Alcoholic",
                    strIngredient1 = "Tomato Juice",
                    strIngredient2 = "Vodka"
                )
            )
        )
        repository.setCocktail(newCocktailRequest)


        onView(withId(R.id.refresh_layout))
            .perform(swipeDown())

        onView(withId(R.id.textview_cocktail_name)).check(matches(withText("Bloody Mary")))
    }


}


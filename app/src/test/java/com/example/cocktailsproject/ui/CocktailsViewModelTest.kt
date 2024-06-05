package com.example.cocktailsproject.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cocktailsproject.data.FakeCocktailsRepository
import com.example.cocktailsproject.models.CocktailRequest
import com.example.cocktailsproject.models.DrinkRawData
import com.example.cocktailsproject.models.toCocktail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class CocktailsViewModelTest {

    private lateinit var repository: FakeCocktailsRepository
    private lateinit var viewModel: CocktailsViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUpViewModel() {
        repository = FakeCocktailsRepository()
        viewModel = CocktailsViewModel(repository)
    }

    @Before
    fun setDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }


    @Test
    fun loadRemoteData_validData_returnSuccess() {

        val cocktail = CocktailRequest(
            listOf<DrinkRawData>(
                DrinkRawData(
                    idDrink = "id1",
                    strDrink = "title1"
                )
            )
        )
        repository.setCocktail(
            cocktail
        )


        runTest {
            viewModel.loadRemoteData()

        }

        val result = viewModel.data.getOrAwaitValue()
        println(result)

        assertThat(result.toString(), equalTo(UIState.Success(cocktail.toCocktail()).toString()))
    }

    @Test
    fun loadRemoteData_beforeLoadingData_returnLoading() {


        val cocktail = CocktailRequest(
            listOf<DrinkRawData>(
                DrinkRawData(
                    idDrink = "id1",
                    strDrink = "title1"
                )
            )
        )
        repository.setCocktail(
            cocktail
        )



        viewModel.loadRemoteData()


        val result = viewModel.data.getOrAwaitValue()
        println(result)

        assertThat(result.toString(), equalTo(UIState.Loading.toString()))

    }


    @Test
    fun loadRemoteData_emptyData_returnError() {

        val cocktail = CocktailRequest(
            listOf<DrinkRawData>(

            )
        )
        repository.setCocktail(
            cocktail
        )


        runTest {
            viewModel.loadRemoteData()

        }

        val result = viewModel.data.getOrAwaitValue()
        println(result)

        assertThat(result.toString(), equalTo(UIState.Error.toString()))
    }

    @Test
    fun reloadRemoteData_modifyData() {


        val cocktail = CocktailRequest(
            listOf<DrinkRawData>(
                DrinkRawData(
                    idDrink = "id1",
                    strDrink = "title1"
                )
            )
        )
        repository.setCocktail(
            cocktail
        )


        runTest {
            viewModel.reloadRemoteData()

        }

        val result = viewModel.data.getOrAwaitValue()
        println(result)

        assertThat(result.toString(), equalTo(UIState.Success(cocktail.toCocktail()).toString()))
    }
}


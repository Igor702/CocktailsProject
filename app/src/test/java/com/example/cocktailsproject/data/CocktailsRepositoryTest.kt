package com.example.cocktailsproject.data

import com.example.cocktailsproject.data.remote.FakeRemoteDataSource
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test


class CocktailsRepositoryTest {

    private lateinit var repository: CocktailsRepository

    @Before
    fun getRepository() {
        val fakeRemoteDataSource = FakeRemoteDataSource()

        repository = CocktailsRepository(fakeRemoteDataSource)
    }

    @Test
    fun getRemoteData_returnRemoteData() = runTest {
        val result = repository.getRemoteData()
        assertThat(result.drinks[0].idDrink, IsEqual("id1"))
    }

}
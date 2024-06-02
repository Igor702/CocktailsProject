package com.example.cocktailsproject.ui

import com.example.cocktailsproject.data.FakeCocktailsRepository

import org.junit.Before


class CocktailsViewModelTest {

    private lateinit var repository: FakeCocktailsRepository
    private lateinit var viewModel: CocktailsViewModel

    @Before
    fun setUpViewModel() {
        repository = FakeCocktailsRepository()
        viewModel = CocktailsViewModel(repository)
    }


}
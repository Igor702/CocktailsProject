package com.example.cocktailsproject.di

import com.example.cocktailsproject.data.FakeAndroidCocktailsRepository
import com.example.cocktailsproject.data.ICocktailsRepository
import dagger.Binds
import dagger.Module


@Module
interface TestRepositoryModule {
    @Binds
    fun bindICocktailsRepository(cocktailsRepository: FakeAndroidCocktailsRepository): ICocktailsRepository
}
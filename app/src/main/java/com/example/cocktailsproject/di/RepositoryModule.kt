package com.example.cocktailsproject.di

import com.example.cocktailsproject.data.CocktailsRepository
import com.example.cocktailsproject.data.ICocktailsRepository
import dagger.Binds
import dagger.Module


@Module
interface RepositoryModule {
    @Binds
    fun provideICocktailsRepository(cocktailsRepository: CocktailsRepository): ICocktailsRepository
}
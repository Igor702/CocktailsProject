package com.example.cocktailsproject.di

import android.content.Context
import com.example.cocktailsproject.ui.SingleCocktailViewModel
import com.example.cocktailsproject.ui.CocktailsViewModel
import com.example.cocktailsproject.ui.HomeViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, RemoteModule::class, LocalModule::class, DatabaseModule::class])
interface AppComponent {

    fun cocktailsViewModelFactory(): CocktailsViewModel.Factory

    fun homeViewModelFactory(): HomeViewModel.Factory

    fun cocktailViewModelFactory(): SingleCocktailViewModel.Factory


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}
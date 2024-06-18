package com.example.cocktailsproject.di

import android.content.Context
import com.example.cocktailsproject.ui.CocktailsViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, RemoteModule::class])
interface AppComponent {

    fun viewModelFactory(): CocktailsViewModel.Factory


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}
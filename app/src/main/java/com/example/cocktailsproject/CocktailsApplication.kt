package com.example.cocktailsproject

import android.app.Application
import com.example.cocktailsproject.di.AppComponent
import com.example.cocktailsproject.di.DaggerAppComponent

class CocktailsApplication : Application() {

    val daggerComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}
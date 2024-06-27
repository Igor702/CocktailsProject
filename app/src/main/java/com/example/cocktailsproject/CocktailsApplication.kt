package com.example.cocktailsproject

import android.app.Application
import com.example.cocktailsproject.di.AppComponent
import com.example.cocktailsproject.di.DaggerAppComponent

open class CocktailsApplication : Application() {

    open val daggerComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}
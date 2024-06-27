package com.example.cocktailsproject

import com.example.cocktailsproject.di.DaggerTestAppComponent
import com.example.cocktailsproject.di.TestAppComponent

class TestCocktailsApplication : CocktailsApplication() {


    override fun initializeComponent(): TestAppComponent {
        return DaggerTestAppComponent.create()
    }
}
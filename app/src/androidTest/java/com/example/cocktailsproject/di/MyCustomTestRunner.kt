package com.example.cocktailsproject.di

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.cocktailsproject.TestCocktailsApplication

class MyCustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestCocktailsApplication::class.java.name, context)
    }
}
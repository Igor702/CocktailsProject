package com.example.cocktailsproject.di

import com.example.cocktailsproject.ui.InfoFragmentTest
import dagger.Component


@Component(modules = [TestRepositoryModule::class])
interface TestAppComponent : AppComponent {


    fun inject(test: InfoFragmentTest)

//    @Component.Factory
//    interface Factory {
//        fun create(@BindsInstance context: Context): TestAppComponent
//    }
}
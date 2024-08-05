package com.example.cocktailsproject.di

import com.example.cocktailsproject.data.local.ILocalDataSource
import com.example.cocktailsproject.data.local.LocalDataSource
import dagger.Binds
import dagger.Module

@Module
interface LocalModule {

    @Binds
    fun bindILocalDataSource(localDataSource: LocalDataSource):ILocalDataSource
}
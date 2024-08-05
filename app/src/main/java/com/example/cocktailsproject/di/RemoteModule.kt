package com.example.cocktailsproject.di

import com.example.cocktailsproject.data.remote.IRemoteDataSource
import com.example.cocktailsproject.data.remote.RemoteDataSource
import dagger.Binds
import dagger.Module


@Module
interface RemoteModule {
    @Binds
    fun bindIRemoteDataSource(remoteDataSource: RemoteDataSource): IRemoteDataSource
}
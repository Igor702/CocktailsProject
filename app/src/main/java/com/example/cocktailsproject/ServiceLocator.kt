package com.example.cocktailsproject

import androidx.annotation.VisibleForTesting
import com.example.cocktailsproject.data.CocktailsRepository
import com.example.cocktailsproject.data.ICocktailsRepository
import com.example.cocktailsproject.data.remote.RemoteDataSource

object ServiceLocator {

    private val lock = Any()

    @Volatile
    private var repository: ICocktailsRepository? = null

    fun provideCocktailsRepository(): ICocktailsRepository {
        synchronized(lock){
            return repository ?: createCocktailsRepository()
        }
    }


    private fun createCocktailsRepository():ICocktailsRepository{
        val newRepo = CocktailsRepository(RemoteDataSource())
        repository = newRepo
        return newRepo
    }


    fun resetRepository(){
        repository = null
    }

    @VisibleForTesting
    fun setTestRepository(repo: ICocktailsRepository){
        repository = repo

    }


}
package com.example.cocktailsproject.data.remote

import com.example.cocktailsproject.models.CocktailRequest

interface IRemoteDataSource {
    suspend fun getRandomCocktail(): CocktailRequest
}
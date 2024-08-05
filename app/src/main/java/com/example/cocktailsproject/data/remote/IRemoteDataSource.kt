package com.example.cocktailsproject.data.remote

import com.example.cocktailsproject.models.Cocktail
import com.example.cocktailsproject.models.CocktailRequest

interface IRemoteDataSource {
    suspend fun getRandomCocktail(): CocktailRequest

   suspend fun searchCocktailByName(query: String): CocktailRequest
    suspend fun  getCocktailById(id: Int): CocktailRequest
}
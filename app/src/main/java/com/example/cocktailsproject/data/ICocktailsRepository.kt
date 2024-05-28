package com.example.cocktailsproject.data

import com.example.cocktailsproject.models.CocktailRequest

interface ICocktailsRepository {
    suspend fun getRemoteData(): CocktailRequest
}
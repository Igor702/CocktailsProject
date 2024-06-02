package com.example.cocktailsproject.data

import com.example.cocktailsproject.models.CocktailRequest

class FakeCocktailsRepository : ICocktailsRepository {

    private var cocktailServiceRequest = CocktailRequest(mutableListOf())

    fun setCocktail(cocktailRequest: CocktailRequest) {
        cocktailServiceRequest = cocktailRequest
    }

    override suspend fun getRemoteData(): CocktailRequest {
        return cocktailServiceRequest
    }
}
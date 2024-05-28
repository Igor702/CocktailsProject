package com.example.cocktailsproject.data

import com.example.cocktailsproject.data.remote.IRemoteDataSource
import com.example.cocktailsproject.models.CocktailRequest

class CocktailsRepository(
    private val remoteData: IRemoteDataSource
) : ICocktailsRepository {

    override suspend fun getRemoteData(): CocktailRequest {

        return remoteData.getRandomCocktail()
    }

}
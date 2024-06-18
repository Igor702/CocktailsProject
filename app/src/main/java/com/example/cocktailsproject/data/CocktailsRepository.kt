package com.example.cocktailsproject.data

import com.example.cocktailsproject.data.remote.IRemoteDataSource
import com.example.cocktailsproject.models.CocktailRequest
import javax.inject.Inject

class CocktailsRepository @Inject constructor(
    private val remoteData: IRemoteDataSource
) : ICocktailsRepository {

    override suspend fun getRemoteData(): CocktailRequest {

        return remoteData.getRandomCocktail()
    }

}
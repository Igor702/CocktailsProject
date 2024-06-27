package com.example.cocktailsproject.data

import com.example.cocktailsproject.models.CocktailRequest
import javax.inject.Inject

class FakeAndroidCocktailsRepository @Inject constructor() : ICocktailsRepository {


    override suspend fun getRemoteData(): CocktailRequest {

        return CocktailTestLoader.getRemoteData()
    }

}


package com.example.cocktailsproject.data.remote

import com.example.cocktailsproject.models.CocktailRequest
import com.example.cocktailsproject.models.DrinkRawData

class FakeRemoteDataSource : IRemoteDataSource {
    override suspend fun getRandomCocktail(): CocktailRequest {
        return CocktailRequest(
            listOf<DrinkRawData>(
                DrinkRawData(
                    idDrink = "id1",
                    strDrink = "title1"
                )
            )
        )
    }
}